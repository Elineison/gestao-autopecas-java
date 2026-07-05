package br.com.elineison.autopecas.config;

import br.com.elineison.autopecas.dto.ClienteRequest;
import br.com.elineison.autopecas.dto.ProdutoRequest;
import br.com.elineison.autopecas.model.CategoriaProduto;
import br.com.elineison.autopecas.service.ClienteService;
import br.com.elineison.autopecas.service.ProdutoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DadosIniciais implements CommandLineRunner {
    private final ProdutoService produtoService;
    private final ClienteService clienteService;

    public DadosIniciais(ProdutoService produtoService, ClienteService clienteService) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    @Override
    public void run(String... args) {
        produtoService.criar(new ProdutoRequest("Pastilha de freio dianteira", "FR-001", CategoriaProduto.FREIO, new BigDecimal("189.90"), 12, 4));
        produtoService.criar(new ProdutoRequest("Filtro de oleo", "FT-010", CategoriaProduto.FILTROS, new BigDecimal("39.90"), 8, 5));
        produtoService.criar(new ProdutoRequest("Amortecedor traseiro", "SP-220", CategoriaProduto.SUSPENSAO, new BigDecimal("329.90"), 3, 2));
        clienteService.criar(new ClienteRequest("Oficina Central", "12.345.678/0001-90", "(85) 99999-1010", "compras@oficinacentral.com"));
    }
}
