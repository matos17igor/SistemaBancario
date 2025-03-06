package com.company.model;

import java.util.List;
import java.util.ArrayList;

public class Conta {
    private String numero;
    private double saldo;
    private String senhaTransacao;
    private String titular;
    private List<Movimentacao> movimentacoes;

    public Conta() {

    }

    public Conta(String numero, double saldo, String senhaTransacao, String titular) {
        this.numero = numero;
        this.saldo = saldo;
        this.senhaTransacao = senhaTransacao;
        this.titular = titular;
        this.movimentacoes = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
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

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(Movimentacao movimentacao) {
        movimentacoes.add(movimentacao);
    }
}
