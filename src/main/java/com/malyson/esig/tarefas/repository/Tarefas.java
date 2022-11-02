package com.malyson.esig.tarefas.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.malyson.esig.tarefas.model.Tarefa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class Tarefas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public Tarefas() {
		
	}
	
	public Tarefas(EntityManager em) {
		this.em = em;
	}
	
	public Tarefa getById(Long id) {
		return em.find(Tarefa.class, id);
	}
	
	public List<Tarefa> buscar(Long id, String desc, Long idResp, Boolean situacao) {
		TypedQuery<Tarefa> query = em.createQuery("from Tarefas where id like :id OR titulo like :desc OR descricao like :desc OR " +
									 "responsavel.id = :idResp OR situacao = :situacao", Tarefa.class);
		query.setParameter("id", id);
		query.setParameter("desc", desc + "%");
		query.setParameter("idResp", idResp);
		query.setParameter("situacao", situacao);
		return query.getResultList();
	}
	
	public Tarefa salvar(Tarefa tarefa) {
		return em.merge(tarefa);
	}
	
	public void deletar(Tarefa tarefa) {
		tarefa = getById(tarefa.getId());
		em.remove(tarefa);
	}
}
