package com.company.sistemabancario;

public class NameException extends Exception {

    public NameException() {
        super("Nome inválido!");
    }

    public NameException(String msg) {
        super(msg);
    }
}
