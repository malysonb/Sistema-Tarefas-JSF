package com.malyson.esig.tarefas.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		if(query.getResultList().isEmpty())
			return new ArrayList<>();
		return query.getResultList();
	}
	
	public List<Tarefa> buscar(Long id, String desc, Colaborador idResp, Boolean situacao) {
		String queryString = "FROM Tarefa WHERE ";
		int params = 0;
		if(id != null) {
			queryString += "id = :id ";
			params++;
		}
		if(!desc.isBlank()) {
			queryString += params > 0 ? "AND " : "";
			queryString += "(UPPER(titulo) like UPPER(:desc) OR UPPER(descricao) like UPPER(:desc)) ";
			params++;
		}
		if(idResp != null) {
			queryString += params > 0 ? "AND " : "";
			queryString += "responsavel = :idResp ";
			params++;
	
		}
		if(situacao != null) {
			queryString += params > 0 ? "AND " : "";
			queryString += "situacao = :situacao ";
			params++;
	
		}
		TypedQuery<Tarefa> query = em.createQuery(queryString, Tarefa.class);
		if(id != null) query.setParameter("id", id);
		if(!desc.isBlank()) query.setParameter("desc", "%" + desc + "%");
		if(idResp != null) query.setParameter("idResp", idResp);
		if(situacao != null) query.setParameter("situacao", situacao);
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
