package com.apptech.apps.easypark.security.factory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.security.config.TKNClaims;
import com.apptech.apps.easypark.security.config.Settings;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class SecurityUtil {

	public static User extractUserCredentials(HttpServletRequest req)
			throws JsonParseException, JsonMappingException, IOException {
		User creds = new ObjectMapper().readValue(req.getInputStream(),
				User.class);
		return creds;
	}

	public static String generateToken(Settings settings,
			Map<String, String> userDetails) {
		final long EXPIRATION_TIME = Long.parseLong(settings.getToken()
				.getExpirytime());

		String encodeSign = TextCodec.BASE64.encode(settings.getToken()
				.getSignature().trim());
		String ezpToken = Jwts
				.builder()
				.setClaims(createClaims(userDetails))
				.setSubject(userDetails.get(TKNClaims.USERNAME.val()))
				.setExpiration(
						new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, encodeSign).compact();
		return ezpToken;
	}

	public static Claims createClaims(Map<String, String> publicClaims){
		Claims claims = Jwts.claims();
		claims.put(TKNClaims.ID.val(), publicClaims.get(TKNClaims.ID.val()));
		claims.put(TKNClaims.NAME.val(), publicClaims.get(TKNClaims.NAME.val()));
		claims.put(TKNClaims.EMAIL.val(), publicClaims.get(TKNClaims.EMAIL.val()));
		claims.put(TKNClaims.ROLE.val(), publicClaims.get(TKNClaims.ROLE.val()));
		return claims;
	}

}
