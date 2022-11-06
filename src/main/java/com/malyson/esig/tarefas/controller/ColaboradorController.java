package com.malyson.esig.tarefas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.malyson.esig.tarefas.dto.ColabPesquisaDTO;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.service.ColaboradorService;

@Named(value = "colabBean")
@ViewScoped
public class ColaboradorController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ColaboradorService colabService; // Serviços do repositório.
	
	private Colaborador colabP;
	
	private List<Colaborador> colabList;
	
	private ColabPesquisaDTO pesquisa = new ColabPesquisaDTO();
	
	@PostConstruct
	private void init() {
		pesquisa.setNome("");
		pesquisa.setSetor("");
		pesquisa.setTelefone("");
		colabList = colabService.getAll();
	}
	
	public void novoColab() {
		colabP = new Colaborador();
	}
	
	public void disporColab() {
		colabP = null;
	}
	
	public void salvar() {
		colabService.salvar(colabP);
		pesquisar();
	}
	
	public void pesquisar() {
		colabList = colabService.pesquisar(pesquisa);
	}
	
	public void deletar(Colaborador colab) {
		colabService.deletar(colab);
		pesquisar();
	}

	// Getter e Setters
	
	public Colaborador getColabP() {
		return colabP;
	}

	public void setColabP(Colaborador colabP) {
		this.colabP = colabP;
	}

	public ColaboradorService getColabService() {
		return colabService;
	}

	public List<Colaborador> getColabList() {
		return colabList;
	}

	public ColabPesquisaDTO getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(ColabPesquisaDTO pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	
}
