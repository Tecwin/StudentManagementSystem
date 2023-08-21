package com.Project.Project.Filter;

import java.io.IOException;
import java.util.Collection;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Project.Project.POJO.Professor;
import com.Project.Project.Service.impl.UserServiceImpl;
import com.Project.Project.Utility.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token=request.getHeader("Authorization");
		try {
		if(token!=null) {
			String jwt = token.substring(7);
			String username=jwtUtil.getUsername(jwt);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails=userServiceImpl.loadUserByUsername(username);
				
				boolean isValid=jwtUtil.isTokenValid(jwt, username);
				if(isValid) {
					Collection<GrantedAuthority> temp=(Collection<GrantedAuthority>) userDetails.getAuthorities();
					UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(),userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				
				}
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		filterChain.doFilter(request, response);
		
	}

}
