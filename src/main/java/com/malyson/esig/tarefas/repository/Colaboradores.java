package com.malyson.esig.tarefas.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.malyson.esig.tarefas.model.Colaborador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Colaboradores implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Colaboradores() {
		
	}
	
	public Colaboradores(EntityManager em) {
		this.em = em;
	}
	
	public List<Colaborador> getAll() {
		TypedQuery<Colaborador> query = em.createQuery("FROM Colaborador", Colaborador.class);
		if(query.getResultList().isEmpty())
			return new ArrayList<>();
		return query.getResultList();
	}
	
	public Colaborador getById(Long id) {
		return em.find(Colaborador.class, id);
	}
	
	public Colaborador salvar(Colaborador colaborador) {
		return em.merge(colaborador);
	}
	
	public void deletar(Colaborador colaborador) {
		colaborador = getById(colaborador.getId());
		em.remove(colaborador);
	}

}
