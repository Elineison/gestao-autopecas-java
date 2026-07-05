package br.com.elineison.autopecas.service;

import br.com.elineison.autopecas.dto.ClienteRequest;
import br.com.elineison.autopecas.dto.ClienteResponse;
import br.com.elineison.autopecas.model.Cliente;
import br.com.elineison.autopecas.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        Cliente cliente = new Cliente();
        preencher(cliente, request);
        return new ClienteResponse(clienteRepository.save(cliente));
    }

    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream()
                .map(ClienteResponse::new)
                .toList();
    }

    public ClienteResponse buscar(Long id) {
        return new ClienteResponse(buscarEntidade(id));
    }

    @Transactional
    public ClienteResponse atualizar(Long id, ClienteRequest request) {
        Cliente cliente = buscarEntidade(id);
        preencher(cliente, request);
        return new ClienteResponse(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.delete(buscarEntidade(id));
    }

    public Cliente buscarEntidade(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente nao encontrado"));
    }

    private void preencher(Cliente cliente, ClienteRequest request) {
        cliente.setNome(request.nome());
        cliente.setDocumento(request.documento());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
    }
}
