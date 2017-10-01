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
		registry.addViewController("/notas").setViewName("solicita-notas");
		registry.addViewController("/solicitarNotas").setViewName("solicita-notas");
		registry.addViewController("/solicitarAprovados").setViewName("solicita-aprovados");
		registry.addViewController("/vagasOciosas").setViewName("vagas-ociosas");
	}

}
