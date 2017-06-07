package org.istv.ske.messages.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by abdel on 06/06/2017.
 */
@Configuration
@PropertySource("classpath:emailSender.properties")
@EnableAsync
@EnableTransactionManagement
public class EmailSender {
    @Value("${mailSender.host}")
    private String host;
    @Value("${mailSender.port}")
    private int port;
    @Value("${mailSender.username}")
    private String username;
    @Value("${mailSender.password}")
    private String password;

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(host);
        javaMailSenderImpl.setPort(port);
        javaMailSenderImpl.setUsername(username);
        javaMailSenderImpl.setPassword(password);
        javaMailSenderImpl.setJavaMailProperties(javaMailProperties());
        return javaMailSenderImpl;
    }

    public Properties javaMailProperties(){
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.quitwait", "true");
        return properties;
    }
}