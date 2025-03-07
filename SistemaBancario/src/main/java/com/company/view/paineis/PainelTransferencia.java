/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.exception.PasswordException;
import com.company.exception.SaldoException;
import com.company.model.Cliente;
import com.company.model.Transferencia;
import com.company.persistence.Persistence;
import com.company.persistence.ClientePersistence;
import com.company.persistence.TransferenciaPersistence;
import com.company.view.TelaCliente;
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
    private Cliente cliente;
    private TelaCliente tela;

    public Cliente getCliente() {
        return cliente;
    }

    public PainelTransferencia(Cliente cliente, TelaCliente tela) {
        this.cliente = cliente;
        this.tela = tela;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelContaDestino = new JLabel("Conta de destino:");
        labelContaDestino.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoContaDestino = new JTextField();
        JLabel labelValor = new JLabel("Valor:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField();
        JLabel labelSenha = new JLabel("Senha de transferência:");
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
                String senha = new String(campoSenha.getPassword());

                if (destino.isEmpty() || valor <= 0 || senha.isEmpty()) {
                    throw new NumberFormatException();
                }

                // Buscar conta de destino no sistema
                Persistence<Cliente> clientePersistence = new ClientePersistence();
                List<Cliente> clientes = clientePersistence.findAll();

                Cliente contaDestino = null;
                Cliente contaOrigem = null;

                for (Cliente c : clientes) {
                    if (c.getConta().getNumero().equals(destino)) {
                        contaDestino = c;
                    }
                    if (c.getConta().getNumero().equals(cliente.getConta().getNumero())) {
                        contaOrigem = c;  // Encontramos o cliente de origem dentro da lista
                    }
                }

                if (contaDestino == null || contaOrigem == null) {
                    JOptionPane.showMessageDialog(null, "Conta de destino ou origem não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (contaDestino == contaOrigem) {
                    JOptionPane.showMessageDialog(null, "A conta de destino não pode ser igual a conta de origem.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realizar a transferência
                try {
                    if (contaOrigem.getConta().getSaldo() < valor) {
                        throw new SaldoException();
                    }
                    if (!senha.equals(contaOrigem.getConta().getSenhaTransacao())) {
                        throw new PasswordException();
                    }
                    Transferencia tr = new Transferencia(contaOrigem.getConta(), valor, contaDestino.getConta());
                    TransferenciaPersistence tp = new TransferenciaPersistence();
                    tp.adicionarSolicitacao(tr);

                    JOptionPane.showMessageDialog(null, "Solicitação de transferência realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (SaldoException | PasswordException error) {
                    JOptionPane.showMessageDialog(null, "Erro: " + error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

}
