# Controle de Estoque de Perfumaria

Projeto desenvolvido em Spring Boot para gerenciamento do estoque de uma perfumaria.

A aplicação possui um CRUD completo de produtos, permitindo cadastrar, listar, buscar, atualizar e excluir produtos do estoque.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Bean Validation

## Funcionalidades

- Cadastro de produtos
- Listagem de produtos
- Busca de produto por ID
- Busca de produtos por nome
- Atualização de produtos
- Exclusão de produtos

## Endpoints

- `POST /produtos` - Cadastrar produto
- `GET /produtos` - Listar produtos
- `GET /produtos/{id}` - Buscar produto por ID
- `PUT /produtos/{id}` - Atualizar produto
- `DELETE /produtos/{id}` - Excluir produto
- `GET /produtos/buscar?nome=` - Buscar produtos por nome

## Estrutura do projeto

O projeto está organizado seguindo a separação em camadas:

- Controller
- Service
- Repository
- Entity
- DTO
- Exception
