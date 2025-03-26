CREATE TABLE solicitacao_consulta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL, -- Chave estrangeira para a tabela cliente
    data_solicitada DATE NOT NULL,
    horario_solicitado VARCHAR(10) NOT NULL,
    status ENUM('PENDENTE', 'CONFIRMADA', 'REJEITADA') NOT NULL, -- Enum para StatusSolicitacao
    observacoes_equipe TEXT, -- Observações da equipe administrativa
    CONSTRAINT fk_solicitacao_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);