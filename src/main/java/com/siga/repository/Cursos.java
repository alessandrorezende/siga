package com.siga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.siga.model.Curso;
import com.siga.model.Instituicao;

public interface Cursos extends JpaRepository<Curso, Long> {

	List<Curso> findByInstituicao(Instituicao instituicao);

	@Query(" FROM Curso c WHERE c.vagas > 0")
	public List<Curso> findByQueryVagasOciosas();

}
