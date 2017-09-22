package com.siga.rest;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.siga.controller.NotaController;

public class RequestHandler {
	
	public static final  String URI = "http://localhost:5000/cursos";
	private static final Logger log = LoggerFactory.getLogger(NotaController.class);
	
	public static HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	public static String getURI() {
		return URI;
	}

	
}
