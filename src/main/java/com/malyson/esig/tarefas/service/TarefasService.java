package com.malyson.esig.tarefas.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.malyson.esig.tarefas.dto.PesquisaDTO;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.model.Tarefa;
import com.malyson.esig.tarefas.repository.Tarefas;
import com.malyson.esig.tarefas.util.Transactional;

public class TarefasService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Tarefas tarefas;
	
	@Inject
	private ColaboradorService colServ;
	
	@Transactional
	public void salvar(Tarefa tarefa) {
		tarefas.salvar(tarefa);
	}
	
	@Transactional
	public void deletar(Tarefa tarefa) {
		tarefas.deletar(tarefa);
	}
	
	@Transactional
	public List<Tarefa> getAll() {
		return tarefas.getAll();
	}
	
	@Transactional
	public Tarefa getId(Long id) {
		return tarefas.getById(id);
	}
	
	@Transactional
	public List<Tarefa> pesquisar(PesquisaDTO dto){
		Colaborador col = dto.getIdResp() != null ? colServ.getById(dto.getIdResp()) : null;
		return tarefas.buscar(dto.getId(), dto.getDescricao(), col, dto.getSituacao());
	}
}
