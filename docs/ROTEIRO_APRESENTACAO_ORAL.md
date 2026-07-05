# Roteiro de Fala Para a Socialização

Este roteiro serve como apoio para apresentar o trabalho de forma natural. A ideia não é ler tudo palavra por palavra, mas usar como base para organizar a explicação.

## Abertura

Boa noite. Meu trabalho foi o desenvolvimento de uma aplicação web em Java chamada Gestão AutoPeças. A proposta foi criar um sistema simples para ajudar uma loja de autopeças a controlar produtos, clientes, vendas e estoque.

Escolhi esse tema porque é uma situação comum em pequenos comércios. Muitas lojas ainda usam planilhas ou anotações para controlar estoque, e isso pode gerar erros, principalmente quando um produto é vendido e a quantidade não é atualizada corretamente.

## Problema

O problema principal é o controle manual das informações. Quando o estoque não é atualizado no momento da venda, a loja pode vender um produto sem ter quantidade suficiente ou deixar de comprar um item que já está acabando.

Por isso, a aplicação foi pensada para centralizar as informações principais: produtos, clientes e vendas.

## Objetivo

O objetivo geral foi desenvolver uma aplicação web em Java para apoiar o controle de estoque e vendas em uma loja de autopeças.

Os objetivos específicos foram cadastrar produtos, cadastrar clientes, registrar vendas, calcular o valor total da venda e fazer a baixa automática no estoque.

## Tecnologias

Para desenvolver o projeto, utilizei Java 17 e Spring Boot no back-end. Também usei Spring Web para criar a API, Spring Data JPA para trabalhar com os dados, Bean Validation para validar as entradas e banco H2 para facilitar os testes.

Na parte visual, foi criada uma tela simples com HTML, CSS e JavaScript, consumindo os dados da API.

## Demonstração da Tela

Na tela inicial aparecem alguns indicadores, como quantidade de produtos, clientes, vendas e itens que precisam de reposição.

A tela também possui formulário para cadastrar produto, formulário para cadastrar cliente e uma área para registrar venda.

Na parte inferior, aparecem os produtos em estoque e as vendas recentes. Quando uma venda é registrada, o sistema desconta a quantidade vendida do estoque do produto.

## Trecho de Código: Baixa de Estoque

Um dos trechos mais importantes do projeto é o método `baixarEstoque`.

Esse método verifica se a quantidade informada é maior que zero e se existe estoque suficiente. Se não houver quantidade suficiente, o sistema impede a venda. Se estiver tudo correto, ele desconta a quantidade do estoque.

Essa regra é importante porque evita que o sistema registre uma venda de um produto sem saldo disponível.

## Trecho de Código: Registro de Venda

Outro trecho importante está no serviço de vendas. Nele, o sistema busca o cliente, busca o produto, chama a baixa de estoque, calcula o valor total e salva a venda.

Esse processo mostra a ligação entre as entidades Cliente, Produto e Venda. Também mostra como uma regra de negócio pode ser centralizada no back-end, deixando o sistema mais organizado.

## Considerações

Com esse trabalho, foi possível aplicar conceitos de Java, orientação a objetos, API REST, banco de dados, validação e organização em camadas.

O projeto foi mantido com escopo simples, porque a proposta da disciplina era desenvolver uma aplicação possível de implementar e apresentar. Mesmo assim, ele representa uma situação real de mercado e pode ser evoluído futuramente com login, relatórios, cadastro de fornecedores e filtros.

Concluo que o trabalho atendeu à proposta da disciplina, pois partiu de uma necessidade prática, teve implementação em Java e apresentou evidências do sistema funcionando.

## Fechamento Curto

De forma geral, o principal aprendizado foi entender melhor como transformar uma necessidade do dia a dia em uma aplicação funcional, ligando a parte teórica da disciplina com a implementação prática.
