package com.security.Seguranca.com.Spring.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.Seguranca.com.Spring.domain.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String>{

	Optional<Grupo> findByNome(String nome);
}
