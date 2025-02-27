package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelSolicitacao extends JPanel {
    public PainelSolicitacao() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
              
        JLabel labelValorCredito = new JLabel("Valor do Crédito:");
        labelValorCredito.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValorCredito = new JTextField();
        JLabel labelPrazo = new JLabel("Prazo:");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField();
        JButton btnConfirmar = new JButton("Solicitar crédito");

        Dimension campoSize = new Dimension(300, 30); 
        campoValorCredito.setPreferredSize(campoSize);
        campoValorCredito.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);   

        Dimension buttonSize = new Dimension(150, 30); 
        btnConfirmar.setPreferredSize(buttonSize);
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT); 
   
        add(labelValorCredito);
        add(campoValorCredito);
        add(Box.createVerticalStrut(10)); 
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalStrut(20)); 
        add(btnConfirmar);
    }
}