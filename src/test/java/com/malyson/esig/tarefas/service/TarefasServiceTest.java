package com.malyson.esig.tarefas.service;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import com.malyson.esig.tarefas.dto.PesquisaDTO;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.model.Tarefa;
import com.malyson.esig.tarefas.repository.Tarefas;

@ExtendWith(MockitoExtension.class)
public class TarefasServiceTest {
	
	@Mock(name = "tarefas")
	private Tarefas tarefas;
	
	@Mock(name = "colServe")
	private ColaboradorService colServ;
	
	@InjectMocks
	private TarefasService tarefasService = new TarefasService();
	
	@Test
	public void DeveRetornarListaQuandoPesquisar() {
		Colaborador colab = new Colaborador();
		PesquisaDTO dto = new PesquisaDTO();
		dto.setId(null);
		dto.setDescricao("Tarefa");
		dto.setIdResp(1L);
		dto.setSituacao(false);
		when(colServ.getById(eq(1L))).thenReturn(colab);
		when(tarefas.buscar(eq(null), eq("Tarefa"), eq(colab), eq(false))).thenReturn(new ArrayList<Tarefa>());
		Assertions.assertNotNull(tarefasService.pesquisar(dto));
	}
	
}
