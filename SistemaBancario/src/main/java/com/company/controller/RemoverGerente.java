package com.company.controller;

import com.company.view.TelaGerenciamentoGerente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverGerente implements ActionListener {

    private final TelaGerenciamentoGerente tela;

    public RemoverGerente(TelaGerenciamentoGerente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.removerGerente();
    }
}
