/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
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
