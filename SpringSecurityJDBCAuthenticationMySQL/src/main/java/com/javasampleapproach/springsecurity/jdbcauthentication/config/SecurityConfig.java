package com.javasampleapproach.springsecurity.jdbcauthentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	DataSource dataSource;
 
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from userssss where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/home").permitAll().antMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
	public void method() {
		
		
	}
	
	public void secondmethod(){
	
	}
}

// Used URL: https://docs.spring.io/autorepo/docs/spring-security-javaconfig/1.0.0.CI-SNAPSHOT/api-reference/org/springframework/security/config/annotation/provisioning/JdbcUserDetailsManagerConfigurator.html
// usersByUsernameQuery(java.lang.String query) ---- Sets the query to be used for finding a user by their username.
// authoritiesByUsernameQuery(java.lang.String query) ---- Sets the query to be used for finding a user's authorities by their username.

