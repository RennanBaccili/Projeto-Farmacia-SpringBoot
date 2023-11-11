package com.treino.java.remedio.rennan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.treino.java.remedio.rennan.remedio.Remedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosAtualizarRemedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosCadastroRemedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosDetalhamentoRemedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosListagemRemedio;
import com.treino.java.remedio.rennan.remedio.repository.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired
	private RemedioRepository repository;
	
	
	//metodo cadastrar é responsável por lidar com a requisição do post
	@PostMapping
	@Transactional
    public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio cadastro, UriComponentsBuilder uriBuilder) {
    	var remedio = new Remedio(cadastro);
        repository.save(remedio);
        //gerar uriBuilder
        var uri = uriBuilder.path("/remedio/{id}").buildAndExpand(remedio.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }
	
	@GetMapping
	public ResponseEntity<List<DadosListagemRemedio>> listar (){	
		return ResponseEntity.ok(repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoRemedio> buscarId (@PathVariable Long id){	
		var remedio = repository.getReferenceById(id);
		
		return ResponseEntity.ok().body(new DadosDetalhamentoRemedio(remedio));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
	    var remedio = repository.getReferenceById(dados.id());
	    remedio.atualizarInforamcoes(dados);
	    
	    return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio)); // ou qualquer outra mensagem adequada
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		if(!repository.existsById(id)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<String> inativar(@PathVariable Long id) {
	    var optionalRemedio = repository.findById(id);

	    if (optionalRemedio.isPresent()) {
	        var remedio = optionalRemedio.get();
	        remedio.inativar();
	       
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Remédio não encontrado");
	    }
	}
	@PutMapping("ativar/{id}")
	@Transactional
	public ResponseEntity<String> ativar(@PathVariable Long id) {
	    var optionalRemedio = repository.findById(id);

	    if (optionalRemedio.isPresent()) {
	        var remedio = optionalRemedio.get();
	        remedio.ativar();
	       
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Remédio não encontrado");
	    }
	}
}
