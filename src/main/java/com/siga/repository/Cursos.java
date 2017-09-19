package com.siga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siga.model.Curso;
import com.siga.model.Instituicao;

public interface Cursos extends JpaRepository<Curso,Long> {

	List<Curso> findByInstituicao(Instituicao instituicao);

}
