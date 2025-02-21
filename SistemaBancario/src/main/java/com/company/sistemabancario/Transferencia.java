package com.company.sistemabancario;

import java.util.Date;

public class Transferencia extends Operacao{
    
    private Conta contaDestino;
    
    public Transferencia(Conta contaAutor,double valor, Conta destino){
        
        super(contaAutor, valor);
        this.contaDestino = destino;
    }
}
