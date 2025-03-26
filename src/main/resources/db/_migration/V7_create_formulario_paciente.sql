CREATE TABLE formulario_paciente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    documentos TEXT -- Campo para armazenar JSON ou outra estrutura
);