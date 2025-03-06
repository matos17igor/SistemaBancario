package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.Conta;
import com.company.persistence.ClientePersistence;
import com.company.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelDeposito extends JPanel {
    private JTextField campoValor;
    private JButton btnConfirmar;
    private JTextField contaDestino;

    public PainelDeposito() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        campoValor = new JTextField();
        contaDestino = new JTextField();
        btnConfirmar = new JButton("Confirmar");

        configurarComponentes();
        btnConfirmar.addActionListener(new DepositoListener());
    }

    private void configurarComponentes() {
        Dimension campoSize = new Dimension(300, 30);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        contaDestino.setAlignmentX(Component.CENTER_ALIGNMENT);
        contaDestino.setPreferredSize(campoSize);
        contaDestino.setMaximumSize(campoSize);

        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelValor = new JLabel("Valor do depósito:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelConta = new JLabel("Conta de destino:");
        labelConta.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(labelConta);
        add(contaDestino);
        add(labelValor);
        add(campoValor);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private class DepositoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                String numeroConta = contaDestino.getText().trim();

                if (valor <= 0 || numeroConta.isEmpty()) {
                    throw new NumberFormatException();
                }

                // Buscar conta de destino no sistema
                Persistence<Cliente> clientePersistence = new ClientePersistence();
                List<Cliente> clientes = clientePersistence.findAll();

                Cliente contaDeposito = null;
                for (Cliente c : clientes) {
                    if (c.getConta().getNumero().equals(numeroConta)) {
                        contaDeposito = c;
                        break;
                    }
                }

                if (contaDeposito == null) {
                    JOptionPane.showMessageDialog(null, "Conta de destino não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realizar o depósito
                contaDeposito.getConta().setSaldo(contaDeposito.getConta().getSaldo() + valor);
                clientePersistence.save(clientes);
                System.out.println("Saldo novo: " + contaDeposito.getConta().getSaldo());

                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
