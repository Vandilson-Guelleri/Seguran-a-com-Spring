package com.security.Seguranca.com.Spring.Configuration;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.security.Seguranca.com.Spring.domain.entity.Usuario;
import com.security.Seguranca.com.Spring.security.CustomAuthentication;
import com.security.Seguranca.com.Spring.security.IdentificacaoUsuario;
import com.security.Seguranca.com.Spring.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{

	//injeção de dependencia pelo construtor, se utilizo final não precisa colocar @Autowired
	private final UsuarioService usuarioService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String login = authentication.getName();
		String senha = (String) authentication.getCredentials();
		
		Usuario usuario = usuarioService.obterUsuarioComPermissoes(login);
		if (usuario != null) {
			boolean senhasBatem = passwordEncoder.matches(senha, usuario.getSenha());
			if (senhasBatem) {
				IdentificacaoUsuario identificacaoUsuario = new IdentificacaoUsuario(
					usuario.getId(),
					usuario.getNome(),
					usuario.getLogin(),
					usuario.getPermissoes()
				);
				return new CustomAuthentication(identificacaoUsuario);
			}
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
