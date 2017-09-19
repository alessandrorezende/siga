package com.siga.dao;

public class DAOException extends RuntimeException{

	public DAOException(String exception){
		System.out.println(exception);
	}
}
