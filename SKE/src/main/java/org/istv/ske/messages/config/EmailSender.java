package org.istv.ske.messages.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by abdel on 06/06/2017.
 */
@Configuration
@PropertySource("classpath:emailSender.properties")
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
		javaMailSenderImpl.setDefaultEncoding("UTF-8");
		javaMailSenderImpl.setJavaMailProperties(javaMailProperties());
		return javaMailSenderImpl;
	}

	public Properties javaMailProperties() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return properties;
	}
}