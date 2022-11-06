insert into colaborador (id, nome, setor, telefone) VALUES (1, 'Malyson', 'Desenvolvimento', '(84) 99999-9999') ON CONFLICT DO NOTHING;
insert into colaborador (id, nome, setor, telefone) VALUES (2, 'Carlos', 'Designer', '(84) 99999-9999') ON CONFLICT DO NOTHING;
insert into colaborador (id, nome, setor, telefone) VALUES (1, 'Fernando', 'RH', '(84) 99999-9999') ON CONFLICT DO NOTHING;

INSERT INTO tarefa(
	id, deadline, descricao, prioridade, situacao, titulo, resposavel)
	VALUES (1, '2022-11-09 15:00:00', 'Entrevista com o tech-leader da esig', 'Alta', false, 'Entrevista Esig', 1) ON CONFLICT DO NOTHING;
    
INSERT INTO tarefa(
	id, deadline, descricao, prioridade, situacao, titulo, resposavel)
	VALUES (1, '2022-11-06 17:00:00', 'Tarefa para testar a função de excluir', 'Baixa', false, 'Tarefa para testar Excluir', 2) ON CONFLICT DO NOTHING;

INSERT INTO tarefa(
	id, deadline, descricao, prioridade, situacao, titulo, resposavel)
	VALUES (1, '2022-11-11 15:00:00', 'Tarefa para testar a função de editar', 'Media', false, 'Tarefa para testar o editar', 3) ON CONFLICT DO NOTHING;