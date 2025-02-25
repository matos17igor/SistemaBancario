package com.company.model;

import java.util.Date;

public class Transferencia extends Operacao{
    
    private Conta destino;
    
    public Transferencia(Conta origem,double valor, Conta destino){
        
        super(origem, valor);
        this.destino = destino;
    }
}
