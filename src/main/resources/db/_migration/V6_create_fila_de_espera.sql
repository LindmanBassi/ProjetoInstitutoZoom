CREATE TABLE fila_de_espera (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_fila ENUM('CONSULTA', 'DOCUMENTOS', 'OUTROS') NOT NULL, -- Enum para TiposDeFila
    formulario_id BIGINT NOT NULL, -- Chave estrangeira para a tabela formulario_paciente
    CONSTRAINT fk_fila_espera_formulario FOREIGN KEY (formulario_id) REFERENCES formulario_paciente(id)
);