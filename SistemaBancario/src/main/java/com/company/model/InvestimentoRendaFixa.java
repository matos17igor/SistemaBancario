/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.model;

public class InvestimentoRendaFixa extends Investimento {
    private int prazoMinimo;

    public InvestimentoRendaFixa(String nome, double valorMinimo, double taxaRendimento, int prazoMinimo) {
        super(nome, valorMinimo, taxaRendimento);
        this.prazoMinimo = prazoMinimo;
    }

    public int getPrazoMinimo() {
        return prazoMinimo;
    }
}
