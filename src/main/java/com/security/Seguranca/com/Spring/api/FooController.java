package com.security.Seguranca.com.Spring.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

	@GetMapping("/public")
	public ResponseEntity<String> publicRoute(){
		return ResponseEntity.ok("Public route OK");
	}
	
	@GetMapping("/private")
	public ResponseEntity<String> privateRoute(Authentication authentication){
		return ResponseEntity.ok("Private route OK | Usuário Logado: " + authentication.getName());
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> adminRoute(){
		return ResponseEntity.ok("Admin route OK");
	}
}
