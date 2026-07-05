package br.com.elineison.autopecas.controller;

import br.com.elineison.autopecas.dto.VendaRequest;
import br.com.elineison.autopecas.dto.VendaResponse;
import br.com.elineison.autopecas.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<VendaResponse> registrar(@RequestBody @Valid VendaRequest request) {
        return ResponseEntity.ok(vendaService.registrar(request));
    }

    @GetMapping
    public ResponseEntity<List<VendaResponse>> listar() {
        return ResponseEntity.ok(vendaService.listar());
    }
}
