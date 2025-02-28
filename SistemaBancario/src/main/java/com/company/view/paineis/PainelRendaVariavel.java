package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelRendaVariavel extends JPanel {
    private JTextField campoValorInvestir;
    private JTextField campoPrazo;
    private JComboBox campoRiscoEstimado;
    private JButton btnInvestir;
    
    public PainelRendaVariavel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelValorInvestir = new JLabel("Valor a Investir:");
        labelValorInvestir.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValorInvestir = new JTextField();

        JLabel labelPrazo = new JLabel("Prazo (meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPrazo = new JTextField();
        
        JLabel labelRiscoEstimado = new JLabel("Risco estimado:");
        labelRiscoEstimado.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoRiscoEstimado = new JComboBox<>(new String[]{"Baixo", "Médio", "Alto"});
        
        btnInvestir = new JButton("Investir");
        btnInvestir.addActionListener(new InvestirListener());

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
    
    private class InvestirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValorInvestir.getText());
                int prazo = Integer.parseInt(campoPrazo.getText());
                String risco = campoRiscoEstimado.getSelectedItem().toString();
                
                if (valor <= 0 || prazo <= 0) {
                    throw new NumberFormatException();
                }
                JOptionPane.showMessageDialog(null, "Investimento de " + risco.toLowerCase() + " risco realizado com sucesso!","Confirmação", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}