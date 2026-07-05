# Imersão Profissional - Passos 1 e 2

## Passo 1 - Necessidade da Aplicação

A necessidade identificada foi a criação de uma aplicação para auxiliar uma loja de autopeças no controle de produtos, clientes e vendas. Em pequenos negócios, é comum que o controle de estoque seja realizado em planilhas ou anotações manuais, o que pode causar perda de informações, venda de produtos sem estoque suficiente e dificuldade para saber quais itens precisam de reposição.

Diante desse problema, foi proposta a aplicação **Gestão AutoPeças**, um sistema web desenvolvido em Java para permitir que uma loja de autopeças cadastre produtos, acompanhe o estoque, registre clientes e realize vendas com baixa automática da quantidade vendida.

De forma resumida, a necessidade escolhida foi:

> Desenvolver uma aplicação web para auxiliar uma loja de autopeças no gerenciamento de produtos, clientes, estoque e vendas, reduzindo erros manuais e facilitando o acompanhamento dos itens que precisam de reposição.

## Passo 2 - Implementação da Aplicação

A aplicação foi implementada como uma versão inicial, com poucas funcionalidades, mas suficientes para demonstrar os conceitos estudados no curso e atender à proposta da disciplina.

As principais funcionalidades implementadas foram:

- cadastro de produtos;
- listagem de produtos;
- controle de quantidade em estoque;
- definição de estoque mínimo;
- indicação de produtos que precisam de reposição;
- cadastro de clientes;
- registro de vendas;
- cálculo do valor total da venda;
- baixa automática no estoque após a venda;
- interface web para demonstração;
- API REST para testes.

As tecnologias utilizadas foram:

- **Java 17**, linguagem principal da aplicação;
- **Spring Boot**, framework para criação da aplicação web;
- **Spring Web**, para criação dos endpoints REST;
- **Spring Data JPA**, para persistência dos dados;
- **Bean Validation**, para validação das entradas;
- **H2 Database**, banco em memória para facilitar testes;
- **HTML, CSS e JavaScript**, para a interface web;
- **Maven**, para gerenciamento de dependências.

Um trecho importante da implementação é a regra de baixa automática de estoque:

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

Esse código impede venda com quantidade inválida ou estoque insuficiente. Quando a venda é válida, a quantidade é subtraída automaticamente do estoque.

Outro trecho importante é o registro da venda:

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
    venda.setValorUnitario(produto.getPreco());
    venda.setValorTotal(produto.getPreco().multiply(BigDecimal.valueOf(request.quantidade())));

    return new VendaResponse(vendaRepository.save(venda));
}
```

Esse trecho demonstra a ligação entre cliente, produto, venda, cálculo do valor total e atualização do estoque.
