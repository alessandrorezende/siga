package com.siga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siga.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {

	Usuario findByUsernameAndPassword(String username, String password);
}
