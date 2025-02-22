package com.company.sistemabancario;

import java.util.List;
import java.util.ArrayList;

public class Conta {
    private String numero;
    private double saldo;
    private String senhaTransacao;
    private String titular;
    private List<Transferencia> transferencias;
    private List<Saque> saques;
    private List<Deposito> depositos;
    private List<Investimento> investimentos;

    public Conta() {
        
    }
    
    public Conta(String numero, double saldo, String senhaTransacao, String titular) {
        this.numero = numero;
        this.saldo = saldo;
        this.senhaTransacao = senhaTransacao;
        this.titular = titular;
        transferencias = new ArrayList<>();
        saques = new ArrayList<>();
        depositos = new ArrayList<>();
        investimentos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
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
    
    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public List<Saque> getSaques() {
        return saques;
    }

    public List<Deposito> getDepositos() {
        return depositos;
    }

    public List<Investimento> getInvestimentos() {
        return investimentos;
    }
    
}
