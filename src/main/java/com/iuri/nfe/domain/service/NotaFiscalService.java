package com.iuri.nfe.domain.service;

import java.util.List;
import java.util.Optional;

import com.iuri.nfe.domain.model.NotaFiscal;

public interface NotaFiscalService {

    NotaFiscal salvar(NotaFiscal notaFiscal);
    List<NotaFiscal> listar();
    Optional<NotaFiscal> buscarPorId(Long id);
    Optional<NotaFiscal> atualizar(Long id, NotaFiscal notaFiscalAtualizada);
    void deletar(Long id);

}
