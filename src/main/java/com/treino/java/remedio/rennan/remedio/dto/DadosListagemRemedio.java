package com.treino.java.remedio.rennan.remedio.dto;

import java.time.LocalDate;

import com.treino.java.remedio.rennan.remedio.Remedio;
import com.treino.java.remedio.rennan.remedio.enums.Laboratorio;
import com.treino.java.remedio.rennan.remedio.enums.Via;

public record DadosListagemRemedio(
		Long id,
		String nome,
		Via via,
		String lote,
		LocalDate validade,
		Laboratorio laboratorio){

	public DadosListagemRemedio(Remedio remedio) {
		this(remedio.getId(), remedio.getNome(),remedio.getVia(),remedio.getLote(),remedio.getValidade(),remedio.getLaboratorio());
	}

}
