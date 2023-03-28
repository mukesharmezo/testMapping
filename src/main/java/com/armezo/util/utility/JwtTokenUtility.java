package com.armezo.util.utility;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtility implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final long TOKEN_VALIDITY=10*60*1000;   // minutes * second * milliseconds
	
	@Value("${jwt.secret}")
	private String secret;
	
	//Get Claims
	public Claims getClaimsFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
				.parseClaimsJws(token)
				.getBody();
				//.parseClaimsJws(token.replace("Bearer ", ""));
	}
	
	//Get Username From jwt token
	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token).getSubject();
	}
	// Get Expiration Date
	public Date getExpirationDateFromToken(String token) {
		return getClaimsFromToken(token).getExpiration();
	}
	//Check Token is Expired or not
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	//Generate Token
	  public String generateToken(UserDetails user) {
	    String jwtToken="";
	    jwtToken = Jwts.builder()
	    		.setSubject(user.getUsername())
	    		.setIssuer("Armezo Solutions")
	    		.setIssuedAt(new Date())
	    		.setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY))
	    		.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
	    		.compact();
	    return jwtToken;
	  }
	  //Check token validity
	  public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
