/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
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
