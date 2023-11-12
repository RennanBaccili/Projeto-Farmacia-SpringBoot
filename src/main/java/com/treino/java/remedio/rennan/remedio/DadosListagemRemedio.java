package com.treino.java.remedio.rennan.remedio;

import java.time.LocalDate;

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
