CREATE TABLE cliente (
    id BIGINT PRIMARY KEY, -- Mesmo ID da tabela login
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    CONSTRAINT fk_cliente_login FOREIGN KEY (id) REFERENCES login(id)
);