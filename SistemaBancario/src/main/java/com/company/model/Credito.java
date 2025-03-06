package com.company.model;

import java.util.Date;

public class Credito {
    
    private String tipo;
    private Cliente cliente;
    private double valor;
    private double entrada;
    private int parcelas;
    private double juros;
    private Date data;
    
    public Credito(String tipo, Cliente cliente, double valor, double entrada, int parcelas, double juros){
        
        this.tipo = tipo;
        this.cliente = cliente;
        this.valor = valor;
        this.entrada = entrada;
        this.parcelas = parcelas;
        this.juros = juros;
        this.data = new Date();
    }

    public String getTipo(){
        return tipo;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setData(Date data) {
        this.data = data;
    }
 
    @Override
    public String toString() {
        return "Cliente: " + cliente.getName();
    }
}
