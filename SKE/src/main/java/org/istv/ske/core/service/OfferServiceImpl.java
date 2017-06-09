package org.istv.ske.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.istv.ske.core.utils.StringUtils;
import org.istv.ske.dal.entities.Domain;
import org.istv.ske.dal.entities.Offer;
import org.istv.ske.dal.entities.Remark;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.entities.User.Role;
import org.istv.ske.dal.repository.OfferRepository;
import org.istv.ske.dal.repository.RemarkRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RemarkRepository remarkRepository;

	@PersistenceContext(name = "ske")
	private EntityManager em;

	@Override
	public Offer createOffer(User user, String title, int duration, String description, Domain domain) {
		Offer offer = new Offer();
		offer.setDescription(description);
		offer.setDuration(duration);
		offer.setStatus(false);
		offer.setDomain(domain);
		offer.setTitle(title);
		offer.setUser(user);

		Set<String> selectedWords = new HashSet<>();
		String raw = title + " " + description;
		raw = raw.toLowerCase().replaceAll("[-+.^:,']", "");
		String[] words = raw.split(" ");
		for (String word : words) {
			if (!word.isEmpty()) {
				String wordWithoutAccent = StringUtils.removeAccents(word);
				if (!StringUtils.WORDS_TO_DELETE.contains(wordWithoutAccent)) {
					selectedWords.add(wordWithoutAccent);
				}
			}
		}
		offer.setKeywords(StringUtils.join(selectedWords));
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public List<Offer> findByUserId(Long userId) {
		User user = userRepository.findOne(userId);
		return offerRepository.findByUser(user);
	}

	@Override
	public Offer findById(Long id) {
		return offerRepository.findOne(id);
	}

	@Override
	public Offer updateOffer(Long offerId, String title, int duration, String description, Domain domain) {
		Offer offer = offerRepository.findOne(offerId);
		offer.setTitle(title);
		offer.setDuration(duration);
		offer.setDescription(description);
		offer.setDomain(domain);

		Set<String> selectedWords = new HashSet<>();
		String raw = title + " " + description;
		raw = raw.toLowerCase().replaceAll("[-+.^:,']", "");
		String[] words = raw.split(" ");
		for (String word : words) {
			if (!word.isEmpty()) {
				String wordWithoutAccent = StringUtils.removeAccents(word);
				if (!StringUtils.WORDS_TO_DELETE.contains(wordWithoutAccent)) {
					selectedWords.add(wordWithoutAccent);
				}
			}
		}
		offer.setKeywords(StringUtils.join(selectedWords));

		offerRepository.save(offer);
		return offer;
	}

	@Override
	public Offer addComment(Long offerId, String comment, int grade) {
		Offer offer = offerRepository.findOne(offerId);
		Remark remark = new Remark(comment, offer, grade);
		offer.getRemarks().add(remark);
		remarkRepository.save(remark);
		offerRepository.save(offer);

		return offer;
	}

	@Override
	public List<Offer> findAll() {
		List<Offer> offer = new ArrayList<>();
		for (Offer d : offerRepository.findAll()) {
			offer.add(d);
		}
		return offer;
	}

	@Override
	public List<Offer> search(String keywords, List<Long> domains, int durationMin, int durationMax, boolean teacher,
			boolean student, int minAvgGrade) {

		boolean both = teacher & student;
		String queryStr = "SELECT o FROM Offer o ";
		queryStr += "WHERE o.duration <= :durationMax AND o.duration >= :durationMin ";

		if (!both && (teacher || student)) {
			queryStr += "AND o.user.role = '" + (teacher ? Role.TEACHER.name() : Role.STUDENT.name()) + "' ";
		}

		if (domains != null && !domains.isEmpty()) {
			queryStr += "AND o.domain.id IN :domains ";
		}

		if (minAvgGrade != 0) {
			queryStr += "AND o.user.id IN (SELECT o2.user.id FROM Offer o2 JOIN o2.remarks r GROUP BY o2.user.id HAVING AVG(r.grade) >= :minAvgGrade) ";
		}

		String[] words = keywords.split(" ");
		String searchClause = "";
		boolean addOr = false;
		for (String word : words) {
			if (!word.isEmpty()) {
				String searchWord = word.toLowerCase().replaceAll("[-+.^:,']", "");
				searchWord = StringUtils.removeAccents(searchWord);
				if (addOr)
					searchClause += "OR ";
				searchClause += "o.keywords LIKE '%" + searchWord + "%' ";
				addOr = true;
			}
		}

		if (!searchClause.isEmpty()) {
			queryStr += "AND (" + searchClause + ")";
		}

		System.out.println(queryStr);

		// + "LEFT JOIN user on offer.user_id = user.id "
		// + "WHERE user.id IN (SELECT user_id from offer join remark ON
		// remark.offer_id = offer.id GROUP BY user_id HAVING AVG(remark.grade)
		// >2) "
		// + "AND offer.duration <= 3 AND offer.duration >= 1 AND
		// offer.domain_id = 4 "
		// + "AND (UPPER(offer.title) LIKE UPPER('%java%') OR
		// UPPER(offer.description) LIKE UPPER('%java%'))";

		Query query = em.createQuery(queryStr);
		if (domains != null && !domains.isEmpty())
			query.setParameter("domains", domains);
		query.setParameter("durationMax", durationMax);
		query.setParameter("durationMin", durationMin);
		if (minAvgGrade != 0)
			query.setParameter("minAvgGrade", (double) minAvgGrade);

		return (List<Offer>) query.getResultList();
	}

	@Override
	public void updateStatus(Long offerId, boolean status) {
		// TODO Auto-generated method stub
		Offer offer = offerRepository.findOne(offerId);
		offer.setStatus(status);
		offerRepository.save(offer);
	}

}
