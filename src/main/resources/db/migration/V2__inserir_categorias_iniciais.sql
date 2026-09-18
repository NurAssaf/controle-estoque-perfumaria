INSERT INTO categorias (nome)
SELECT 'Feminino'
    WHERE NOT EXISTS (
    SELECT 1 FROM categorias WHERE nome = 'Feminino'
);

INSERT INTO categorias (nome)
SELECT 'Masculino'
    WHERE NOT EXISTS (
    SELECT 1 FROM categorias WHERE nome = 'Masculino'
);

INSERT INTO categorias (nome)
SELECT 'Unissex'
    WHERE NOT EXISTS (
    SELECT 1 FROM categorias WHERE nome = 'Unissex'
);

INSERT INTO categorias (nome)
SELECT 'Infantil'
    WHERE NOT EXISTS (
    SELECT 1 FROM categorias WHERE nome = 'Infantil'
);