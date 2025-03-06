package com.company.controller;

import com.company.view.TelaGerenciamentoCaixa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverCaixa implements ActionListener {

    private final TelaGerenciamentoCaixa tela;

    public RemoverCaixa(TelaGerenciamentoCaixa tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.removerCaixa();
    }
}
