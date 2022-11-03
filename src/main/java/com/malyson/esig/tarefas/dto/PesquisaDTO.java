package com.malyson.esig.tarefas.dto;

import java.io.Serializable;

public class PesquisaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String descricao;
	
	private Long idResp;
	
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

	public Long getIdResp() {
		return idResp;
	}

	public void setIdResp(Long idResp) {
		this.idResp = idResp;
	}
	
}
