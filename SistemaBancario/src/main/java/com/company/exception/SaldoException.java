/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.company.exception;

/**
 *
 * @author ice
 */
public class SaldoException extends Exception {

    /**
     * Creates a new instance of <code>SaldoException</code> without detail
     * message.
     */
    public SaldoException() {
    }

    /**
     * Constructs an instance of <code>SaldoException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SaldoException(String msg) {
        super(msg);
    }
}
