package com.miinaroland;

import com.miinaroland.Service.CurrentUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SubjectsApplication extends WebMvcConfigurerAdapter {

	@Autowired
	private CurrentUserDetailsService userDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(SubjectsApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/access").setViewName("access");
	}

	@Bean
	public ApplicationSecurity applicationSecurity() {
		return new ApplicationSecurity();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Configuration
	protected static class AuthenticationSecurity extends
			GlobalAuthenticationConfigurerAdapter {

		@Autowired
		private CurrentUserDetailsService users;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(users);
		}
	}

	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Autowired
		DaoAuthenticationProvider daoAuthenticationProvider;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
					.fullyAuthenticated().and().formLogin().loginPage("/login")
					.failureUrl("/login?error").and().logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and()
					.exceptionHandling().accessDeniedPage("/access?error");
			// @formatter:on
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(daoAuthenticationProvider);
		}

	}
}
