/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.exception;

public class SaldoException extends Exception {

    public SaldoException() {
        super("Saldo insuficiente para esta operação.");
    }
}
