/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view;

import com.company.view.frames.AdminFrame;
import javax.swing.*;

public class TelaAdmin {

    private AdminFrame tela;
    private JButton btnClientes;
    private JButton btnGerentes;
    private JButton btnCaixas;
    private JButton btnLogout;

    public void desenha() {
        this.tela = new AdminFrame();
        tela.setVisible(true);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);

        btnClientes = tela.getBtnClientes();
        btnGerentes = tela.getBtnGerentes();
        btnCaixas = tela.getBtnCaixas();
        btnLogout = tela.getBtnLogout();

        btnClientes.addActionListener(e -> desenhaMenuCliente());
        btnGerentes.addActionListener(e -> desenhaMenuGerente());
        btnCaixas.addActionListener(e -> desenhaMenuCaixa());
        btnLogout.addActionListener(e -> tela.setVisible(false));
    }

    public void desenhaMenuCliente() {
        TelaGerenciamentoCliente tc = new TelaGerenciamentoCliente();
        tc.desenha();
    }

    public void desenhaMenuGerente() {
        TelaGerenciamentoGerente tc = new TelaGerenciamentoGerente();
        tc.desenha();
    }

    public void desenhaMenuCaixa() {
        TelaGerenciamentoCaixa tc = new TelaGerenciamentoCaixa();
        tc.desenha();
    }

}
