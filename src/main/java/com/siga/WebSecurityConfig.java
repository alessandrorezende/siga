package com.siga;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.siga.model.Usuario;
import com.siga.repository.Usuarios;
import com.siga.util.GenerateHashPasswordUtil;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Usuarios usuarios;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/pug/**", "/scss/**", "/vendor/**", "/webjars/**", "/register",
						"/forgotpassword")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
				.logout().permitAll().and()
				.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Password login MD5
		auth.authenticationProvider(authProvider());

		// Adicionar usuario na lista de usuarios autorizados
		auth.userDetailsService(inMemoryUserDetailsManager());

		// Carregar usuarios que estao no banco de dados
		List<Usuario> listaUsuario = usuarios.findAll();
		for (Usuario u : listaUsuario) {
			auth.inMemoryAuthentication().withUser(u.getUsername())
					.password(GenerateHashPasswordUtil.generateHash(u.getPassword())).roles("USER");
		}

		// Acesso do administrador
		auth.inMemoryAuthentication().withUser("alessandrorezende@msn.com")
				.password(GenerateHashPasswordUtil.generateHash("123456")).roles("ADMIN");
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		final Properties users = new Properties();
		users.put("user", "pass,ROLE_USER,enabled"); // add whatever other user you need
		return new InMemoryUserDetailsManager(users);
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		// Ao fazer login alterar senha para MD5 e compara com a senha na memoria
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.userDetailsService());
		authProvider.setPasswordEncoder(new Md5PasswordEncoder());
		return authProvider;
	}
}