CREATE TABLE assistente_social (
    id BIGINT PRIMARY KEY, -- Mesmo ID da tabela login
    nome VARCHAR(255) NOT NULL,
    CONSTRAINT fk_assistente_social_login FOREIGN KEY (id) REFERENCES login(id)
);