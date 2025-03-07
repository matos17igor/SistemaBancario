/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.exception.PasswordException;
import com.company.exception.SaldoException;
import com.company.model.Cliente;
import com.company.model.Conta;
import com.company.model.Saque;
import com.company.persistence.ClientePersistence;
import com.company.persistence.Persistence;
import com.company.persistence.SaquePersistence;
import com.company.view.TelaCaixa;
import com.company.view.TelaCliente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelSaque extends JPanel {
    private JTextField campoValor;
    private JPasswordField campoSenha;
    private JButton btnConfirmar;
    private Cliente cliente;
    private TelaCliente tela;

    public PainelSaque(Cliente cliente, TelaCliente tela) {
        this.cliente = cliente;
        this.tela = tela;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel labelValor = new JLabel("Valor do saque:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField();
        JLabel labelSenha = new JLabel("Senha de transferência:");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha = new JPasswordField();
        btnConfirmar = new JButton("Confirmar");

        Dimension campoSize = new Dimension(300, 30);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoSenha.setPreferredSize(campoSize);
        campoSenha.setMaximumSize(campoSize);
        
        Dimension buttonSize = new Dimension(150, 30); 
        btnConfirmar.setPreferredSize(buttonSize);
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.addActionListener(new SaqueListener());
        
        add(labelValor);
        add(campoValor);
        add(Box.createVerticalStrut(10));
        add(labelSenha);
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
                
                // Buscar conta de destino no sistema
                Persistence<Cliente> clientePersistence = new ClientePersistence();
                List<Cliente> clientes = clientePersistence.findAll();

                Cliente contaOrigem = null;
                
                for (Cliente c : clientes) {
                    if (c.getConta().getNumero().equals(cliente.getConta().getNumero())) {
                        contaOrigem = c;  // Encontramos o cliente de origem dentro da lista
                    }
                }

                try {
                    if (contaOrigem.getConta().getSaldo() < valor) {
                        throw new SaldoException();
                    }
                    if (!senha.equals(contaOrigem.getConta().getSenhaTransacao())) {
                        throw new PasswordException();
                    }

                    Saque sq = new Saque(contaOrigem.getConta(), valor);
                    SaquePersistence sp = new SaquePersistence();
                    sp.adicionarSolicitacao(sq);

                    JOptionPane.showMessageDialog(null, "Solicitação de saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                } catch (SaldoException | PasswordException error) {
                    JOptionPane.showMessageDialog(null, "Erro: " + error.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
