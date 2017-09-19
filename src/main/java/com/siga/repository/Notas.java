package com.siga.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.siga.model.Curso;
import com.siga.model.Nota;

public interface Notas extends JpaRepository<Nota,Long> {
	
	Nota findByCurso(Curso curso);
	@Query(" FROM Nota n WHERE  n.curso.instituicao.nome =  :instituicao")
	public List<Nota> findByQueryInstituicao(@Param("instituicao") String instituicao);
}
