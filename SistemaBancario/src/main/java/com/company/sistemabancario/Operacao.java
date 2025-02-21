package com.company.sistemabancario;

import java.util.Date;

public class Operacao {
    
    public int id;
    public static int idAtual = 0;
    private Conta contaAutor;
    private double valor;
    private Date data;
    
    public Operacao(Conta autor, double valor){
        this.id = idAtual;
        this.contaAutor = autor;
        this.valor = valor;
        this.data = new Date();
        idAtual++;
    }
    
}
