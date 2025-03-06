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
