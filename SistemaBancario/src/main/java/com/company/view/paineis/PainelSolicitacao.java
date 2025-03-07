/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.Credito;
import com.company.persistence.ClientePersistence;
import com.company.persistence.CreditoPersistence;
import com.company.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelSolicitacao extends JPanel {

    private JComboBox<String> comboTipo;
    private JTextField campoValorCredito;
    private static JTextField campoEntrada;
    private JTextField campoPrazo;
    private JButton btnConfirmar;
    private Cliente cliente;

    public PainelSolicitacao(Cliente cliente) {
        this.cliente = cliente;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        comboTipo = new JComboBox<>();
        comboTipo.addItem("Financiamento");
        comboTipo.addItem("Empréstimo");
        JLabel labelValorCredito = new JLabel("Valor do Crédito:");
        labelValorCredito.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValorCredito = new JTextField();
        JLabel labelPrazo = new JLabel("Parcelas:");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPrazo = new JTextField();
        btnConfirmar = new JButton("Solicitar crédito");
        JLabel labelEntrada = new JLabel("Entrada:");
        labelEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoEntrada = new JTextField();
        btnConfirmar = new JButton("Solicitar crédito");

        Dimension comboSize = new Dimension(300, 30);
        comboTipo.setMaximumSize(comboSize);
        comboTipo.setPreferredSize(comboSize);
        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onComboBoxChange(String.valueOf(comboTipo.getSelectedItem()));
            }
        });
        Dimension campoSize = new Dimension(300, 30);
        campoValorCredito.setPreferredSize(campoSize);
        campoValorCredito.setMaximumSize(campoSize);
        campoEntrada.setPreferredSize(campoSize);
        campoEntrada.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);

        Dimension buttonSize = new Dimension(150, 30);
        btnConfirmar.setPreferredSize(buttonSize);
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.addActionListener(new InvestirListener());

        add(comboTipo);
        add(labelValorCredito);
        add(campoValorCredito);
        add(Box.createVerticalStrut(10));
        add(labelEntrada);
        add(campoEntrada);
        add(Box.createVerticalStrut(10));
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private static void onComboBoxChange(String tipo) {
        if (tipo.equals("Empréstimo")) {
            campoEntrada.setEditable(false);
        }
        if (tipo.equals("Financiamento")) {
            campoEntrada.setEditable(true);
        }
    }

    private class InvestirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String tipo = comboTipo.getSelectedItem().toString();
                double valor = Double.parseDouble(campoValorCredito.getText());
                double entrada = Double.parseDouble(campoEntrada.getText());
                int parcelas = Integer.parseInt(campoPrazo.getText());

                if (valor <= 0 || entrada < 0 || parcelas <= 0 || tipo.isEmpty()) {
                    throw new NumberFormatException();
                }

                // Buscar conta de destino no sistema
                Persistence<Cliente> clientePersistence = new ClientePersistence();
                List<Cliente> clientes = clientePersistence.findAll();

                Cliente clienteOrigem = null;

                for (Cliente c : clientes) {
                    if (c.getConta().getNumero().equals(cliente.getConta().getNumero())) {
                        clienteOrigem = c;  // Encontramos o cliente de origem dentro da lista
                    }
                }

                Credito c = new Credito(tipo, clienteOrigem, valor, entrada, parcelas, 0);
                CreditoPersistence cp = new CreditoPersistence();
                cp.adicionarSolicitacao(c);

                // Salvar todas as mudanças
                clientePersistence.save(clientes);

                JOptionPane.showMessageDialog(null, "Solicitação realizada com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, insira valores válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
