package com.security.Seguranca.com.Spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.Seguranca.com.Spring.api.dto.CadastroUsuarioDTO;
import com.security.Seguranca.com.Spring.domain.entity.Usuario;
import com.security.Seguranca.com.Spring.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO body){
		Usuario usuarioSalvo = usuarioService.salvar(body.getUsuario(), body.getPermissoes());
		return ResponseEntity.ok(usuarioSalvo);
	}
}
