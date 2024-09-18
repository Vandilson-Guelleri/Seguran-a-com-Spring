package com.security.Seguranca.com.Spring.api.dto;

import java.util.List;

import com.security.Seguranca.com.Spring.domain.entity.Usuario;

import lombok.Data;

@Data
public class CadastroUsuarioDTO {

	private Usuario usuario;
	private List<String> permissoes;
}
