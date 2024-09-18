package com.security.Seguranca.com.Spring.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.Seguranca.com.Spring.domain.entity.Grupo;
import com.security.Seguranca.com.Spring.domain.repository.GrupoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

	private final GrupoRepository repository;

	@PostMapping
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo) {
		repository.save(grupo);
		return ResponseEntity.ok(grupo);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Grupo>> listar(){
		return ResponseEntity.ok(repository.findAll());
	
	}
}
