package com.security.Seguranca.com.Spring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.Seguranca.com.Spring.domain.entity.Grupo;
import com.security.Seguranca.com.Spring.domain.entity.Usuario;
import com.security.Seguranca.com.Spring.domain.entity.UsuarioGrupo;
import com.security.Seguranca.com.Spring.domain.repository.GrupoRepository;
import com.security.Seguranca.com.Spring.domain.repository.UsuarioGrupoRepository;
import com.security.Seguranca.com.Spring.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final GrupoRepository grupoRepository;
	private final UsuarioGrupoRepository usuarioGrupoRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	public Usuario salvar(Usuario usuario, List<String> grupos) {
		String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		repository.save(usuario);
		
		List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
		Optional<Grupo>	possivelGrupo = grupoRepository.findByNome(nomeGrupo);
		if(possivelGrupo.isPresent()) {
			Grupo grupo =  possivelGrupo.get();
			return new UsuarioGrupo(usuario, grupo);
		}
		return null;
		}).filter(grupo -> grupo != null).collect(Collectors.toList());
		usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
		return usuario;
	}
	
	public Usuario obterUsuarioComPermissoes(String login) {
		Optional<Usuario> usuarioOptional = repository.findByLogin(login);
		if(usuarioOptional.isEmpty()) {
			return null;
		}
		
		Usuario usuario = usuarioOptional.get();
		List<String> permissoes = usuarioGrupoRepository.findByUsuario(usuario);
		usuario.setPermissoes(permissoes);
		
		return usuario;
	}
}
