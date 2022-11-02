package com.malyson.esig.tarefas.repository;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class GerarSchemas {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TarefasPU");
		emf.close();
	}

}
