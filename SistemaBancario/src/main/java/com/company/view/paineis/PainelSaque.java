package com.company.view.paineis;

import com.company.exception.PasswordException;
import com.company.exception.SaldoException;
import com.company.model.Cliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSaque extends JPanel {
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton btnConfirmar;
    private Cliente cliente;

    public PainelSaque() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelValor = new JLabel("Valor do saque:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField();
        campoValor.setEditable(false);
        JLabel labelSenha = new JLabel("Senha de transferência:");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha = new JPasswordField();
        campoSenha.setEditable(false);
        btnConfirmar = new JButton("Confirmar");

        configurarComponentes();
        btnConfirmar.addActionListener(new SaqueListener());
    }

    private void configurarComponentes() {
        Dimension campoSize = new Dimension(300, 30);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoSenha.setPreferredSize(campoSize);
        campoSenha.setMaximumSize(campoSize);

        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(new JLabel("Valor do saque:"));
        add(campoValor);
        add(Box.createVerticalStrut(10));
        add(new JLabel("Senha de transferência:"));
        add(campoSenha);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private class SaqueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                String senha = new String(campoSenha.getPassword());

                if (valor <= 0 || senha.isEmpty()) {
                    throw new NumberFormatException();
                }

                cliente.realizaTransferencia(valor, cliente.getConta(), senha);
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SaldoException | PasswordException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
