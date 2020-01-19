package com.orest.rest_recap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


import java.util.Locale;

@SpringBootApplication
public class RestRecapApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestRecapApplication.class, args);
	}



	@Bean
	public LocaleResolver localeResolver() {
	//	SessionLocaleResolver localeResolver = new SessionLocaleResolver(); have to add content in arguments
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver(

		);
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

/*	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		return messageSource;
	}

	or spring.messages.basename=messages is the same, as method above

	*/


}
