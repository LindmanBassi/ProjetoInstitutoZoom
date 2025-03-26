CREATE TABLE consulta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT NOT NULL, -- Chave estrangeira para a tabela cliente
    data DATE NOT NULL,
    horario VARCHAR(10) NOT NULL,
    status ENUM('AGENDADA', 'REALIZADA', 'CANCELADA') NOT NULL, -- Enum para StatusConsulta
    CONSTRAINT fk_consulta_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);