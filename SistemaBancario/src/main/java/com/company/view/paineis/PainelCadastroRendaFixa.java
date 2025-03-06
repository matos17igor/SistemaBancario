package com.company.view.paineis;

import com.company.model.InvestimentoRendaFixa;
import com.company.persistence.InvestimentoRendaFixaPersistence;
import com.company.exception.PasswordException;
import com.company.exception.SaldoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelCadastroRendaFixa extends JPanel {
    
    
    public PainelCadastroRendaFixa(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel labelOpcao = new JLabel("Opção escolhida:");
        labelOpcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoOpcao = new JTextField("CDB");
        
        JLabel labelValor = new JLabel("Valor Mínimo a Investir:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValor = new JTextField("Valor Investido");
        //campoValor.setEditable(false);
        
        JLabel labelPrazo = new JLabel("Prazo(meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField("Prazo");
        
        JLabel labelTaxa = new JLabel("Taxa de Rentabilidade(%):");
        labelTaxa.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoTaxa = new JTextField("Taxa");
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
    

        btnCadastrar.addActionListener(e -> {
            // Exemplo de ação: verificando se os campos estão preenchidos e exibindo uma mensagem
            if (!campoValor.getText().isEmpty() && !campoTaxa.getText().isEmpty() && !campoPrazo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Renda Fixa cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        
        Dimension campoSize = new Dimension(300, 30);
        campoOpcao.setPreferredSize(campoSize);
        campoOpcao.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoTaxa.setPreferredSize(campoSize);
        campoTaxa.setMaximumSize(campoSize);
        btnCadastrar.setPreferredSize(new Dimension(150, 30));
        btnCadastrar.setMaximumSize(new Dimension(150, 30));
        
        
        add(Box.createVerticalStrut(35));
        add(labelOpcao);
        add(Box.createVerticalStrut(3));
        add(campoOpcao);
        
        add(Box.createVerticalStrut(15));
        add(labelValor);
        add(Box.createVerticalStrut(3));
        add(campoValor);
        
        add(Box.createVerticalStrut(15));
        add(labelPrazo);
        add(Box.createVerticalStrut(3));
        add(campoPrazo);
        
        add(Box.createVerticalStrut(15));
        add(labelTaxa);
        add(Box.createVerticalStrut(3));
        add(campoTaxa);
        
        add(Box.createVerticalStrut(15));
        add(btnCadastrar);
    }
}
