package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecureConfig implements WebMvcConfigurer {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> {
			try {
				/*
				 * http.authorizeHttpRequests().antMatchers("/login").permitAll().antMatchers(
				 * "/index") .hasAnyAuthority("admin",
				 * "user").antMatchers("/registration").hasAnyAuthority("admin", "user")
				 * .antMatchers("/admin").hasAuthority("admin").anyRequest().authenticated().and
				 * ().csrf().disable() .formLogin().loginPage("/login")
				 * .failureUrl("/login?error=true").defaultSuccessUrl("/index").
				 * usernameParameter("email") .passwordParameter("password").and().logout()
				 * .logoutRequestMatcher(new
				 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
				 * .exceptionHandling().accessDeniedPage("/access-denied");
				 */
				http.authorizeHttpRequests().antMatchers("/login").permitAll().antMatchers("/index")
						.hasAnyAuthority("admin", "user").antMatchers("/registration").hasAnyAuthority("admin", "user")
						.antMatchers("/admin").hasAuthority("admin").anyRequest().authenticated().and().csrf().disable()
						.formLogin()/* .loginPage("/login") */
						.failureUrl("/login?error=true").defaultSuccessUrl("/index").and().logout()
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and()
						.exceptionHandling().accessDeniedPage("/access-denied");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).httpBasic();
		return http.build();

	}
}
