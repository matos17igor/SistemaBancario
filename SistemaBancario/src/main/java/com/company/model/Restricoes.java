package com.company.model;

import java.util.HashMap;
import java.util.Map;

public class Restricoes {
    private double valorMin;
    private int prazoMin;
    private double taxaMin;
    
    public Restricoes(double valorMin, int prazoMin, double taxaMin) {
        this.valorMin = valorMin;
        this.prazoMin = prazoMin;
        this.taxaMin = taxaMin;
    }
    
    public boolean validar(double valor, int prazo, double taxa) {
        return valor >= valorMin && prazo >= prazoMin && taxa >= taxaMin;
    }
    
    public String getMensagemErro(double valor, int prazo, double taxa) {
        if (valor < valorMin) {
            return "O valor deve ser maior que R$ " + valorMin + ".";
        }
        if (prazo < prazoMin) {
            return "O prazo deve ser maior que " + prazoMin + " meses.";
        }
        if (taxa < taxaMin) {
            return "A taxa deve ser maior que " + taxaMin + "%.";
        }
        return "Valores inválidos.";
    }
    
    // Métodos getter adicionados
    public double getValorMin() {
        return valorMin;
    }

    public int getPrazoMin() {
        return prazoMin;
    }

    public double getTaxaMin() {
        return taxaMin;
    }
}
