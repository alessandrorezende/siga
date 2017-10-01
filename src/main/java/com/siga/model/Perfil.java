package com.siga.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Table(name = "perfil")
@Entity
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Perfil implements Serializable {

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	private String nome;

	public Perfil(Long id, String nome) {
		this.setId(id);
		this.setName(nome);
	}

	public Perfil() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setName(String nome) {
		this.nome = nome;
	}
}
