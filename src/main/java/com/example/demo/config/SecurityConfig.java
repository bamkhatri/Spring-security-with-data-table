package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/registerUser/**").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/logout").permitAll()
			.antMatchers("/").hasAnyRole("USER","ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasRole("USER")
			
		//	.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/getloginpage").permitAll()
			.loginProcessingUrl("/dologin")
			.and()
			.exceptionHandling().accessDeniedPage("/access");
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
		
		/*.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("sanlama"))
				.roles("USER").and()
				.withUser("admin").password(new BCryptPasswordEncoder().encode("asmin")).roles("ADMIN");*/
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
