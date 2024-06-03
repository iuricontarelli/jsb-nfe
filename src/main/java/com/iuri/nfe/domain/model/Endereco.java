package com.iuri.nfe.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String pais;
    private String estado;
    private String cidade;
    private String numero;

    public Endereco() {}

    public Endereco(String pais, String estado, String cidade, String numero) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

}
