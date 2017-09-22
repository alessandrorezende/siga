package com.siga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.siga.model.Instituicao;

public interface Instituicoes extends JpaRepository<Instituicao,Long> {

	Instituicao findByNome(String nome);
}
