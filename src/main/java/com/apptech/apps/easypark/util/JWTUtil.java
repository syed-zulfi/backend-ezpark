package com.apptech.apps.easypark.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.apptech.apps.easypark.constants.JWTClaimField;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.security.JWTAuthSettings;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JWTUtil {

	public static User extractUserCredentials(HttpServletRequest req)
			throws JsonParseException, JsonMappingException, IOException {
		User creds = new ObjectMapper().readValue(req.getInputStream(),
				User.class);
		return creds;
	}

	public static String generateToken(JWTAuthSettings settings,
			Map<String, String> userDetails) {
		final long EXPIRATION_TIME = Long.parseLong(settings.getToken()
				.getExpirytime());

		String encodeSign = TextCodec.BASE64.encode(settings.getToken()
				.getSignature().trim());
		String ezpToken = Jwts
				.builder()
				.setClaims(createClaims(userDetails))
				.setSubject(userDetails.get(JWTClaimField.USERNAME.val()))
				.setExpiration(
						new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, encodeSign).compact();
		return ezpToken;
	}

	public static Claims createClaims(Map<String, String> publicClaims){
		Claims claims = Jwts.claims();
		claims.put(JWTClaimField.ID.val(), publicClaims.get(JWTClaimField.ID.val()));
		claims.put(JWTClaimField.NAME.val(), publicClaims.get(JWTClaimField.NAME.val()));
		claims.put(JWTClaimField.EMAIL.val(), publicClaims.get(JWTClaimField.EMAIL.val()));
		claims.put(JWTClaimField.ROLE.val(), publicClaims.get(JWTClaimField.ROLE.val()));
		return claims;
	}

}
