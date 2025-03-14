/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
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
