package com.treino.java.remedio.rennan.remedio;

import java.io.Serializable;
import java.time.LocalDate;

import com.treino.java.remedio.rennan.remedio.dto.DadosAtualizarRemedio;
import com.treino.java.remedio.rennan.remedio.dto.DadosCadastroRemedio;
import com.treino.java.remedio.rennan.remedio.enums.Laboratorio;
import com.treino.java.remedio.rennan.remedio.enums.Via;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Entity(name="remedio")
@Table(name="remedios")
@Getter
@Setter
public class Remedio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Via via;
	
	private String lote;
	private int quantidade;
	private LocalDate validade;
	
	@Enumerated(EnumType.STRING)
	private Laboratorio laboratorio;

	private Boolean ativo;
	
	public Remedio() {
	      
	  }

	public Remedio(DadosCadastroRemedio dados) {
		this.ativo = true;
		this.setNome(dados.nome());
		this.via=dados.via();
		this.setLote(dados.lote());
		this.setQuantidade(dados.quantidade());
		this.setValidade(dados.validade());
		this.laboratorio= dados.laboratorio();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}


	public void atualizarInforamcoes(@Valid DadosAtualizarRemedio dados) {
		if(dados.nome() != null  ) this.nome= dados.nome();
		
		if(dados.via() != null)  this.via= dados.via();
			
		if(dados.laboratorio() != null) this.laboratorio=dados.laboratorio();
	}

	public void inativar() {
		this.ativo=false;
	}

	public void ativar() {
		this.ativo=true;
	}




}
