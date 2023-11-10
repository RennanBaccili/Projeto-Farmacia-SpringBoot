package com.treino.java.remedio.rennan.remedio.dto;

import java.time.LocalDate;

import com.treino.java.remedio.rennan.remedio.enums.Laboratorio;
import com.treino.java.remedio.rennan.remedio.enums.Via;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRemedio(
		@NotBlank
		String nome,
		@Enumerated
		Via via,
		@NotBlank
		String lote,
		int quantidade,
		@Future
		LocalDate validade,
		@Enumerated
		Laboratorio laboratorio
){


}
