package com.company.model;

import java.util.Date;

public class Emprestimo {
    
    private Cliente cliente;
    private double valor;
    private int parcelas;
    private double juros;
    private Date data;
    
    public Emprestimo(Cliente cliente, double valor, int parcelas, double juros){
        
        this.cliente = cliente;
        this.valor = valor;
        this.parcelas = parcelas;
        this.juros = juros;
        this.data = new Date();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public double getJuros() {
        return juros;
    }
    
    public Date getData() {
        return data;
    }

}
