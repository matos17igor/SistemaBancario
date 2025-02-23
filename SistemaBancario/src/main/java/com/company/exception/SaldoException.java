package com.company.exception;

public class SaldoException extends Exception {

    public SaldoException() {
        super("Saldo insuficiente para esta operação.");
    }
}
