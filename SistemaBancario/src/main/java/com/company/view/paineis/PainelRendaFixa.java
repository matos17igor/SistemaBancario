package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelRendaFixa extends JPanel {
    private JTextField campoValorInvestir;
    private JTextField campoPrazo;
    private JTextField campoTaxaRentabilidade;
    private JButton btnInvestir;
    
    public PainelRendaFixa() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelValorInvestir = new JLabel("Valor a Investir:");
        labelValorInvestir.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValorInvestir = new JTextField();

        JLabel labelPrazo = new JLabel("Prazo (meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPrazo = new JTextField();

        JLabel labelTaxaRentabilidade = new JLabel("Taxa de Rentabilidade (%):");
        labelTaxaRentabilidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTaxaRentabilidade = new JTextField();

        btnInvestir = new JButton("Investir");
        btnInvestir.addActionListener(new InvestirListener());

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
    
    private class InvestirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValorInvestir.getText());
                int prazo = Integer.parseInt(campoPrazo.getText());
                double taxa = Double.parseDouble(campoTaxaRentabilidade.getText());
                
                if (valor <= 0 || prazo <= 0 || taxa <= 0) {
                    throw new NumberFormatException();
                }
                
                JOptionPane.showMessageDialog(null, "Investimento realizado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}