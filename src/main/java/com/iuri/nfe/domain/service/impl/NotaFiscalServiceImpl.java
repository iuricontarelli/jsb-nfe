package com.iuri.nfe.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iuri.nfe.application.exception.NotaFiscalNotFoundException;
import com.iuri.nfe.domain.model.NotaFiscal;
import com.iuri.nfe.domain.service.NotaFiscalService;
import com.iuri.nfe.infra.repository.NotaFiscalRepository;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    private final NotaFiscalRepository repository;

    public NotaFiscalServiceImpl(NotaFiscalRepository repository) {
        this.repository = repository;
    }

    @Override
    public NotaFiscal salvar(NotaFiscal notaFiscal) {
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
            throw new NotaFiscalNotFoundException("Nota Fiscal com ID " + id + " não encontrada!");
        }

        return notaFiscal;
    }

    @Override
    public Optional<NotaFiscal> atualizar(Long id, NotaFiscal notaFiscalAtualizada) {
        Optional<NotaFiscal> notaFiscal = repository.findById(id);

        if (notaFiscal.isEmpty()) {
            throw new NotaFiscalNotFoundException("Nota Fiscal com ID " + id + " não encontrada!");
        }

        NotaFiscal existingNotaFiscal = notaFiscal.get();

        existingNotaFiscal.setFornecedor(notaFiscalAtualizada.getFornecedor());
        existingNotaFiscal.setCliente(notaFiscalAtualizada.getCliente());
        existingNotaFiscal.setProduto(notaFiscalAtualizada.getProduto());
        existingNotaFiscal.setData(notaFiscalAtualizada.getData());

        return Optional.of(repository.save(existingNotaFiscal));
    }        

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
