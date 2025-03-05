package com.company.view;

import com.company.view.frames.AdminFrame;
import javax.swing.*;

public class TelaAdmin {

    private AdminFrame tela;
    private JButton btnClientes;
    private JButton btnGerentes;
    private JButton btnCaixas;

    public void desenha() {
        this.tela = new AdminFrame();
        tela.setVisible(true);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);

        btnClientes = tela.getBtnClientes();
        btnGerentes = tela.getBtnGerentes();
        btnCaixas = tela.getBtnCaixas();

        btnClientes.addActionListener(e -> desenhaMenuCliente());
        btnGerentes.addActionListener(e -> desenhaMenuGerente());
        btnCaixas.addActionListener(e -> desenhaMenuCaixa());
    }

    public void desenhaMenuCliente() {
        TelaGerenciamentoCliente tc = new TelaGerenciamentoCliente();
        tc.desenha();
    }

    public void desenhaMenuGerente() {

    }

    public void desenhaMenuCaixa() {

    }

}
