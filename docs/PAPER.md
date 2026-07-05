# Desenvolvimento de uma Aplicação Web em Java para Gestão de Estoque e Vendas em Loja de Autopeças

**Acadêmico:** [Seu nome completo]

**Turma:** [Sua turma]

**Tutor:** [Nome do tutor]

## RESUMO

Este paper apresenta o desenvolvimento do sistema Gestão AutoPeças, uma aplicação web em Java voltada ao controle básico de uma loja de autopeças. A ideia surgiu a partir de uma necessidade comum em pequenos comércios: organizar produtos, clientes e vendas sem depender apenas de planilhas ou anotações manuais. O objetivo geral foi criar um sistema simples, mas funcional, com cadastro de produtos, cadastro de clientes, registro de vendas e atualização automática do estoque. A pesquisa teve natureza aplicada, com abordagem qualitativa, pois partiu de um problema prático e resultou na implementação de uma solução. Para o desenvolvimento, foram utilizados Java 17, Spring Boot, Spring Data JPA, Bean Validation, banco H2, HTML, CSS e JavaScript. Como resultado, foi criada uma aplicação com interface web e API REST, permitindo testar as principais operações do sistema. Conclui-se que o projeto atendeu à proposta da disciplina, pois uniu pesquisa, implementação e documentação de uma aplicação relacionada a uma situação real de mercado.

**Palavras-chave:** Java; estoque; loja de autopeças.

## INTRODUÇÃO

Uma loja de autopeças precisa lidar com vários produtos, categorias, preços, clientes e vendas ao longo do dia. Quando essas informações ficam apenas em planilhas ou cadernos, o controle se torna mais sujeito a erros. Um produto pode ser vendido sem estoque suficiente, um item com baixa quantidade pode passar despercebido e o histórico de vendas pode ficar difícil de consultar.

Com base nesse cenário, a pergunta que orientou este trabalho foi: como desenvolver uma aplicação web em Java que ajude uma loja de autopeças a controlar produtos, clientes, estoque e vendas? A partir dessa pergunta, foi desenvolvida a aplicação Gestão AutoPeças.

O objetivo geral foi implementar uma aplicação web em Java para apoiar o controle de estoque e vendas. Os objetivos específicos foram: levantar uma necessidade de mercado; definir um conjunto pequeno de funcionalidades; criar as entidades Produto, Cliente e Venda; implementar uma API REST; montar uma tela web para demonstração; aplicar a baixa automática de estoque após a venda; e organizar o trabalho no formato de paper.

O sistema não foi pensado para substituir um ERP completo. A proposta foi criar uma versão inicial, com funcionalidades suficientes para demonstrar o aprendizado da disciplina e resolver uma parte importante do problema: saber o que existe em estoque, quem comprou e quais vendas foram registradas.

## FUNDAMENTAÇÃO TEÓRICA

A engenharia de software ajuda a transformar uma necessidade em um sistema organizado. Sommerville (2016) explica que o desenvolvimento de software envolve etapas como levantamento de requisitos, projeto, implementação, testes e manutenção. No sistema Gestão AutoPeças, essas etapas aparecem na definição do problema, na escolha das funcionalidades e na organização do código.

Pressman e Maxim (2020) afirmam que um software deve ser desenvolvido considerando o problema que precisa resolver e a qualidade da solução entregue. Por esse motivo, o sistema foi mantido com escopo simples. Em vez de incluir muitas funções, foram priorizadas as operações mais importantes para uma loja pequena: cadastrar produtos, cadastrar clientes, registrar vendas e atualizar o estoque.

O levantamento de requisitos foi necessário para definir o que a aplicação deveria fazer. Os principais requisitos funcionais foram: permitir o cadastro de produtos, listar os itens disponíveis, cadastrar clientes, registrar vendas e descontar automaticamente a quantidade vendida. Entre os requisitos não funcionais, destacam-se facilidade de uso, execução local e código organizado em camadas.

A linguagem Java foi escolhida por ser bastante utilizada no desenvolvimento de sistemas corporativos. Deitel e Deitel (2017) apresentam Java como uma linguagem orientada a objetos, na qual classes representam entidades e comportamentos. No projeto, as classes `Produto`, `Cliente` e `Venda` representam os elementos principais da loja.

A programação orientada a objetos permite aproximar o código da realidade do negócio. A classe `Produto`, por exemplo, possui nome, código, categoria, preço, quantidade em estoque e estoque mínimo. Além dos dados, ela também possui o método `baixarEstoque`, que representa uma ação do próprio produto dentro do sistema.

O desenvolvimento web com API REST permite que as informações sejam acessadas por meio de requisições HTTP. Richardson e Ruby (2007) explicam que REST organiza o sistema em recursos. Nesta aplicação, os recursos principais são produtos, clientes e vendas, acessados por rotas como `/api/produtos`, `/api/clientes` e `/api/vendas`.

O Spring Boot foi utilizado para facilitar a criação da aplicação Java. Ele permite configurar o servidor, mapear rotas, receber requisições e integrar outros recursos com menos configuração manual. No projeto, o Spring Boot foi usado junto com Spring Web, Spring Data JPA e Bean Validation.

A persistência de dados é importante para que as informações cadastradas possam ser armazenadas e consultadas. Elmasri e Navathe (2019) explicam que bancos de dados organizam dados de forma estruturada. Neste trabalho, foi utilizado o banco H2 em memória, suficiente para testes e demonstração da aplicação durante a disciplina.

