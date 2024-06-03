package com.iuri.nfe.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iuri.nfe.domain.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
