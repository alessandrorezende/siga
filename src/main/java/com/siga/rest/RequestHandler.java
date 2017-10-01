package com.siga.rest;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class RequestHandler {

	//public static final String URI = "http://localhost:5000";
	public static final String URI = "https://mec-ws.herokuapp.com";
	

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

	public static String getURINotas() {
		return URI + "/notaService";
	}

	public static String getURIAprovados() {
		return URI + "/aprovadoService";
	}

	public static String getURIVagasOciosas() {
		return URI + "/vagaOciosaService";
	}

}
