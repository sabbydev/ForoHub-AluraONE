CREATE TABLE topicos (
    id_topico BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje VARCHAR(255) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    estado BOOLEAN NOT NULL,
    autor VARCHAR(255) NOT NULL,
    curso VARCHAR(255) NOT NULL,
    UNIQUE (titulo, mensaje)
);