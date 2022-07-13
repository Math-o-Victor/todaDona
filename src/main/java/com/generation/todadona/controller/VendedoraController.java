package com.generation.todadona.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.todadona.model.VendedoraLogin;
import com.generation.todadona.model.VendedoraModel;
import com.generation.todadona.service.VendedoraService;

@RestController
@RequestMapping("/vendedora")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendedoraController {
	
	@Autowired
	private VendedoraService vendedoraService;
	
	@PostMapping("/logar")
	public ResponseEntity<VendedoraLogin> autentication(@RequestBody Optional<VendedoraLogin> cpf){
		return vendedoraService.logarUsuario(cpf)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Optional<VendedoraModel>> cadastro(@RequestBody VendedoraModel vendedora){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(vendedoraService.cadastrarUsuario(vendedora));
	}
	

}
