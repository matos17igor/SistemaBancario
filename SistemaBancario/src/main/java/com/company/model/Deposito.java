package com.company.model;


public class Deposito extends Operacao{
    
    private Conta destino;
    
    public Deposito(Conta autor, double valor, Conta destino){
        super(autor, valor);
        this.destino = destino;
    }
}
