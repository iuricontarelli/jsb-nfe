package com.iuri.nfe.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iuri.nfe.application.exception.NotaFiscalNotFoundException;
import com.iuri.nfe.domain.model.Empresa;
import com.iuri.nfe.domain.model.NotaFiscal;
import com.iuri.nfe.domain.service.NotaFiscalService;
import com.iuri.nfe.domain.service.impl.util.AtualizacaoService;
import com.iuri.nfe.infra.repository.EmpresaRepository;
import com.iuri.nfe.infra.repository.NotaFiscalRepository;

import jakarta.transaction.Transactional;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotaFiscalRepository repository;
    private final EmpresaRepository empresaRepository;
    private final AtualizacaoService atualizacaoService;

    public NotaFiscalServiceImpl(
        NotaFiscalRepository repository,
        EmpresaRepository empresaRepository,
        AtualizacaoService atualizacaoService
    ) {
        this.repository = repository;
        this.empresaRepository = empresaRepository;
        this.atualizacaoService = atualizacaoService;
    }

    @Override
    public NotaFiscal salvar(NotaFiscal notaFiscal) {
        empresaRepository.save(notaFiscal.getEmpresa());
        return repository.save(notaFiscal);
    }

    @Override
    public List<NotaFiscal> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<NotaFiscal> buscarPorId(Long id) {
        Optional<NotaFiscal> notaFiscal = repository.findById(id);

        if (notaFiscal.isEmpty()) {
            throw new NotaFiscalNotFoundException("Nota Fiscal com ID " + id + " não encontrada");
        }

        return notaFiscal;
    }

    @Override
    @Transactional
    public Optional<NotaFiscal> atualizar(Long id, NotaFiscal notaFiscalAtualizada) {
        return repository.findById(id).map(notaFiscal -> {
            Empresa empresaAtual = notaFiscal.getEmpresa();
            Empresa empresaAtualizada = notaFiscalAtualizada.getEmpresa();
            
            atualizacaoService.atualizarEmpresa(empresaAtual, empresaAtualizada);
            atualizacaoService.atualizarDetalhesNotaFiscal(notaFiscal, notaFiscalAtualizada);

            empresaRepository.save(empresaAtual);
            return Optional.of(repository.save(notaFiscal));
        }).orElseThrow(() -> new NotaFiscalNotFoundException("Nota Fiscal com ID " + id + " não encontrada"));
    }

    @Override
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new NotaFiscalNotFoundException("Nota Fiscal com ID " + id + " não encontrada");
        }
        repository.deleteById(id);
    }

}
