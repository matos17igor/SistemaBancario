package com.company.sistemabancario;

import java.util.Date;

public class Financiamento {
    
    private Cliente cliente;
    private double valor;
    private double entrada;
    private int parcelas;
    private double juros;
    private Date data;
    
    public Financiamento(Cliente cliente, double valor, double entrada, int parcelas, double juros){
        
        this.cliente = cliente;
        this.valor = valor;
        this.entrada = entrada;
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
    
    public double getEntrada() {
        return entrada;
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
