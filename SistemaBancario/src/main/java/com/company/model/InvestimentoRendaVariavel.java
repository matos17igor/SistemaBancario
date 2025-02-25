package com.company.model;


public class InvestimentoRendaVariavel extends Investimento {
    private double percentualRisco;

    public InvestimentoRendaVariavel(String nome, double valorMinimo, double taxaRendimento,double percentualRisco) {
        super(nome, valorMinimo, taxaRendimento);
        this.percentualRisco = percentualRisco;
    }
    
    @Override
    public double calcularRendimento(double valorInvestido, int meses) {
        double fatorRisco = 1 - (percentualRisco / 100.0);
        return valorInvestido * taxaRendimento * meses * fatorRisco;
    }
    
}
