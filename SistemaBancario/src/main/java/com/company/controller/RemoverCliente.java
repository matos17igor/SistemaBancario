package com.company.controller;

import com.company.view.TelaGerenciamentoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverCliente implements ActionListener {

    private final TelaGerenciamentoCliente tela;

    public RemoverCliente(TelaGerenciamentoCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.removerCliente();
    }
}
