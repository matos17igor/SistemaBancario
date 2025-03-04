package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelTransferenciaCaixa extends JPanel {
    private JTextField campoContaOrigem;
    private JTextField campoContaDestino;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton btnConfirmar;
    
    public PainelTransferenciaCaixa() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        campoContaOrigem = new JTextField();
        campoContaOrigem.setEditable(false);
        campoContaDestino = new JTextField();
        campoContaDestino.setEditable(false);
        campoValor = new JTextField();
        campoValor.setEditable(false);
        campoSenha = new JPasswordField();
        campoSenha.setEditable(false);
        btnConfirmar = new JButton("Confirmar");

        configurarComponentes();
        btnConfirmar.addActionListener(new TransferenciaListener());
    }

    private void configurarComponentes() {
        Dimension campoSize = new Dimension(300, 30);
        campoContaOrigem.setPreferredSize(campoSize);
        campoContaOrigem.setMaximumSize(campoSize);
        campoContaDestino.setPreferredSize(campoSize);
        campoContaDestino.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoSenha.setPreferredSize(campoSize);
        campoSenha.setMaximumSize(campoSize);

        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(new JLabel("Conta de origem:"));
        add(campoContaOrigem);
        add(new JLabel("Conta de destino:"));
        add(campoContaDestino);
        add(new JLabel("Valor:"));
        add(campoValor);
        add(new JLabel("Senha de transferência:"));
        add(campoSenha);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private class TransferenciaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String origem = campoContaOrigem.getText();
                String destino = campoContaDestino.getText();
                double valor = Double.parseDouble(campoValor.getText());
                String senha = new String(campoSenha.getPassword());
                
                if (origem.isEmpty() || destino.isEmpty() || valor <= 0 || senha.isEmpty()) {
                    throw new NumberFormatException();
                }

                // Implementar lógica de transferência via caixa
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valores inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}