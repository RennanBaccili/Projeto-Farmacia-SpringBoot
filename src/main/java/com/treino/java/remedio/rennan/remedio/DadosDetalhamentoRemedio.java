package com.treino.java.remedio.rennan.remedio;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(
		Long id,
		String nome,
		Via via,
		String lote,
		int quantidade,
		LocalDate validade,
		Boolean ativo) {

	public DadosDetalhamentoRemedio(Remedio remedio) {
		this(
				remedio.getId(),
				remedio.getNome(),
				remedio.getVia(),
				remedio.getLote(),
				remedio.getQuantidade(),
				remedio.getValidade(),
				remedio.getAtivo());
		
	}

}