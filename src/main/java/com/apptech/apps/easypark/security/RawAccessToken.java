package com.apptech.apps.easypark.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.security.authentication.BadCredentialsException;

import com.apptech.apps.easypark.exceptions.JwtExpiredTokenException;

public class RawAccessToken implements JWTToken {

	private String token;
	
	public RawAccessToken(String token) {
		this.token=token;
	}
	
	public Jws<Claims>  parseClaims(String signingKey){
		try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpiredTokenException(this, "JWT Token expired", expiredEx);
        }
	}
	
	@Override
	public String getToken() {
		return token;
	}

	
}
