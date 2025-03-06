package com.company.model;

public class Investimento {

    protected String nome;
    protected double valorMinimo;
    protected double taxaRendimento;

    public Investimento(String nome, double valorMinimo, double taxaRendimento) {
        this.nome = nome;
        this.valorMinimo = valorMinimo;
        this.taxaRendimento = taxaRendimento;
    }

    public String getNome() {
        return nome;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    @Override
    public String toString(){
        return nome;
    }
}
