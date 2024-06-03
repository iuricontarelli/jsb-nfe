package com.iuri.nfe.infra.mock;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iuri.nfe.domain.model.Empresa;
import com.iuri.nfe.domain.model.Endereco;
import com.iuri.nfe.domain.model.NotaFiscal;
import com.iuri.nfe.domain.service.impl.NotaFiscalServiceImpl;
import com.iuri.nfe.infra.repository.EmpresaRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final NotaFiscalServiceImpl service;
    private final EmpresaRepository empresaRepository;

    public DataLoader(
        NotaFiscalServiceImpl service,
        EmpresaRepository empresaRepository
    ) {
        this.service = service;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco1 = new Endereco("Brasil", "SP", "São Paulo", "123");
        Endereco endereco2 = new Endereco("Brasil", "RJ", "Rio de Janeiro", "456");

        Empresa empresa1 = new Empresa("Empresa 1", "12345678000190", endereco1);
        Empresa empresa2 = new Empresa("Empresa 2", "09876543000191", endereco2);

        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        service.salvar(new NotaFiscal(empresa1, "Cliente 1", "Produto 1", LocalDate.now()));
        service.salvar(new NotaFiscal(empresa2, "Cliente 2", "Produto 2", LocalDate.now()));
    }

}
