package com.treino.java.remedio.rennan.remedio.dto;

import com.treino.java.remedio.rennan.remedio.enums.Laboratorio;
import com.treino.java.remedio.rennan.remedio.enums.Via;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRemedio(
		@NotNull Long id,
		String nome,
		Via via,
		Laboratorio laboratorio) {

}
