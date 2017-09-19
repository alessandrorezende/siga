package com.siga.resource;

public class ServiceException extends RuntimeException{

	public ServiceException(String exception){
		System.out.println(exception);
	}
}
