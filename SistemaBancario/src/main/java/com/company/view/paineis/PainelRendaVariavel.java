package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelRendaVariavel extends JPanel {
    public PainelRendaVariavel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelValorInvestir = new JLabel("Valor a Investir:");
        labelValorInvestir.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValorInvestir = new JTextField();

        JLabel labelPrazo = new JLabel("Prazo (meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField();
        
        JLabel labelRiscoEstimado = new JLabel("Risco estimado:");
        labelRiscoEstimado.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComboBox campoRiscoEstimado = new JComboBox<>(new String[]{"Baixo", "Médio", "Alto"});
        
        JButton btnInvestir = new JButton("Investir");

        Dimension campoSize = new Dimension(300, 30);
        campoValorInvestir.setPreferredSize(campoSize);
        campoValorInvestir.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoRiscoEstimado.setPreferredSize(campoSize);
        campoRiscoEstimado.setMaximumSize(campoSize);

        Dimension buttonSize = new Dimension(150, 30);
        btnInvestir.setPreferredSize(buttonSize);
        btnInvestir.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(labelValorInvestir);
        add(campoValorInvestir);
        add(Box.createVerticalStrut(10));
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(labelRiscoEstimado);
        add(campoRiscoEstimado);
        add(Box.createVerticalStrut(20));
        add(btnInvestir);
    }
}