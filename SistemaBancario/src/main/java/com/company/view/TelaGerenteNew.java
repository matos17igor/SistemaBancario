package com.company.view;

import com.company.model.Gerente;
import com.company.view.frames.AdminGerenteNew;
import javax.swing.*;

public class TelaGerenteNew {

    private AdminGerenteNew tela;
    private JButton btnApoio;
    private JButton btnAvaliacao;
    private JButton btnCadastroRendaFixa;
    private JButton btnCadastroRendaVariavel;
    private Gerente gerente;

    public void desenha(Gerente g) {
        this.tela = new AdminGerenteNew();
        tela.setVisible(true);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);
        
        setGerente(g);

        btnApoio = tela.getBtnApoio();
        btnAvaliacao = tela.getBtnAvaliacao();
        btnCadastroRendaFixa = tela.getBtnCadastroRendaFixa();
        btnCadastroRendaVariavel = tela.getBtnCadastroRendaVariavel();
        JLabel labelNome = tela.getLabel();
        labelNome.setText("Bem-vindo, " + getGerente().getName());

        btnApoio.addActionListener(e -> desenhaMenuApoio());
        btnAvaliacao.addActionListener(e -> desenhaAvaliacao());
        btnCadastroRendaFixa.addActionListener(e -> desenhaCadastroRendaFixa());
        btnCadastroRendaVariavel.addActionListener(e -> desenhaCadastroRendaVariavel());
        
 
    }

    public void desenhaMenuApoio() {
        TelaGerenciamentoCliente tc = new TelaGerenciamentoCliente();
        tc.desenha();
    }

    public void desenhaAvaliacao() {
        TelaGerente tg = new TelaGerente();
        tg.desenha(gerente);
    }

    public void desenhaCadastroRendaFixa() {

    }
    
    public void desenhaCadastroRendaVariavel() {

    }
    
    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

}