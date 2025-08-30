package com.minihelpdesk.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long validityInMs = 3600000; // 1h

	public String generateToken(String username, String role) {
		return Jwts.builder().setSubject(username).claim("role", role).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + validityInMs)).signWith(key).compact();
	}

	public Jws<Claims> validateToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
	}

	public String getUsername(String token) {
		return validateToken(token).getBody().getSubject();
	}

	public String getRole(String token) {
		return validateToken(token).getBody().get("role", String.class);
	}
}
