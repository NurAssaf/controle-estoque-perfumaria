CREATE TABLE fornecedores (
                              id BIGINT NOT NULL AUTO_INCREMENT,
                              nome VARCHAR(255) NOT NULL,
                              cnpj VARCHAR(14) NOT NULL,
                              cep VARCHAR(8) NOT NULL,
                              rua VARCHAR(255),
                              numero VARCHAR(255),
                              bairro VARCHAR(255),
                              cidade VARCHAR(255),
                              uf VARCHAR(2),
                              PRIMARY KEY (id),
                              CONSTRAINT uk_fornecedores_cnpj UNIQUE (cnpj)
);