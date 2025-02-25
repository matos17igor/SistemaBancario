package com.company.model;

public class InvestimentoRendaFixa extends Investimento {
    private int prazoMinimo;

    public InvestimentoRendaFixa(String nome, double valorMinimo, double taxaRendimento, int prazoMinimo) {
        super(nome, valorMinimo, taxaRendimento);
        this.prazoMinimo = prazoMinimo;
    }

    @Override
    public double calcularRendimento(double valorInvestido, int meses) {
        if(meses < prazoMinimo){
            return 0;
        }
        return valorInvestido * taxaRendimento * meses;
    }
    
    
    
}
