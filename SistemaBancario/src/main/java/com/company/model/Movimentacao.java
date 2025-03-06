package com.company.model;

import java.util.Date;

public class Movimentacao {
    private Date data;
    private double valor;
    private String descricao;
    private String origem;

    public Movimentacao(double valor, String descricao, String origem) {
        this.data = new Date();
        this.valor = valor;
        this.descricao = descricao;
        this.origem = origem;
    }

    public Date getData() {
        return data;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
