package com.malyson.esig.tarefas.dto;

import java.io.Serializable;

import com.malyson.esig.tarefas.model.Colaborador;

public class PesquisaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String descricao;
	
	private Colaborador idResp;
	
	private Boolean situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(Boolean situacao) {
		this.situacao = situacao;
	}

	public Colaborador getIdResp() {
		return idResp;
	}

	public void setIdResp(Colaborador idResp) {
		this.idResp = idResp;
	}
	
}
