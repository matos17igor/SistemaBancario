package com.company.sistemabancario;

public class Conta {
    private String numero;
    private double saldo;
    private String senhaTransacao;
    private String titular;

    public Conta() {
        
    }
    
    public Conta(String numero, double saldo, String senhaTransacao, String titular) {
        this.numero = numero;
        this.saldo = saldo;
        this.senhaTransacao = senhaTransacao;
        this.titular = titular;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getSenhaTransacao() {
        return senhaTransacao;
    }

    public void setSenhaTransacao(String senhaTransacao) {
        this.senhaTransacao = senhaTransacao;
    }

    public String getTitular() {
        return titular;
    }
    
}
