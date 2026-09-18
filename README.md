# Controle de Estoque de Perfumaria

API REST desenvolvida em Spring Boot para gerenciamento do estoque de uma perfumaria.

Permite cadastrar, consultar, atualizar e excluir produtos, além de cadastrar e consultar categorias e fornecedores. Cada produto pode ser associado a uma categoria e a um fornecedor.

No cadastro de fornecedores, a aplicação consulta a ViaCEP usando Spring Cloud OpenFeign para preencher o endereço a partir do CEP informado.
## Tecnologias utilizadas

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Bean Validation
- Spring Cloud OpenFeign
- Flyway
- Springdoc OpenAPI / Swagger UI
- SLF4J
- Postman para testes da API

## Funcionalidades

- Cadastro e consulta de categorias
- Cadastro e consulta de fornecedores
- Associação de produtos a categorias e fornecedores
- Consulta de endereço pela ViaCEP no cadastro de fornecedores
- Validação dos dados recebidos e bloqueio de CNPJ duplicado
- Tratamento global de erros
- Transações nas operações de escrita
- Versionamento do banco de dados com Flyway
- Logs de operações e erros com SLF4J
- Documentação interativa da API com Swagger UI

## Endpoints

- `POST /categorias` - Cadastrar categoria
- `GET /categorias` - Listar categorias
- `GET /categorias/{id}` - Buscar categoria por ID
- `POST /fornecedores` - Cadastrar fornecedor com consulta à ViaCEP
- `GET /fornecedores` - Listar fornecedores
- `GET /fornecedores/{id}` - Buscar fornecedor por ID
- `POST /produtos` - Cadastrar produto
- `GET /produtos` - Listar produtos
- `GET /produtos/{id}` - Buscar produto por ID
- `PUT /produtos/{id}` - Atualizar produto
- `DELETE /produtos/{id}` - Excluir produto
- `GET /produtos/buscar?nome=Perfume` - Buscar produtos pelo nome
## Estrutura do projeto

O projeto está organizado seguindo a separação em camadas:

- Controller
- Service
- Repository
- Entity
- DTO
- Exception
- Client: integração com a API externa ViaCEP

## Documentação da API

Com a aplicação em execução, acesse:

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Especificação OpenAPI: http://localhost:8080/v3/api-docs

## Como executar

### Pré-requisitos

- JDK 17
- MySQL em execução na porta 3306
- Acesso à internet para consultar a ViaCEP e baixar as dependências

### Configuração

1. Abra o projeto no IntelliJ como projeto Maven.
2. Confira a URL e o usuário do MySQL em `src/main/resources/application.properties`.
3. Na configuração de execução da aplicação, defina a variável de ambiente `MYSQL_PASSWORD` com a senha do seu MySQL.
4. Execute a classe `ControleEstoquePerfumariaApplication`.

A API estará disponível em `http://localhost:8080`.
O Flyway executa as migrações pendentes na inicialização.

## Testes com Postman

Importe o arquivo `controle-estoque-perfumaria.postman_collection.json` no Postman.

Com a aplicação em execução, execute a coleção pelo Runner, na ordem salva, com uma iteração.

A coleção contém 9 testes, validados com sucesso:
- Cadastro de fornecedor: 201
- Dados inválidos: 400
- CNPJ duplicado: 409
- Listagem de produtos: 200
- Produto inexistente: 404
- Cadastro de produto: 201
- Atualização de produto: 200
- Exclusão de produto: 204
- Consulta após exclusão: 404

Para executar os testes de produtos, devem existir uma categoria com ID 1 e um fornecedor com ID 1. Em um banco novo, confira esses registros antes de executar a coleção.

As variáveis `cnpjTeste` e `produtoId` são preenchidas pelos scripts da coleção. O CNPJ gerado serve apenas para testar a validação de formato de 14 dígitos.