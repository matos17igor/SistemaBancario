/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
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
