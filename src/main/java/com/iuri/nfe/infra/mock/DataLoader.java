package com.iuri.nfe.infra.mock;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iuri.nfe.domain.model.NotaFiscal;
import com.iuri.nfe.domain.service.impl.NotaFiscalServiceImpl;

@Component
public class DataLoader implements CommandLineRunner {

    private final NotaFiscalServiceImpl service;

    public DataLoader(NotaFiscalServiceImpl service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        service.salvar(new NotaFiscal("Fornecedor 1", "Cliente 1", "Produto 1", LocalDate.now()));
        service.salvar(new NotaFiscal("Fornecedor 2", "Cliente 2", "Produto 2", LocalDate.now()));
    }

}
