package com.siga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siga.model.Perfil;

public interface Perfis extends JpaRepository<Perfil, Long> {

	Perfil findByNome(String nome);

}
