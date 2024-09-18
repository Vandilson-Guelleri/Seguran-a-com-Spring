package com.security.Seguranca.com.Spring.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.Seguranca.com.Spring.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String login);
}
