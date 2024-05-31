package com.iuri.nfe.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuri.nfe.domain.model.NotaFiscal;
import com.iuri.nfe.domain.service.impl.NotaFiscalServiceImpl;

@RestController
@RequestMapping("/nf")
public class NotaFiscalController {

    private final NotaFiscalServiceImpl service;

    public NotaFiscalController(NotaFiscalServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public NotaFiscal criarNotaFiscal(@RequestBody NotaFiscal notaFiscal) {
        return service.salvar(notaFiscal);
    }

    @GetMapping
    public List<NotaFiscal> listarNotaFiscals() {
        return service.listar();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> buscarNotaFiscalPorId(@PathVariable Long id) {
        Optional<NotaFiscal> notaFiscal = service.buscarPorId(id);
        return notaFiscal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaFiscal> atualizarNotaFiscal(@PathVariable Long id, @RequestBody NotaFiscal notaFiscalAtualizada) {
        Optional<NotaFiscal> notaFiscal = service.atualizar(id, notaFiscalAtualizada);
        return notaFiscal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNotaFiscal(@PathVariable Long id) {
        Optional<NotaFiscal> notaFiscal = service.buscarPorId(id);
        if (notaFiscal.isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
