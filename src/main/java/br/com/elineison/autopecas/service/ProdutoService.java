package br.com.elineison.autopecas.service;

import br.com.elineison.autopecas.dto.ProdutoRequest;
import br.com.elineison.autopecas.dto.ProdutoResponse;
import br.com.elineison.autopecas.model.Produto;
import br.com.elineison.autopecas.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        if (produtoRepository.existsByCodigo(request.codigo())) {
            throw new IllegalArgumentException("Ja existe produto com este codigo");
        }

        Produto produto = new Produto();
        preencher(produto, request);
        return new ProdutoResponse(produtoRepository.save(produto));
    }

    public List<ProdutoResponse> listar() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponse::new)
                .toList();
    }

    public ProdutoResponse buscar(Long id) {
        return new ProdutoResponse(buscarEntidade(id));
    }

    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = buscarEntidade(id);
        preencher(produto, request);
        return new ProdutoResponse(produto);
    }

    @Transactional
    public void excluir(Long id) {
        produtoRepository.delete(buscarEntidade(id));
    }

    public Produto buscarEntidade(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto nao encontrado"));
    }

    private void preencher(Produto produto, ProdutoRequest request) {
        produto.setNome(request.nome());
        produto.setCodigo(request.codigo());
        produto.setCategoria(request.categoria());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());
        produto.setEstoqueMinimo(request.estoqueMinimo());
    }
}
