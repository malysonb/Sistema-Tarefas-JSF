package com.malyson.esig.tarefas.util;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		HashMap<String,String> props=new HashMap<>();
		props.put("jakarta.persistence.jdbc.url", System.getenv("JDBC_DATABASE_URL"));
		props.put("jakarta.persistence.jdbc.user", System.getenv("JDBC_DATABASE_USERNAME"));
		props.put("jakarta.persistence.jdbc.password", System.getenv("JDBC_DATABASE_PASSWORD"));
		System.out.println(System.getenv("JDBC_DATABASE_URL"));
		this.factory = Persistence.createEntityManagerFactory("TarefasPU", props);
	}
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager em) {
		em.close();
	}
	
}
