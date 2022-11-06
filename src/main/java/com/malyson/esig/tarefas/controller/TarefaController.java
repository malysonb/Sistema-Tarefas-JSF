package com.malyson.esig.tarefas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	
	private Tarefa tarefa = null; // Objeto ponteiro para dialogos do view.
	
	@Inject
	private ColaboradorService colab; // Serviços do repositório.
	
	@Inject
	private TarefasService tarefasService; // Serviços do repositório.
	
	// Objeto de transferencia para pesquisa.
	private PesquisaDTO pesquisaDTO = new PesquisaDTO();
	
	// Lista de tarefas a ser exibido no view.
	private List<Tarefa> tarefaList = new ArrayList<>();
	
	// id do colaborador responsável pela tarefa.
	private Long idResp;
	
	/**
	 * Metodo reproduzido ao inicio do escopo logo após o construtor.
	 * preenche inicialmente a lista de tarefas.
	 */
	@PostConstruct
	private void init() {
		pesquisaDTO.setSituacao(false);
		pesquisaDTO.setDescricao("");
		pesquisar();
	}
	
	/**
	 * Prepara uma nova tarefa à ser adicionada.
	 */
	public void novaTarefa() {
		tarefa = new Tarefa();
	}
	
	/**
	 * Edita uma tarefa
	 * @param idS id da tarefa em string.
	 */
	public void editarTarefa(String idS) {
		Long id = Long.valueOf(idS);
		tarefa = tarefasService.getId(id);
	}
	
	/**
	 * Torna nulo a tarefa ponteiro da view.
	 */
	public void disporTarefa() {
		tarefa = null;
	}

	/**
	 * Cria ou edita uma tarefa no banco de dados.
	 */
	public void salvar() {
		
		tarefa.setResponsavel(colab.getById(idResp));
		tarefa.setSituacao(false);
		tarefasService.salvar(tarefa);
		pesquisar();
	}
	
	/**
	 * Deleta uma tarefa do banco de dados.
	 * @param tarefa Objeto tarefa.
	 */
	public void excluir(Tarefa tarefa){
		tarefasService.deletar(tarefa);
		pesquisar();
	}
	
	/**
	 * Alterna a conclusão de uma tarefa.
	 * @param tarefa Objeto tarefa.
	 */
	public void concluir(Tarefa tarefa){
		tarefa.setSituacao(!tarefa.getSituacao());
		tarefasService.salvar(tarefa);
		pesquisar();
	}
	
	/**
	 * atualiza a lista de tarefas com as condições desejadas
	 */
	public void pesquisar() {
		tarefaList = tarefasService.pesquisar(pesquisaDTO);
	}
	
	// Getters e Setters
	
	public Prioridade[] getPrioridadesEnum() {
		return Prioridade.values();
	}
	
	public List<Colaborador> getColaboradoresList() {
		List<Colaborador> colabs = colab.getAll(); 
		return colabs;
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
}
