package com.malyson.esig.tarefas.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.malyson.esig.tarefas.model.Colaborador;
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
	
	public List<Tarefa> getAll() {
		TypedQuery<Tarefa> query = em.createQuery("FROM Tarefa", Tarefa.class);
		return query.getResultList();
	}
	
	public List<Tarefa> buscar(Long id, String desc, Colaborador idResp, Boolean situacao) {
		String queryString = "FROM Tarefa WHERE (:id is null or id = :id) " +
							 "AND (:desc is null or (UPPER(titulo) like UPPER(:desc) OR UPPER(descricao) like UPPER(:desc))) "+
							 "AND (:idResp is null or responsavel = :idResp) "+
							 "AND (:situacao is null or situacao = :situacao)";
		TypedQuery<Tarefa> query = em.createQuery(queryString, Tarefa.class);
		query.setParameter("id", id);
		query.setParameter("desc", "%" + desc + "%");
		query.setParameter("idResp", idResp);
		query.setParameter("situacao", situacao);
		return query.getResultList();
	}
	
	public Tarefa salvar(Tarefa tarefa) {
		return em.merge(tarefa);
	}
	
	public void deletar(Tarefa tarefa) {
		tarefa = getById(tarefa.getId()); // Pegando instancia real
		em.remove(tarefa);
	}
}
