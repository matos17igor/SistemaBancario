package com.company.controller;

import com.company.view.TelaGerenciamentoGerente;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelecionarGerente implements ListSelectionListener {

    private final TelaGerenciamentoGerente tela;

    public SelecionarGerente(TelaGerenciamentoGerente tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizarFormulario();
    }
}
