package com.company.view.paineis;

import com.company.exception.PasswordException;
import com.company.exception.SaldoException;
import com.company.model.Cliente;
import com.company.model.Conta;
import com.company.persistence.Persistence;
import com.company.persistence.ClientePersistence;
import com.company.persistence.ContaPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelTransferencia extends JPanel {

    private JTextField campoContaDestino;
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton btnConfirmar;

    public PainelTransferencia() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelContaDestino = new JLabel("Conta de destino:");
        labelContaDestino.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoContaDestino = new JTextField();
        JLabel labelValor = new JLabel("Valor:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha = new JPasswordField();
        btnConfirmar = new JButton("Confirmar");

        Dimension campoSize = new Dimension(300, 30);
        campoContaDestino.setPreferredSize(campoSize);
        campoContaDestino.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoSenha.setPreferredSize(campoSize);
        campoSenha.setMaximumSize(campoSize);

        Dimension buttonSize = new Dimension(150, 30);
        btnConfirmar.setPreferredSize(buttonSize);
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.addActionListener(new InvestirListener());

        add(labelContaDestino);
        add(campoContaDestino);
        add(Box.createVerticalStrut(10));
        add(labelValor);
        add(campoValor);
        add(Box.createVerticalStrut(10));
        add(labelSenha);
        add(campoSenha);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private class InvestirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String destino = campoContaDestino.getText();
                double valor = Double.parseDouble(campoValor.getText());
                String senha = String.valueOf(campoSenha.getPassword());

                if (destino.isEmpty() || valor <= 0 || senha.isEmpty()) {
                    throw new NumberFormatException();
                }

                // Busca cliente autenticado
                Persistence<Cliente> clientePersistence = new ClientePersistence();
                List<Cliente> clientes = clientePersistence.findAll();

                Cliente clienteLogado = null;
                for (Cliente c : clientes) {
                    if (c.getSenha().equals(senha)) {
                        clienteLogado = c;
                        break;
                    }
                }

                if (clienteLogado == null) {
                    JOptionPane.showMessageDialog(null, "Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Busca conta de destino
                Persistence<Conta> contaPersistence = new ContaPersistence();
                List<Conta> contas = contaPersistence.findAll();

                Conta contaDestino = null;
                for (Conta conta : contas) {
                    if (conta.getNumero().trim().equals(destino.trim())) {
                        contaDestino = conta;
                        break;
                    }
                }

                if (contaDestino == null) {
                    JOptionPane.showMessageDialog(null, "Conta de destino não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realiza a transferencia
                try {
                    clienteLogado.realizaTransferencia(valor, contaDestino, senha);
                    JOptionPane.showMessageDialog(null, "Investimento realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } catch (SaldoException | PasswordException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
