package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelApoio extends JPanel {
    
    double valor = 1000000;
    
    public PainelApoio() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if(valor >= 1000000){
        // Exemplo de botão
        
            JLabel labelOperacao = new JLabel("Operacao:");
            labelOperacao.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField campoOperacao = new JTextField("Trasnferencia");
            campoOperacao.setEditable(false);
        
            JLabel labelValor = new JLabel("Valor:");
            labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField campoValor = new JTextField("R$" + valor);
            campoValor.setEditable(false);
        
            JLabel labelSenha = new JLabel("Senha:");
            labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPasswordField campoSenha = new JPasswordField("Valor");
            campoSenha.setEditable(false);
        
            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        

            btnConfirmar.addActionListener(e -> {
                // Função que será executada quando o botão for clicado
                JOptionPane.showMessageDialog(this, "Operacao Confirmada!");
            });

        
            Dimension campoSize = new Dimension(300, 30);
            campoOperacao.setPreferredSize(campoSize);
            campoOperacao.setMaximumSize(campoSize);
            campoValor.setPreferredSize(campoSize);
            campoValor.setMaximumSize(campoSize);
            campoSenha.setPreferredSize(campoSize);
            campoSenha.setMaximumSize(campoSize);
            btnConfirmar.setPreferredSize(new Dimension(150, 30));
            btnConfirmar.setMaximumSize(new Dimension(150, 30));
        
            
            add(Box.createVerticalStrut(60));
            add(labelOperacao);
            add(Box.createVerticalStrut(3));
            add(campoOperacao);
        
            add(Box.createVerticalStrut(15));
            add(labelValor);
            add(Box.createVerticalStrut(3));
            add(campoValor);
        
            add(Box.createVerticalStrut(15));
            add(labelSenha);
            add(Box.createVerticalStrut(3));
            add(campoSenha);
        
            add(Box.createVerticalStrut(15));
            add(btnConfirmar);
        }
        else {
            JLabel labelVazio = new JLabel("Sem operacoes para serem visualizadas!");
            labelVazio.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelVazio.setFont(new Font("Arial", Font.BOLD, 16));
            
            add(Box.createVerticalStrut(250));
            add(labelVazio);
        }
        
    }
}
