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

import com.iuri.nfe.domain.model.Empresa;
import com.iuri.nfe.domain.model.NotaFiscal;
import com.iuri.nfe.domain.service.impl.NotaFiscalServiceImpl;
import com.iuri.nfe.infra.repository.EmpresaRepository;

@RestController
@RequestMapping("/nf")
public class NotaFiscalController {

    private final NotaFiscalServiceImpl service;
    private final EmpresaRepository empresaRepository;

    public NotaFiscalController(
        NotaFiscalServiceImpl service,
        EmpresaRepository empresaRepository
    ) {
        this.service = service;
        this.empresaRepository = empresaRepository;
    }

    @PostMapping
    public ResponseEntity<NotaFiscal> criarNotaFiscal(@RequestBody NotaFiscal notaFiscal) {
        Empresa empresa = notaFiscal.getEmpresa();

        if (empresa != null) {
            empresaRepository.save(empresa);
        }
        
        NotaFiscal savedNotaFiscal = service.salvar(notaFiscal);
        return ResponseEntity.ok(savedNotaFiscal);
    }

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> listarNotaFiscals() {
        List<NotaFiscal> notasFiscais = service.listar();
        return ResponseEntity.ok(notasFiscais);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NotaFiscal> buscarNotaFiscalPorId(@PathVariable Long id) {
        Optional<NotaFiscal> notaFiscal = service.buscarPorId(id);
        
        if (notaFiscal.isPresent()) {
            return ResponseEntity.ok(notaFiscal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaFiscal> atualizarNotaFiscal(@PathVariable Long id, @RequestBody NotaFiscal notaFiscalAtualizada) {
        Optional<NotaFiscal> updatedNotaFiscal = service.atualizar(id, notaFiscalAtualizada);

        if (updatedNotaFiscal.isPresent()) {
            return ResponseEntity.ok(updatedNotaFiscal.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
