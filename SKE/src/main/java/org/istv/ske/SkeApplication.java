package org.istv.ske;

import org.istv.ske.core.service.UserServiceImpl;
import org.istv.ske.dal.entities.User;
import org.istv.ske.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan({"org.istv.ske"})
public class SkeApplication extends SpringBootServletInitializer {
    @Autowired
    UserRepository userRepository;
    private boolean crypted = false;
    private static Class<SkeApplication> applicationClass = SkeApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(SkeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRED)
    private void postConstruct() {
        // Changer password claires en chiffrés
        if (crypted == false) {
            chiffrerAutoCompletion();
            crypted = true;
        }
    }

    public void chiffrerAutoCompletion() {
        List<User> users = new ArrayList<>();
        try {
            for (User u : userRepository.findAll()) {
                u.setUserPassword(UserServiceImpl.chiffrer(u.getUserPassword()));
                userRepository.save(u);
            }
        } catch (Exception e) {
            System.out.println("Aucun utilisateur");
        }
    }
}

