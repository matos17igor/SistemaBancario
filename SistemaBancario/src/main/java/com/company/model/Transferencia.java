/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.model;

import java.util.Date;

public class Transferencia extends Operacao{
    
    private Conta destino;
    
    public Transferencia(Conta origem,double valor, Conta destino){
        
        super(origem, valor);
        this.destino = destino;
    }
    
    public Conta getDestino() {
        return destino;
    }
}
