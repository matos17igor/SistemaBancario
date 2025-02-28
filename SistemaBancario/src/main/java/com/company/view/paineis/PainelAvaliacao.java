package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class PainelAvaliacao extends JPanel {
    public PainelAvaliacao(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel labelValor = new JLabel("Valor de Crédito:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValor = new JTextField("Valor");
        campoValor.setEditable(false);
        
        JLabel labelPrazo = new JLabel("Prazo:");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField("Prazo");
        campoPrazo.setEditable(false);
        
        JLabel labelSaldo = new JLabel("Saldo Atual: R$0,00");
        labelSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        labelSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel labelExtrato = new JLabel("Extrato:");
        labelExtrato.setAlignmentX(Component.CENTER_ALIGNMENT);
        String[] colunas = {"Conta Destino", "Transferência", "Valor"};
        Object[][] dados = {
            {"Pedro", "Saque", "R$ 1.000,00"},
            {"Pedro", "Depósito", "R$ 500,00"},
            {"Pedro", "Transferência", "R$ 2.000,00"},
            {"Pedro", "Investimento", "R$ 300,00"},
            {"Pedro", "Saque", "R$ 1.000,00"},
            {"Pedro", "Depósito", "R$ 500,00"},
            {"Pedro", "Transferência", "R$ 2.000,00"},
            {"Pedro", "Investimento", "R$ 300,00"}
        };
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
        JTable tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane campoTabela = new JScrollPane(tabela);
        
        JButton btnAutorizar = new JButton("Autorizar");
        btnAutorizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        Dimension campoSize = new Dimension(300, 30);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoTabela.setPreferredSize(new Dimension(300, 100));
        campoTabela.setMaximumSize(new Dimension(300, 100));
        btnAutorizar.setPreferredSize(new Dimension(150, 30));
        btnAutorizar.setMaximumSize(new Dimension(150, 30));
        
        
        add(Box.createVerticalStrut(95));
        add(labelValor);
        add(Box.createVerticalStrut(3));
        add(campoValor);
        
        add(Box.createVerticalStrut(15));
        add(labelPrazo);
        add(Box.createVerticalStrut(3));
        add(campoPrazo);
        
        add(Box.createVerticalStrut(15));
        add(labelSaldo);
        
        add(Box.createVerticalStrut(15));
        add(labelExtrato);
        add(Box.createVerticalStrut(3));
        add(campoTabela);
        
        add(Box.createVerticalStrut(15));
        add(btnAutorizar);
    }
}