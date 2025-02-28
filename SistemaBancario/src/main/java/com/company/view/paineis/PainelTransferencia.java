package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelTransferencia extends JPanel {
    private JTextField campoContaDestino;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton btnConfirmar;
    
    public PainelTransferencia() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
              
        JLabel labelContaDestino = new JLabel("Conta de destino:");
        labelContaDestino.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoContaDestino = new JTextField();
        JLabel labelValor = new JLabel("Valor:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha = new JPasswordField();
        btnConfirmar = new JButton("Confirmar");

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
        btnConfirmar.addActionListener(new InvestirListener());
   
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
    
    private class InvestirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String destino = campoContaDestino.getText();
                double valor = Double.parseDouble(campoValor.getText());
                String senha = String.valueOf(campoSenha.getPassword());
                
                if (destino.equals("")|| valor <= 0 || senha.equals("")) {
                    throw new NumberFormatException();
                }
                
                JOptionPane.showMessageDialog(null, "Investimento realizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}