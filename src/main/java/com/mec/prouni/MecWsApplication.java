package com.mec.prouni;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
public class MecWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MecWsApplication.class, args);
	}
	
}
