package br.com.elineison.autopecas.service;

import br.com.elineison.autopecas.dto.VendaRequest;
import br.com.elineison.autopecas.dto.VendaResponse;
import br.com.elineison.autopecas.model.Cliente;
import br.com.elineison.autopecas.model.Produto;
import br.com.elineison.autopecas.model.Venda;
import br.com.elineison.autopecas.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public VendaService(VendaRepository vendaRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.vendaRepository = vendaRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

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

    public List<VendaResponse> listar() {
        return vendaRepository.findAll().stream()
                .map(VendaResponse::new)
                .toList();
    }
}
