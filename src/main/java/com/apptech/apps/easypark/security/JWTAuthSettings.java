package com.apptech.apps.easypark.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
public class JWTAuthSettings {
     private Token token;
     
     
	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public static class Token{
		private String signature;
		private String prefixlabel;
		private String headerlabel;
		private String expirytime;
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getPrefixlabel() {
			return prefixlabel;
		}
		public void setPrefixlabel(String prefixlabel) {
			this.prefixlabel = prefixlabel;
		}
		public String getHeaderlabel() {
			return headerlabel;
		}
		public void setHeaderlabel(String headerlabel) {
			this.headerlabel = headerlabel;
		}
		
		public String getExpirytime() {
			return expirytime;
		}
		public void setExpirytime(String expirytime) {
			this.expirytime = expirytime;
		}
		@Override
		public String toString() {
			return "Token [signature=" + signature + ", prefixlabel="
					+ prefixlabel + ", headerlabel=" + headerlabel
					+ ", expirytime=" + expirytime + "]";
		}
	}

	@Override
	public String toString() {
		return "JWTAuthSettings [token=" + token.toString() + "]";
	}
	
	

}
