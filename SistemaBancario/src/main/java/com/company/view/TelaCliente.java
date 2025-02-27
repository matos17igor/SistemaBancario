package com.company.view;

import java.awt.*;
import javax.swing.*;


public class TelaCliente {
    
    private JFrame tela;
    private JPanel painelBotoes;
    private JPanel painelSuperior;
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    
    private JButton btnTransferencia;
    private JButton btnConsulta;
    private JButton btnRendaFixa;
    private JButton btnRendaVariavel;
    private JButton btnSolicitacao;
    private JLabel mensagem;
    private JLabel saldo;


    
    public void desenha() {
        tela = new JFrame("Menu Cliente");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());
        
        desenhaPainelSuperior();
        desenhaPainelBotoes();
        
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
        
        
    }
    
    public void desenhaPainelSuperior(){
        painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        mensagem = new JLabel("Bem-vindo, USER!");
        saldo = new JLabel("Saldo: R$0,00");
        mensagem.setFont(new Font("Arial", Font.BOLD, 18));
        saldo.setFont(new Font("Arial", Font.PLAIN, 16));
        
        painelSuperior.add(mensagem);
        painelSuperior.add(saldo);
        
        tela.add(painelSuperior, BorderLayout.NORTH);
    }
    
    public void desenhaPainelBotoes(){
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 1, 5, 5));
        
        
        
        btnConsulta = new JButton("Realizar transferência");
        btnTransferencia = new JButton("Consulta de Saldo/Extrato");        
        btnRendaFixa = new JButton("Investimento em Renda Fixa");       
        btnRendaVariavel = new JButton("Investimento em Renda Variável");
        btnSolicitacao = new JButton("Solicitação de Crédito");
        
        painelBotoes.add(btnTransferencia);
        painelBotoes.add(btnConsulta);
        painelBotoes.add(btnRendaFixa);
        painelBotoes.add(btnRendaVariavel);
        painelBotoes.add(btnSolicitacao);
        
        tela.add(painelBotoes, BorderLayout.CENTER);
    }

}
