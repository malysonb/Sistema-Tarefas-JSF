package com.malyson.esig.tarefas.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.malyson.esig.tarefas.model.Colaborador;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		return query.getResultList();
	}
	
	public List<Colaborador> pesquisar(Long id, String nome, String setor){
		String queryString = "FROM Colaborador WHERE (:id is null or id = :id) "+
							 "AND (:nome is null or UPPER(nome) like UPPER(:nome) ) "+
							 "AND (:setor is null or UPPER(setor) like UPPER(:setor))";
		TypedQuery<Colaborador> query = em.createQuery(queryString, Colaborador.class);
		query.setParameter("id", id);
		query.setParameter("nome", "%" + nome + "%");
		query.setParameter("setor", "%" + setor + "%");
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
