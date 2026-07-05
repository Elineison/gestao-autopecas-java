# Prints e Evidências Para o Paper e PPT

Este arquivo lista os prints e trechos de código que devem ser usados como evidências do desenvolvimento prático.

## Prints Para o Paper

### Figura 2 - Tela Inicial do Sistema Gestão AutoPeças

Mostrar a tela completa em `http://localhost:8080`, com os indicadores, formulários e listas.

Legenda sugerida:

**Figura 2 - Tela inicial do sistema Gestão AutoPeças.**  
Fonte: elaborado pelo autor.

### Figura 3 - Cadastro de Produto

Mostrar o formulário de produto preenchido com nome, código, categoria, preço, estoque e estoque mínimo.

Legenda sugerida:

**Figura 3 - Formulário de cadastro de produto.**  
Fonte: elaborado pelo autor.

### Figura 4 - Cadastro de Cliente

Mostrar o formulário de cliente preenchido com nome, documento, telefone e e-mail.

Legenda sugerida:

**Figura 4 - Formulário de cadastro de cliente.**  
Fonte: elaborado pelo autor.

### Figura 5 - Registro de Venda

Mostrar a área de registro de venda com cliente, produto e quantidade selecionados.

Legenda sugerida:

**Figura 5 - Registro de venda no sistema.**  
Fonte: elaborado pelo autor.

### Figura 6 - Estoque e Vendas Recentes

Mostrar a lista de estoque e a lista de vendas recentes após registrar uma venda.

Legenda sugerida:

**Figura 6 - Estoque atualizado e venda registrada.**  
Fonte: elaborado pelo autor.

## Trechos de Código Para o Paper

### Trecho 1 - Baixa de Estoque

Arquivo:

`src/main/java/br/com/elineison/autopecas/model/Produto.java`

Código:

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

Explicação curta:

Esse método impede venda com quantidade inválida ou estoque insuficiente. Quando a venda é válida, a quantidade vendida é descontada do estoque.

### Trecho 2 - Registro de Venda

Arquivo:

`src/main/java/br/com/elineison/autopecas/service/VendaService.java`

Código:

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

Explicação curta:

Esse método reúne as principais etapas da venda: localizar cliente e produto, baixar o estoque, calcular o valor total e salvar o registro da venda.

## Prints Para o PPT

No PPT, use menos imagens do que no paper. O ideal é deixar a apresentação mais limpa.

Sugestão:

- Slide 7: print da tela inteira;
- Slide 8: trecho do método `baixarEstoque`;
- Slide 10: trecho do método `registrar`;
- Slide 11: print da venda registrada e estoque atualizado.

## Como Tirar Prints Melhores

- abra a aplicação em tela cheia;
- deixe o zoom do navegador em 100%;
- evite mostrar abas pessoais ou dados sensíveis;
- preencha os campos com dados simples;
- registre uma venda antes de tirar o print de estoque e vendas recentes;
- confira se o nome do sistema aparece como Gestão AutoPeças.
