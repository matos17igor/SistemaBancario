package com.company.controller;

import com.company.view.TelaGerenciamentoCaixa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarCaixa implements ActionListener {

    private final TelaGerenciamentoCaixa tela;

    public AdicionarCaixa(TelaGerenciamentoCaixa tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.addCaixa();
    }
}
