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

import com.malyson.esig.tarefas.dto.ColabPesquisaDTO;
import com.malyson.esig.tarefas.model.Colaborador;
import com.malyson.esig.tarefas.service.ColaboradorService;

@ExtendWith(MockitoExtension.class)
public class ColaboradorControllerTest {

	@Mock
	private ColaboradorService colab;
	
	@InjectMocks
	private ColaboradorController colabController;
	
	@Test
	@DisplayName("Deve criar um novo colaborador")
	public void DeveCriarNovoColab() {
		Assertions.assertNull(colabController.getColabP());
		colabController.novoColab();
		Assertions.assertNotNull(colabController.getColabP());
	}
	
	@Test
	@DisplayName("Tornar null colaborador ao dispor da pagina")
	public void DeveTornarNullColab() {
		colabController.novoColab();
		Assertions.assertNotNull(colabController.getColabP());
		colabController.disporColab();
		Assertions.assertNull(colabController.getColabP());
	}
	
	@Test
	@DisplayName("Não ocorre exceções ao salvar colaborador")
	public void DeveSalvarColabComSucesso() {
		lenient().when(colab.getById(1L)).thenReturn(new Colaborador());
		when(colab.salvar(any(Colaborador.class))).thenReturn(new Colaborador());
		when(colab.pesquisar(any(ColabPesquisaDTO.class))).thenReturn(new ArrayList<>());
		colabController.novoColab();
		Assertions.assertDoesNotThrow(() -> colabController.salvar());
	}
	
	
}
