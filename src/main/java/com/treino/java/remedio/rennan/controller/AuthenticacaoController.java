 package com.treino.java.remedio.rennan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treino.java.remedio.rennan.usuarios.DadosAutenticacao;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticacaoController {

	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
		
		var token = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
		// passo login e senha para criar o token
		// e verifico se o token está autenticado
		
		var autenticacao = manager.authenticate(token);
		return ResponseEntity.ok().build();
	}
}
