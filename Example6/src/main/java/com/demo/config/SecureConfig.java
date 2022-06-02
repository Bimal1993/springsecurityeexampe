package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.service.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecureConfig implements WebMvcConfigurer {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().antMatchers("/index")
				.hasAnyAuthority("admin", "user").antMatchers("/registration").hasAnyAuthority("admin", "user")
				.antMatchers("/admin").hasAuthority("admin").anyRequest().authenticated().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).and().exceptionHandling()
				.accessDeniedPage("/access-denied").and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.httpBasic().and().build();
	}
}
