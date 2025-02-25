package com.company.model;

import java.util.Date;

public class Operacao {
    
    public int id;
    public static int idAtual = 0;
    private Conta origem;
    private double valor;
    private Date data;
    
    public Operacao(Conta autor, double valor){
        this.id = idAtual;
        this.origem = autor;
        this.valor = valor;
        this.data = new Date();
        idAtual++;
    }
    
}
