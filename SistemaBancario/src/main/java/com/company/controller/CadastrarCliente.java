package com.company.controller;

import com.company.view.TelaCadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarCliente implements ActionListener {

    private final TelaCadastro tela;

    public CadastrarCliente(TelaCadastro tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.cadastrarCliente();
    }
}
