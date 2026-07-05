# Gestão AutoPeças

Sistema web em Java/Spring Boot para controle de estoque, clientes e vendas de uma loja de autopeças.

O projeto foi criado como uma versão inicial para a disciplina de Imersão Profissional: Implementação de uma Aplicação. A proposta é resolver uma necessidade comum em lojas de autopeças: registrar produtos, acompanhar estoque mínimo, cadastrar clientes e registrar vendas com baixa automática no estoque.

## Funcionalidades

- Cadastro de produtos.
- Listagem de produtos.
- Alerta de reposição por estoque mínimo.
- Cadastro de clientes.
- Registro de venda.
- Baixa automática no estoque ao vender.
- Listagem de vendas recentes.
- Interface web simples para demonstração.
- API REST para testes com navegador, Postman ou Insomnia.

## Tecnologias

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- HTML, CSS e JavaScript
- Maven

## Como executar

Com Java 17 e Maven instalados, execute:

```bash
mvn spring-boot:run
```

Depois acesse:

```text
http://localhost:8080
```

Banco H2:

```text
http://localhost:8080/h2-console
```

Dados:

```text
JDBC URL: jdbc:h2:mem:autopecas
User: sa
Password: vazio
```

## Endpoints

```text
GET    /api/produtos
POST   /api/produtos
GET    /api/produtos/{id}
PUT    /api/produtos/{id}
DELETE /api/produtos/{id}

GET    /api/clientes
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}

GET    /api/vendas
POST   /api/vendas
```

## Regra de negócio principal

Ao registrar uma venda, o sistema verifica se existe estoque suficiente. Se houver, salva a venda e reduz a quantidade do produto automaticamente. Se não houver, retorna erro.

```java
produto.baixarEstoque(request.quantidade());
```

## Estrutura do projeto

```text
src/main/java/br/com/elineison/autopecas
├── config        # carga inicial de dados
├── controller    # endpoints REST
├── dto           # objetos de entrada e saída
├── model         # entidades Produto, Cliente e Venda
├── repository    # acesso aos dados com Spring Data JPA
└── service       # regras de negócio

src/main/resources/static
├── index.html
├── styles.css
└── app.js
```

## Documentação acadêmica

Os textos de apoio do trabalho estão na pasta `docs/`:

- `PAPER.md`: texto base do paper.
- `PPT_SOCIALIZACAO.md`: roteiro dos slides.
- `ROTEIRO_APRESENTACAO_ORAL.md`: apoio para a apresentação.
- `PRINTS_E_EVIDENCIAS.md`: prints e trechos de código sugeridos.

## Autor

Elineison Inácio
