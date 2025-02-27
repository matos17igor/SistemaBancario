package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelTransferencia extends JPanel {
    public PainelTransferencia() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
              
        JLabel labelContaDestino = new JLabel("Conta de destino:");
        labelContaDestino.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoContaDestino = new JTextField();
        JLabel labelValor = new JLabel("Valor:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValor = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPasswordField campoSenha = new JPasswordField();
        JButton btnConfirmar = new JButton("Confirmar");

        Dimension campoSize = new Dimension(300, 30); 
        campoContaDestino.setPreferredSize(campoSize);
        campoContaDestino.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoSenha.setPreferredSize(campoSize);
        campoSenha.setMaximumSize(campoSize);

        Dimension buttonSize = new Dimension(150, 30); 
        btnConfirmar.setPreferredSize(buttonSize);
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT); 
   
        add(labelContaDestino);
        add(campoContaDestino);
        add(Box.createVerticalStrut(10)); 
        add(labelValor);
        add(campoValor);
        add(Box.createVerticalStrut(10));
        add(labelSenha);
        add(campoSenha);
        add(Box.createVerticalStrut(20)); 
        add(btnConfirmar);
    }
}