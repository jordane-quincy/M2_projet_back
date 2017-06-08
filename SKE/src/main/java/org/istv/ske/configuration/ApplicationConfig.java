package org.istv.ske.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.istv.ske.core.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
@PropertySource("classpath:/META-INF/database.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.istv.ske")
@EnableAsync
public class ApplicationConfig {

	public static final String JSON_SUCCESS = "{\"ok\":\"true\"}";

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan("org.istv.ske");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.id.new_generator_mappings", "false");
		props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.hbm2ddl.import_files", "Data.sql");
		entityManagerFactory.setJpaProperties(props);
		return entityManagerFactory;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager platformTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(getTokenValidationFilter());
		registration.addUrlPatterns("/*");
		registration.setName("tokenValidationFilter");
		registration.setOrder(1);
		return registration;
	}

	@Bean(name = "tokenValidationFilter")
	public Filter getTokenValidationFilter() {
		return new TokenValidationFilter();
	}

	@ControllerAdvice
	public class BadRequestExceptionHandler {
		@ExceptionHandler(value = { BadRequestException.class })
		public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response,
				BadRequestException e) throws IOException {
			response.sendError(e.getStatus(), e.getMessage());
			return null;
		}
	}

	@ControllerAdvice
	public class InternalExceptionHandler {
		@ExceptionHandler(value = { Exception.class, RuntimeException.class })
		public String defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
				throws IOException {
			response.sendError(500, e.getMessage());
			return null;
		}
	}
}
