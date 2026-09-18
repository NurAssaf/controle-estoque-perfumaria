ALTER TABLE produtos
    ADD COLUMN fornecedor_id BIGINT;

ALTER TABLE produtos
    ADD CONSTRAINT fk_produtos_fornecedor
        FOREIGN KEY (fornecedor_id)
            REFERENCES fornecedores (id);