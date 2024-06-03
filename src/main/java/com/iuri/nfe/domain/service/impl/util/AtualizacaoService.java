package com.iuri.nfe.domain.service.impl.util;

import org.springframework.stereotype.Service;

import com.iuri.nfe.domain.model.Empresa;
import com.iuri.nfe.domain.model.Endereco;
import com.iuri.nfe.domain.model.NotaFiscal;

@Service
public class AtualizacaoService {

    public void atualizarEmpresa(Empresa empresaAtual, Empresa empresaAtualizada) {
        empresaAtual.setNome(empresaAtualizada.getNome());
        empresaAtual.setCnpj(empresaAtualizada.getCnpj());
        atualizarEndereco(empresaAtual.getEndereco(), empresaAtualizada.getEndereco());
    }

    public void atualizarEndereco(Endereco enderecoAtual, Endereco enderecoAtualizado) {
        enderecoAtual.setPais(enderecoAtualizado.getPais());
        enderecoAtual.setEstado(enderecoAtualizado.getEstado());
        enderecoAtual.setCidade(enderecoAtualizado.getCidade());
        enderecoAtual.setNumero(enderecoAtualizado.getNumero());
    }

    public void atualizarDetalhesNotaFiscal(NotaFiscal notaFiscalAtual, NotaFiscal notaFiscalAtualizada) {
        notaFiscalAtual.setCliente(notaFiscalAtualizada.getCliente());
        notaFiscalAtual.setProduto(notaFiscalAtualizada.getProduto());
        notaFiscalAtual.setData(notaFiscalAtualizada.getData());
    }

}
