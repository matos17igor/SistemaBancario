package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSolicitacao extends JPanel {
    private JTextField campoValorCredito;
    private JTextField campoPrazo;
    private JButton btnConfirmar;
    
    
    public PainelSolicitacao() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
              
        JLabel labelValorCredito = new JLabel("Valor do Crédito:");
        labelValorCredito.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValorCredito = new JTextField();
        
        JLabel labelPrazo = new JLabel("Prazo:");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPrazo = new JTextField();
        
        btnConfirmar = new JButton("Solicitar crédito");

        Dimension campoSize = new Dimension(300, 30); 
        campoValorCredito.setPreferredSize(campoSize);
        campoValorCredito.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);   

        Dimension buttonSize = new Dimension(150, 30); 
        btnConfirmar.setPreferredSize(buttonSize);
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT); 
        btnConfirmar.addActionListener(new InvestirListener());
   
        add(labelValorCredito);
        add(campoValorCredito);
        add(Box.createVerticalStrut(10)); 
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalStrut(20)); 
        add(btnConfirmar);
    }
    
    private class InvestirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValorCredito.getText());
                int prazo = Integer.parseInt(campoPrazo.getText());
          
                if (valor <= 0 || prazo <= 0) {
                    throw new NumberFormatException();
                }
                
                JOptionPane.showMessageDialog(null, "Solicitação realizada com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}