package com.malyson.esig.tarefas.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.malyson.esig.tarefas.dto.PesquisaDTO;
import com.malyson.esig.tarefas.enums.Prioridade;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.model.Tarefa;
import com.malyson.esig.tarefas.repository.Tarefas;
import com.malyson.esig.tarefas.service.ColaboradorService;
import com.malyson.esig.tarefas.service.TarefasService;

@Named(value = "taBean")
@ViewScoped
public class TarefaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa = new Tarefa();
	
	private PesquisaDTO pesquisaDTO = new PesquisaDTO();
	
	@Inject
	private ColaboradorService colab;
	
	@Inject
	private TarefasService tarefasService;
	
	private List<Tarefa> tarefaList;
	
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
	
	public List<Tarefa> getTarefaList() {
		return tarefaList;
	}
	
	public PesquisaDTO getPesquisaDTO() {
		return pesquisaDTO;
	}
	
	
	public Prioridade[] getPrioridadesEnum() {
		System.out.println("pegando enums");
		return Prioridade.values();
	}
	
	public List<Colaborador> getColaboradoresList() {
		List<Colaborador> colabs = colab.getAll(); 
		return colabs;
	}

	public String salvar() {
		tarefa.setResponsavel(colab.getById(idResp));
		tarefa.setSituacao(false);
		tarefasService.salvar(tarefa);
		return "ListTarefa?faces-redirect=true";
	}
	
	public void pesquisar() {
		tarefaList = tarefasService.pesquisar(pesquisaDTO);
		System.out.println(tarefaList);
	}

	public ColaboradorService getColab() {
		return colab;
	}
	
	public TarefasService getTarefasService() {
		return tarefasService;
	}
}
