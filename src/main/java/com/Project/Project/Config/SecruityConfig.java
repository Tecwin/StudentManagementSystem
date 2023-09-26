package com.Project.Project.Config;

import static //
org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Project.Project.Filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecruityConfig {
	
	public static final String[] publicURL= {"/user/login","/student/save","/professor/save","/"};
	public static final String[] SWAGGERURL= {
			 										"/swagger-ui/**",
			/*
													  "/swagger-ui/index.html",													  
													  "/swagger-ui/swagger-ui.css",
													  "/swagger-ui/index.css",
													  "/swagger-ui/swagger-ui-bundle.js",
													  "/swagger-ui/swagger-ui-standalone-preset.js",
													  "/swagger-ui/swagger-initializer.js",
													  "/swagger-ui/favicon-32x32.png",
													  "/swagger-ui/favicon-16x16.png",
													  */
													  "/v3/api-docs/swagger-config",
													  "/v3/api-docs"
													  
													  };
	public static final String[] ADMINURL= {"/professor/delete/{id}"};
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private InvalidUserAuthEntryPoint invalidUserAuthEntryPoint;
	
	@Autowired
	private SecurityFilter securityFilter;
	

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

	 
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http
	      .cors().and()
	      .csrf().disable()
	      .headers().frameOptions().disable()
	      .and()
	      .authorizeHttpRequests()
	      .requestMatchers(toH2Console()).permitAll()
	      .requestMatchers(publicURL).permitAll()  
	      .requestMatchers(SWAGGERURL).permitAll()
	      .requestMatchers(ADMINURL).hasRole("ADMIN")
	      .requestMatchers("/professor/saveCourse").hasRole("Professor")
	      .requestMatchers("/user/save").hasAuthority("ROLE_ADMIN")
	      .anyRequest().authenticated()
	      .and()
	      .exceptionHandling()
	      .authenticationEntryPoint(invalidUserAuthEntryPoint)
	      .and()
	      .sessionManagement()
	      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and()
	      .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	      System.out.println("###^###");	
	      
	        return http.build();
	    }

	
}
