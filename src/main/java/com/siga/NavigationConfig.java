package com.siga;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class NavigationConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/forgotpassword").setViewName("forgot-password");
		registry.addViewController("/solicitarNotas").setViewName("solicitar-notas");
		registry.addViewController("/solicitarAprovados").setViewName("solicitar-aprovados");
		registry.addViewController("/vagasOciosas").setViewName("vagas-ociosas");
	}

}
