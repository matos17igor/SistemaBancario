package com.company.model;

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
    private List<Credito> creditos;

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
        creditos = new ArrayList<>();
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

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void setTransferencias(Transferencia transferencias) {
        this.transferencias.add(transferencias);
    }

    public void setSaques(Saque saques) {
        this.saques.add(saques);
    }

    public void setDepositos(List<Deposito> depositos) {
        this.depositos = depositos;
    }

    public void setInvestimentos(List<Investimento> investimentos) {
        this.investimentos = investimentos;
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

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }
   
}
