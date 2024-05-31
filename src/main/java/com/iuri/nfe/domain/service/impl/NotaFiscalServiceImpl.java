package com.iuri.nfe.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        return repository.findById(id);
    }

    @Override
    public Optional<NotaFiscal> atualizar(Long id, NotaFiscal notaFiscalAtualizada) {
        return repository.findById(id).map(notaFiscal -> {
            notaFiscal.setFornecedor(notaFiscalAtualizada.getFornecedor());
            notaFiscal.setCliente(notaFiscalAtualizada.getCliente());
            notaFiscal.setProduto(notaFiscalAtualizada.getProduto());
            notaFiscal.setData(notaFiscalAtualizada.getData());
            return repository.save(notaFiscal);
        });
    }        

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
