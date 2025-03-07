/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.controller;


import com.company.view.TelaGerenciamentoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarCliente implements ActionListener {
    private final TelaGerenciamentoCliente tela;

    public EditarCliente(TelaGerenciamentoCliente tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.editarCliente();
    }
}
