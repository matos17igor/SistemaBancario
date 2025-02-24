package com.company.sistemabancario;

public abstract class Investimento {
    protected String nome;
    protected double valorMinimo;
    protected double taxaRendimento;

    public Investimento(String nome, double valorMinimo, double taxaRendimento) {
        this.nome = nome;
        this.valorMinimo = valorMinimo;
        this.taxaRendimento = taxaRendimento;
    }
    
    public String getNome(){
        return nome;
    }
    
    public abstract double calcularRendimento(double valorInvestido, int meses);
}
