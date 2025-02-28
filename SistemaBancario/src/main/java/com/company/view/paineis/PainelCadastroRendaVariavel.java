package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelCadastroRendaVariavel extends JPanel{
    public PainelCadastroRendaVariavel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel labelOpcao = new JLabel("Opção escolhida:");
        labelOpcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoOpcao = new JTextField("Ações");
        campoOpcao.setEditable(false);
        
        JLabel labelValor = new JLabel("Valor a Investir:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValor = new JTextField("Valor Investido");
        campoValor.setEditable(false);
        
        JLabel labelPrazo = new JLabel("Prazo(meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField("Prazo");
        campoPrazo.setEditable(false);
        
        JLabel labelRisco = new JLabel("Risco Estimado:");
        labelRisco.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoRisco = new JTextField("Risco");
        campoRisco.setEditable(false);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCadastrar.addActionListener(e -> {
            // Exemplo de ação: verificando se os campos estão preenchidos e exibindo uma mensagem
            if (!campoValor.getText().isEmpty() && !campoRisco.getText().isEmpty() && !campoPrazo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Renda Variavel cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
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
        campoRisco.setPreferredSize(campoSize);
        campoRisco.setMaximumSize(campoSize);
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
        add(labelRisco);
        add(Box.createVerticalStrut(3));
        add(campoRisco);
        
        add(Box.createVerticalStrut(15));
        add(btnCadastrar);
    }
}