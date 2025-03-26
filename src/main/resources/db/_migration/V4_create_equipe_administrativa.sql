CREATE TABLE equipe_adm (
    id BIGINT PRIMARY KEY, -- Mesmo ID da tabela login
    nome VARCHAR(255) NOT NULL,
    CONSTRAINT fk_equipe_adm_login FOREIGN KEY (id) REFERENCES login(id)
);