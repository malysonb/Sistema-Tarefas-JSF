package com.malyson.esig.tarefas.controller;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.model.Tarefa;
import com.malyson.esig.tarefas.service.ColaboradorService;
import com.malyson.esig.tarefas.service.TarefasService;

@ExtendWith(MockitoExtension.class)
public class TarefaControllerTest {

	@Mock
	private ColaboradorService colab;
	
	@Mock
	private TarefasService tarefasService;
	
	@InjectMocks
	private TarefaController tarefaController;
	
	@Test
	@DisplayName("Deve criar uma nova tarefa")
	public void DeveCriarNovaTarefa() {
		Assertions.assertNull(tarefaController.getTarefa());
		tarefaController.novaTarefa();
		Assertions.assertNotNull(tarefaController.getTarefa());
	}
	
	@Test
	@DisplayName("Tornar null tarefa ao dispor da pagina")
	public void DeveTornarNullTarefa() {
		tarefaController.novaTarefa();
		Assertions.assertNotNull(tarefaController.getTarefa());
		tarefaController.disporTarefa();
		Assertions.assertNull(tarefaController.getTarefa());
	}
	
	@Test
	@DisplayName("Ao editar Tarefa deve receber um valor pesquisado no banco")
	public void TarefaDeveReceberUsuario() {
		when(tarefasService.getId(1L)).thenReturn(new Tarefa());
		tarefaController.editarTarefa("1");
		Assertions.assertNotNull(tarefaController.getTarefa());
	}
	
	@Test
	@DisplayName("Não ocorre exceções ao salvar tarefa")
	public void DeveSalvarTarefaComSucesso() {
		lenient().when(colab.getById(1L)).thenReturn(new Colaborador());
		doNothing().when(tarefasService).salvar(any(Tarefa.class));
		tarefaController.novaTarefa();
		Assertions.assertDoesNotThrow(() -> tarefaController.salvar());
	}
	
	@Test
	@DisplayName("Tarefa muda o estado para concluida")
	public void DeveConcluirTarefa() {
		Tarefa tarefa = new Tarefa();
		tarefa.setSituacao(false);
		tarefaController.concluir(tarefa);
		Assertions.assertTrue(tarefa.getSituacao());
	}
	
	@Test
	@DisplayName("Lista não deve retornar nulo")
	public void ListaColabNaoDeveSerNulo() {
		when(colab.getAll()).thenReturn(new ArrayList<Colaborador>());
		Assertions.assertNotNull(tarefaController.getColaboradoresList());
	}
	
	
	
}
