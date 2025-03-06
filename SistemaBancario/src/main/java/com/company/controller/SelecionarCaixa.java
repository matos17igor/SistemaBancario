package com.company.controller;

import com.company.view.TelaGerenciamentoCaixa;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelecionarCaixa implements ListSelectionListener {

    private final TelaGerenciamentoCaixa tela;

    public SelecionarCaixa(TelaGerenciamentoCaixa tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizarFormulario();
    }
}
