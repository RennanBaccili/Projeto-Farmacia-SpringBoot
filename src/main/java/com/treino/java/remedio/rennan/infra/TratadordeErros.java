package com.treino.java.remedio.rennan.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

//essa classe permite resolver todos os exceptions de Rest

@RestControllerAdvice
public class TratadordeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratador404(){
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratador400(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors(); // variavel com todos os campos que deram erros 
		

		return ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList());
	}
	//DTO
	public record DadosErros(String campo, String mensagem) {
		public DadosErros(FieldError error) {
			this(error.getField(),error.getDefaultMessage());
		}
	}
}
