package com.company.view;

import java.awt.*;
import javax.swing.*;


public class TelaCliente {
    
    private JFrame tela;
    private JPanel painel;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    
    private JButton btnTransferencia;
    private JButton btnConsulta;
    private JButton btnRendaFixa;
    private JButton btnRendaVariavel;
    private JButton btnSolicitacao;
    

    
    public void desenha() {
        tela = new JFrame("Menu Cliente");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());
        desenhaGrid();
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
        
        
    }
    
    public void desenhaGrid(){
        painel = new JPanel();
        painel.setLayout(new GridLayout(5, 1, 10, 10));
        
        
        
        btnConsulta = new JButton("Realizar transferência");
        
        btnTransferencia = new JButton("Consulta de Saldo/Extrato");
        
        btnRendaFixa = new JButton("Investimento em Renda Fixa");
        
        btnRendaVariavel = new JButton("Investimento em Renda Variável");
        
        btnSolicitacao = new JButton("Solicitação de Crédito");
        
        painel.add(btnTransferencia);
        painel.add(btnConsulta);
        painel.add(btnRendaFixa);
        painel.add(btnRendaVariavel);
        painel.add(btnConsulta);
        painel.add(btnSolicitacao);
        
        tela.add(painel, BorderLayout.CENTER);
    }
    
    
}
