package com.company.controller;

import com.company.view.TelaGerenciamentoCliente;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelecionarCliente implements ListSelectionListener {

    private final TelaGerenciamentoCliente tela;

    public SelecionarCliente(TelaGerenciamentoCliente tela) {
        this.tela = tela;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        tela.atualizarFormulario();
    }
}
