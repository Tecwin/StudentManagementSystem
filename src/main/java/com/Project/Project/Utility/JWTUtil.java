package com.Project.Project.Utility;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${app.secret}")
	private String secret;
	
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuer("rod")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret.getBytes()))
				.compact();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encode(secret.getBytes()))
				.parseClaimsJws(token)
				.getBody();
	}
	
	public Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}
	
	public String getUsername(String token) {
		return getClaims(token).getSubject();
		}
	
	public boolean isTokenExp(String token) {
		Date expiryDate=getExpirationDate(token);
		return expiryDate.after(new Date(System.currentTimeMillis()));
	}
	public boolean isTokenValid(String token, String username) {
		String tokenUsernameString=getUsername(token);
		return (tokenUsernameString.equals(username)&&isTokenExp(token));
		
	}
}
