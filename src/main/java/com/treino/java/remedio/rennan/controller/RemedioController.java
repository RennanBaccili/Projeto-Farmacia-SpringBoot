package com.treino.java.remedio.rennan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treino.java.remedio.rennan.remedio.Remedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosAtualizarRemedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosCadastroRemedio;
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
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroRemedio remedio, BindingResult bindingResult) {

        try {
            repository.save(new Remedio(remedio));
            return ResponseEntity.ok("Remédio cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar o remédio.");
        }
    }
	
	@GetMapping
	public ResponseEntity<List<DadosListagemRemedio>> listar (){	
		return ResponseEntity.ok(repository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList());
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<String> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        String mensagemErro = construirMensagemErro(bindingResult);
	        return ResponseEntity.badRequest().body(mensagemErro);
	    }
	    var remedio = repository.getReferenceById(dados.id());
	    remedio.atualizarInforamcoes(dados);
	    return ResponseEntity.ok("Atualização bem-sucedida"); // ou qualquer outra mensagem adequada
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		if(!repository.existsById(id)) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso não encontrado");
		}
		repository.deleteById(id);
		return ResponseEntity.ok("Exclusão bem-sucedida");
	}
	
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<String> inativar(@PathVariable Long id) {
	    var optionalRemedio = repository.findById(id);

	    if (optionalRemedio.isPresent()) {
	        var remedio = optionalRemedio.get();
	        remedio.inativar();
	       
	        return ResponseEntity.ok("Inativação bem-sucedida");
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
	       
	        return ResponseEntity.ok("Ativação bem-sucedida");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Remédio não encontrado");
	    }
	}
	
	
	private String construirMensagemErro(BindingResult bindingResult) {
	    var errors = new StringBuilder();
	    for (FieldError error : bindingResult.getFieldErrors()) {
	        errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(". ");
	    }
	    return errors.toString();
	}
}
