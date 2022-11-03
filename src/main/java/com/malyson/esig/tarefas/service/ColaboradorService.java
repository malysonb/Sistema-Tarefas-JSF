package com.malyson.esig.tarefas.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.repository.Colaboradores;
import com.malyson.esig.tarefas.util.Transactional;

public class ColaboradorService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Colaboradores colabs;
	
	@Transactional
	public List<Colaborador> getAll(){
		return colabs.getAll();
	}
	
	@Transactional
	public Colaborador getById(Long id) {
		return id != null ? colabs.getById(id) : null;
	}
	
}
