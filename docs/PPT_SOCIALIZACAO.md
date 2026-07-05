# Roteiro para PPT de Socialização

## Slide 1 - Título

**Gestão AutoPeças**

Sistema web em Java para gestão de estoque e vendas em loja de autopeças.

## Slide 2 - Problema

Pequenas lojas de autopeças muitas vezes controlam produtos, clientes e vendas por planilhas ou anotações.

Problemas:

- dificuldade de acompanhar estoque;
- venda sem quantidade suficiente;
- falta de alerta de reposição;
- informações espalhadas;
- pouca rastreabilidade das vendas.

## Slide 3 - Objetivo

Desenvolver uma aplicação web em Java para controlar produtos, clientes e vendas.

Objetivos específicos:

- cadastrar produtos;
- controlar estoque mínimo;
- cadastrar clientes;
- registrar vendas;
- baixar estoque automaticamente;
- demonstrar o sistema em uma interface web.

## Slide 4 - Aplicação Desenvolvida

Nome: **Gestão AutoPeças**

Finalidade: auxiliar uma loja de autopeças a organizar operações básicas de estoque e venda.

Funcionalidades:

- cadastro de produto;
- cadastro de cliente;
- registro de venda;
- cálculo de valor total;
- baixa automática de estoque;
- alerta de reposição.

## Slide 5 - Tecnologias

- Java 17;
- Spring Boot;
- Spring Web;
- Spring Data JPA;
- Bean Validation;
- H2 Database;
- HTML, CSS e JavaScript;
- Maven.

## Slide 6 - Arquitetura

Camadas:

- Model: Produto, Cliente e Venda;
- DTO: entrada e saída de dados;
- Repository: acesso ao banco;
- Service: regras de negócio;
- Controller: endpoints REST;
- Static: interface web.

## Slide 7 - Tela do Sistema

Inserir print da tela inicial.

Explicar:

- indicadores no topo;
- formulários de cadastro;
- registro de venda;
- lista de estoque;
- lista de vendas recentes.

## Slide 8 - Trecho de Código: Baixa de Estoque

```java
public void baixarEstoque(int quantidade) {
    if (quantidade <= 0) {
        throw new IllegalArgumentException("Quantidade deve ser maior que zero");
    }
    if (quantidadeEstoque < quantidade) {
        throw new IllegalArgumentException("Estoque insuficiente para o produto " + nome);
    }
    quantidadeEstoque -= quantidade;
}
```

## Slide 9 - Explicação do Código

O método valida a quantidade solicitada na venda.

Se não houver estoque suficiente, a venda não é concluída.

Quando a venda é válida, a quantidade é descontada automaticamente.

## Slide 10 - Trecho de Código: Registro de Venda

```java
@Transactional
public VendaResponse registrar(VendaRequest request) {
    Cliente cliente = clienteService.buscarEntidade(request.clienteId());
    Produto produto = produtoService.buscarEntidade(request.produtoId());
    produto.baixarEstoque(request.quantidade());
    Venda venda = new Venda();
    venda.setCliente(cliente);
    venda.setProduto(produto);
    venda.setQuantidade(request.quantidade());
    venda.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(request.quantidade())));
    return new VendaResponse(vendaRepository.save(venda));
}
```

## Slide 11 - Resultados

Resultados obtidos:

- aplicação Java funcional;
- API REST;
- interface web;
- cadastro de produtos e clientes;
- registro de vendas;
- controle de estoque;
- alerta de reposição.
- código-fonte versionado no GitHub.

Repositório: `https://github.com/Elineison/gestao-autopecas-java`

## Slide 12 - Considerações

O projeto mostrou como uma necessidade real de mercado pode ser resolvida com uma aplicação simples.

Aprendizados:

- Java e orientação a objetos;
- Spring Boot;
- persistência com JPA;
- validação de dados;
- regras de negócio;
- organização em camadas.

## Slide 13 - Referências

DEITEL, Paul; DEITEL, Harvey. *Java: como programar*. 10. ed. São Paulo: Pearson, 2017.

ELMASRI, Ramez; NAVATHE, Shamkant B. *Sistemas de banco de dados*. 7. ed. São Paulo: Pearson, 2019.

PRESSMAN, Roger S.; MAXIM, Bruce R. *Engenharia de software: uma abordagem profissional*. 9. ed. Porto Alegre: AMGH, 2020.

RICHARDSON, Leonard; RUBY, Sam. *RESTful web services*. Sebastopol: O'Reilly Media, 2007.

SOMMERVILLE, Ian. *Engenharia de software*. 10. ed. São Paulo: Pearson, 2016.
