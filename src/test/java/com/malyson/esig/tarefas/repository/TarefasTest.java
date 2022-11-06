package com.malyson.esig.tarefas.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class TarefasTest {

	@Mock
	private EntityManager em;
	
	@InjectMocks
	private Tarefas tarefas;
	
	
	
}
