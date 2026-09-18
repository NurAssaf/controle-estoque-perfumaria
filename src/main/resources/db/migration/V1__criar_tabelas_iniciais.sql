CREATE TABLE categorias (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            nome VARCHAR(255) NOT NULL,
                            PRIMARY KEY (id)
);

CREATE TABLE produtos (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nome VARCHAR(255) NOT NULL,
                          marca VARCHAR(255) NOT NULL,
                          preco DOUBLE NOT NULL,
                          quantidade_estoque INT NOT NULL,
                          categoria_id BIGINT,
                          PRIMARY KEY (id),
                          CONSTRAINT fk_produtos_categoria
                              FOREIGN KEY (categoria_id) REFERENCES categorias (id)
);