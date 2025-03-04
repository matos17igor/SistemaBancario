package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelDeposito extends JPanel {
    private JTextField campoValor;
    private JButton btnConfirmar;

    public PainelDeposito() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JTextField labelValor = new JTextField("Valor do depósito:");
        campoValor = new JTextField();
        campoValor.setEditable(false);
        btnConfirmar = new JButton("Confirmar");

        configurarComponentes();
        btnConfirmar.addActionListener(new DepositoListener());
    }

    private void configurarComponentes() {
        Dimension campoSize = new Dimension(300, 30);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);

        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(new JLabel("Valor do depósito:"));
        add(campoValor);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private class DepositoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                if (valor <= 0) {
                    throw new NumberFormatException();
                }
                //cliente.getConta().setSaldo(cliente.getConta().getSaldo() + valor);
                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
