/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.model;


public class InvestimentoRendaVariavel extends Investimento {
    private double percentualRisco;

    public InvestimentoRendaVariavel(String nome, double valorMinimo, double taxaRendimento,double percentualRisco) {
        super(nome, valorMinimo, taxaRendimento);
        this.percentualRisco = percentualRisco;
    }

    public double getRisco() {
        return percentualRisco;
    }
}
