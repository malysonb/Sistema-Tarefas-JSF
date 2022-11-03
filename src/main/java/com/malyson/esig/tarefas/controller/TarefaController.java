package com.malyson.esig.tarefas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.malyson.esig.tarefas.dto.PesquisaDTO;
import com.malyson.esig.tarefas.enums.Prioridade;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.model.Tarefa;
import com.malyson.esig.tarefas.service.ColaboradorService;
import com.malyson.esig.tarefas.service.TarefasService;

@Named(value = "taBean")
@ViewScoped
public class TarefaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa = null;
	
	
	@Inject
	private ColaboradorService colab;
	
	@Inject
	private TarefasService tarefasService;
	
	private PesquisaDTO pesquisaDTO = new PesquisaDTO();
	
	private List<Tarefa> tarefaList = new ArrayList<>();
	
	private Long idResp;
	
	@PostConstruct
	private void init() {
		tarefaList = tarefasService.getAll();
	}
	
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
	
	public void novaTarefa() {
		tarefa = new Tarefa();
	}
	
	public void editarTarefa(String idS) {
		Long id = Long.valueOf(idS);
		tarefa = tarefasService.getId(id);
	}
	
	public void disporTarefa() {
		tarefa = null;
	}

	public String salvar() {
		tarefa.setResponsavel(colab.getById(idResp));
		tarefa.setSituacao(false);
		tarefasService.salvar(tarefa);
		return "ListTarefa?faces-redirect=true";
	}
	
	public void excluir(Tarefa tarefa){
		tarefasService.deletar(tarefa);
		pesquisar();
	}
	
	public void concluir(Tarefa tarefa){
		tarefa.setSituacao(!tarefa.getSituacao());
		tarefasService.salvar(tarefa);
		pesquisar();
	}
	
	public void pesquisar() {
		tarefaList = tarefasService.pesquisar(pesquisaDTO);
		tarefaList.forEach(c -> System.out.println(c.getTitulo()));
	}

	public ColaboradorService getColab() {
		return colab;
	}
	
	public TarefasService getTarefasService() {
		return tarefasService;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}
