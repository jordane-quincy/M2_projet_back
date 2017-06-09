package org.istv.ske.core.service;

import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.repository.FormationRepository;
import org.istv.ske.dal.repository.SecretQuestionRepository;
import org.istv.ske.dal.repository.SkillRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.annotations.BeforeMethod;

/**
 * Created by sarasentouh on 08/06/2017.
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes =
// { UserServiceImpl.class })
public class UserServiceImplTest {

	@Autowired
	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;
	@Mock
	SkillRepository skillRepository;
	@Spy
	FormationRepository formationRepository;
	@Mock
	SecretQuestionRepository secretQuestionRepository;

	private MockMvc mockMvc;

	private Skill skill;

	@BeforeMethod
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		// userService.setUserRepository(userRepository);
		// userService.setSkillRepository(skillRepository);
		// userService.setSecretQuestionRepository(secretQuestionRepository);

		skill = new Skill();
		Mockito.when(skillRepository.findByLabel(Mockito.anyString())).thenReturn(skill);
	}

	@Test
	public void createUser() throws Exception {

		/*
		 * Formation formation = formationRepository.findOne(1L); SecretQuestion
		 * question = secretQuestionRepository.findOne((long) 1); List<String>
		 * skills = null;
		 */

		// User user = userService.createUser("ruffin.cle@gmail.com", "Ruffin",
		// "Clément", "pass", 1496832215000l,
		// formation, question, skills, "0664859755");

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("https://httpbin.org/get");
		// .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		// .header("Authorization", "429465ad74003fd8528f92855a3bd717");//
		// .andDo(MockMvcResultHandlers.print())
		/*
		 * MockHttpServletRequestBuilder requestBuilder =
		 * MockMvcRequestBuilders.post("/auth/connect") .param("email",
		 * "jordane.quincy@etu.univ-valenciennes.fr").param("password",
		 * "jquincy") .accept(MediaType.APPLICATION_JSON).contentType(MediaType.
		 * APPLICATION_JSON); System.out.println(requestBuilder);
		 */
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			System.out.println(result.toString());
		} catch (Exception e) {
			System.out.println("Result is null !");
		}
	}
}