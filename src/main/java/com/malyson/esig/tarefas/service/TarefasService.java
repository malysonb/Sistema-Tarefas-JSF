package com.malyson.esig.tarefas.service;

import java.io.Serializable;

import javax.inject.Inject;

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
	
	@Transactional
	public void salvar(Tarefa tarefa) {
		tarefas.salvar(tarefa);
	}
	
	@Transactional
	public void deletar(Tarefa tarefa) {
		tarefas.deletar(tarefa);
	}
}
