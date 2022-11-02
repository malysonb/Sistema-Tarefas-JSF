package com.malyson.esig.tarefas.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.malyson.esig.tarefas.enums.Prioridade;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.model.Tarefa;
import com.malyson.esig.tarefas.repository.Tarefas;
import com.malyson.esig.tarefas.service.ColaboradorService;

@Named(value = "taBean")
@ViewScoped
public class TarefaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa = new Tarefa();
	
	@Inject
	private ColaboradorService colab;
	
	@Inject
	private Tarefas tarefas;
	
	private Long idResp;
	
	public Long getIdResp() {
		return idResp;
	}


	public void setIdResp(Long idResp) {
		this.idResp = idResp;
	}


	public Tarefa getTarefa() {
		return tarefa;
	}
	
	
	public Prioridade[] getPrioridadesEnum() {
		System.out.println("pegando enums");
		return Prioridade.values();
	}
	
	public List<Colaborador> getColaboradoresList() {
		List<Colaborador> colabs = colab.getAll(); 
		return colabs;
	}

	public void salvar() {
		tarefa.setResponsavel(colab.getById(idResp));
		System.out.println("Aqui seu animal: " + tarefa.getTitulo() + " - " + tarefa.getResponsavel().getNome());
	}


	public ColaboradorService getColab() {
		return colab;
	}
}
