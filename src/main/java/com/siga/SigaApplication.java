package com.siga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class SigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SigaApplication.class, args);
	}

	/*
	 * @Bean public SpringTemplateEngine templateEngine() { SpringTemplateEngine
	 * templateEngine = new SpringTemplateEngine(); templateEngine.addDialect(new
	 * LayoutDialect()); return templateEngine; }
	 */
}
