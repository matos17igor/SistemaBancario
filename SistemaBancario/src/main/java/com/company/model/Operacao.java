package com.company.model;

import java.util.Date;

public class Operacao {
    
    private Conta origem;
    private double valor;
    private Date data;
    
    public Operacao(Conta autor, double valor){
        this.origem = autor;
        this.valor = valor;
        this.data = new Date();
    }
    
    public Conta getOrigem() {
        return origem;
    }
    
    public double getValor() {
        return valor;
    }
}
