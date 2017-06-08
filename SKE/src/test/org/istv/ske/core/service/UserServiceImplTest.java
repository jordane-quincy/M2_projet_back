package org.istv.ske.core.service;

import org.istv.ske.dal.entities.Formation;
import org.istv.ske.dal.entities.SecretQuestion;
import org.istv.ske.dal.entities.Skill;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.SecretQuestionRepository;
import org.istv.ske.dal.repository.SkillRepository;
import org.istv.ske.dal.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sarasentouh on 08/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = {UserServiceImpl.class})
public class UserServiceImplTest {

    @Autowired
    @InjectMocks
    UserService userService;


    @Mock
    UserRepository userRepository;
    @Mock
    SkillRepository skillRepository;
    @Mock
    SecretQuestionRepository secretQuestionRepository;

    private Skill skill;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        userService.setUserRepository(userRepository);
        userService.setSkillRepository(skillRepository);
        userService.setSecretQuestionRepository(secretQuestionRepository);

        skill = new Skill();
        Mockito.when(skillRepository.findByLabel(Mockito.anyString())).thenReturn(skill);
    }

    @Test
    public void createUser() throws Exception {

        Formation f = new Formation();
        SecretQuestion Question = new SecretQuestion();
        List<String> skills = new ArrayList();

        User user = userService.createUser("ruffin.cle@gmail.com", "Ruffin", "Clément", "pass", 1496832215000l, f, Question, skills);

        assertNotNull(user);


    }
}