package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelRendaFixa extends JPanel {
    public PainelRendaFixa() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelValorInvestir = new JLabel("Valor a Investir:");
        labelValorInvestir.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValorInvestir = new JTextField();

        JLabel labelPrazo = new JLabel("Prazo (meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField();

        JLabel labelTaxaRentabilidade = new JLabel("Taxa de Rentabilidade (%):");
        labelTaxaRentabilidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoTaxaRentabilidade = new JTextField();

        JButton btnInvestir = new JButton("Investir");

        Dimension campoSize = new Dimension(300, 30);
        campoValorInvestir.setPreferredSize(campoSize);
        campoValorInvestir.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoTaxaRentabilidade.setPreferredSize(campoSize);
        campoTaxaRentabilidade.setMaximumSize(campoSize);

        Dimension buttonSize = new Dimension(150, 30);
        btnInvestir.setPreferredSize(buttonSize);
        btnInvestir.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(labelValorInvestir);
        add(campoValorInvestir);
        add(Box.createVerticalStrut(10));
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(labelTaxaRentabilidade);
        add(campoTaxaRentabilidade);
        add(Box.createVerticalStrut(20));
        add(btnInvestir);
    }
}