**Figura 1 - Trecho de código da regra de baixa de estoque.**

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

Fonte: elaborado pelo autor.

A Figura 1 mostra a regra mais importante do sistema. Antes de descontar o produto do estoque, o método verifica se a quantidade informada é maior que zero e se existe saldo suficiente. Caso alguma regra não seja atendida, a venda não deve continuar. Isso evita um problema comum no comércio: vender um item que não está disponível.

A validação de dados também foi aplicada nas entradas da API. Foram usadas anotações como `@NotBlank`, `@NotNull`, `@Min` e `@DecimalMin` para impedir dados vazios ou valores inválidos. Essa validação ajuda a manter o sistema mais confiável e reduz erros no cadastro.

A organização em camadas também foi adotada no projeto. Os controllers recebem as requisições, os services concentram as regras de negócio, os repositories acessam os dados e os models representam as entidades. Essa separação deixa o código mais fácil de entender e facilita mudanças futuras.

## METODOLOGIA

Este trabalho utilizou pesquisa aplicada, pois teve como objetivo construir uma solução para um problema prático: o controle de estoque e vendas em uma loja de autopeças. Quanto ao tipo, a pesquisa é exploratória e descritiva. É exploratória porque partiu da análise de uma necessidade, e descritiva porque apresenta como a aplicação foi construída.

A abordagem é qualitativa, pois os resultados foram analisados a partir do funcionamento do sistema e das funcionalidades implementadas, e não por meio de números estatísticos. Quanto aos procedimentos, a pesquisa foi bibliográfica e experimental. A parte bibliográfica utilizou livros sobre engenharia de software, Java, banco de dados e desenvolvimento web. A parte experimental corresponde à criação da aplicação.

As fontes usadas foram livros, documentação técnica, código-fonte e testes manuais da aplicação. Os instrumentos utilizados foram levantamento de requisitos, implementação em Java, testes pela interface web e verificação das respostas da API.

A aplicação foi desenvolvida com Java 17 e Spring Boot. O Maven foi usado para gerenciar as dependências. O banco H2 foi escolhido por facilitar os testes, já que não exige instalação de um banco externo. A tela foi criada com HTML, CSS e JavaScript, consumindo os endpoints da API.

O código-fonte foi versionado em um repositório GitHub, o que permitiu organizar os arquivos do projeto e manter o registro da implementação desenvolvida. O repositório está disponível em: `https://github.com/Elineison/gestao-autopecas-java`.

A principal regra de negócio está no registro da venda. Quando uma venda é realizada, o sistema localiza o cliente, localiza o produto, verifica o estoque, calcula o valor total e salva a venda:

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

Esse trecho mostra a integração entre as entidades Cliente, Produto e Venda. A anotação `@Transactional` garante que o registro da venda e a baixa do estoque aconteçam dentro da mesma operação. Se ocorrer erro durante o processo, a operação não deve ser concluída parcialmente.

Como evidências do trabalho prático, devem ser inseridas no documento final as seguintes imagens:

**Figura 2 - Tela inicial do sistema Gestão AutoPeças.** Fonte: elaborado pelo autor.

**Figura 3 - Cadastro de produto.** Fonte: elaborado pelo autor.

**Figura 4 - Cadastro de cliente.** Fonte: elaborado pelo autor.

**Figura 5 - Registro de venda.** Fonte: elaborado pelo autor.

**Figura 6 - Lista de estoque e vendas recentes.** Fonte: elaborado pelo autor.

## CONSIDERAÇÕES

O desenvolvimento do sistema Gestão AutoPeças permitiu aplicar, em uma situação prática, conteúdos relacionados a Java, orientação a objetos, desenvolvimento web, banco de dados e validação de dados. O projeto partiu de um problema simples, mas bastante comum: controlar produtos e vendas sem perder informações importantes.

Durante a construção da aplicação, ficou claro que limitar o escopo é importante. Um sistema comercial completo teria muitas outras funções, mas, para esta disciplina, foi mais adequado concentrar o trabalho nas operações principais: cadastrar, consultar, vender e atualizar o estoque.

Entre os aprendizados, destacam-se a criação de uma API REST com Spring Boot, o uso de entidades JPA, a separação do código em camadas, a validação das entradas e a implementação de uma regra de negócio que altera o estoque após a venda.

Como melhoria futura, o sistema poderia incluir login de usuários, relatórios, cadastro de fornecedores, filtros de produtos e integração com um banco de dados externo. Mesmo assim, a versão atual já demonstra a finalidade proposta e pode ser executada e testada localmente.

Conclui-se que o trabalho atendeu à proposta da disciplina, pois apresentou uma necessidade, implementou uma aplicação em Java e relacionou os trechos de código com conceitos estudados.

## REFERÊNCIAS BIBLIOGRÁFICAS

DEITEL, Paul; DEITEL, Harvey. *Java: como programar*. 10. ed. São Paulo: Pearson, 2017.

ELMASRI, Ramez; NAVATHE, Shamkant B. *Sistemas de banco de dados*. 7. ed. São Paulo: Pearson, 2019.

PRESSMAN, Roger S.; MAXIM, Bruce R. *Engenharia de software: uma abordagem profissional*. 9. ed. Porto Alegre: AMGH, 2020.

RICHARDSON, Leonard; RUBY, Sam. *RESTful web services*. Sebastopol: O'Reilly Media, 2007.

SOMMERVILLE, Ian. *Engenharia de software*. 10. ed. São Paulo: Pearson, 2016.
