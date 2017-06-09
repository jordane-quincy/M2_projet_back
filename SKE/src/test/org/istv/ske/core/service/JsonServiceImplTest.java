package org.istv.ske.core.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.istv.ske.core.service.JsonService;
import org.istv.ske.core.service.JsonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

/**
 * Created by sarasentouh on 07/06/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = {JsonServiceImpl.class})
public class JsonServiceImplTest {

    @Autowired
    JsonService jsonService;

    @Test
    public void parse1() throws Exception {
        //JsonObject retour = jsonService.parse("{id:3, credit:5,userMail:\"ruffin.clem@gmail.com\", userPassword:\"pass\", userName:\"Ruffin\", userFirstName:\"Clément\", birthday:1496832215000, offers:null, notifications\":null, role:\"TEACHER\",formation:null,skills:null}").getAsJsonObject();

        JsonObject retour = jsonService.parse("{email: \"ruffin.cle@gmail.com\", name: \"Ruffin\", firstName: \"Clément\", password: \"pass\", birthday: 1496832215000, formationName: \"Master TNSI\", formationLevel: \"M2\"}").getAsJsonObject();



        assertNotNull(retour);
        assertEquals("ruffin.cle@gmail.com", retour.get("email").getAsString());
        assertEquals("Ruffin", retour.get("name").getAsString());
        assertEquals("Clément", retour.get("firstName").getAsString());
        assertEquals("pass", retour.get("password").getAsString());
        assertEquals("1496832215000", retour.get("birthday").getAsString());
        assertEquals("Master TNSI", retour.get("formationName").getAsString());
        assertEquals("M2", retour.get("formationLevel").getAsString());


    }



    @Test
    public void parse2() throws Exception {
    }




    @Test
    public void stringify() throws Exception {
    }




    //  email: \"ruffin.cle@gmail.com\", name: \"Ruffin\", firstName: \"Clément\", password: \"pass\", birthday: 1496832215000, formationName: \"Master TNSI\", formationLevel: \"M2\"


}