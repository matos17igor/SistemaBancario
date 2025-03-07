/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.model;


public class Deposito extends Operacao{
    
    private Conta destino;
    
    public Deposito(Conta autor, double valor, Conta destino){
        super(autor, valor);
        this.destino = destino;
    }

    public Conta getDestino() {
        return destino;
    }
    
}
