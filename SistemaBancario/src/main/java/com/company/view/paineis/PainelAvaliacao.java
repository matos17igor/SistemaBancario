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
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelAvaliacao extends JPanel {

    private JComboBox<Credito> comboSolicitacoes;
    private JButton btnConfirmar;
    private JButton btnNegar;
    private static JTextField campoTipo;
    private static JTextField campoValorCredito;
    private static JTextField campoEntrada;
    private static JTextField campoPrazo;
    private static JTextField campoJuros;

    public PainelAvaliacao() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboSolicitacoes = new JComboBox<Credito>();
        btnConfirmar = new JButton("Aprovar Crédito");
        btnNegar = new JButton("Negar Crédito");
        campoPrazo = new JTextField();
        campoEntrada = new JTextField();
        campoTipo = new JTextField();
        campoValorCredito = new JTextField();
        campoJuros = new JTextField();

        JLabel labelSolicitacoes = new JLabel("Solicitações Pendentes:");
        labelSolicitacoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelTipo = new JLabel("Tipo:");
        labelTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelValorCredito = new JLabel("Valor do Crédito:");
        labelValorCredito.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelEntrada = new JLabel("Entrada:");
        labelEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelPrazo = new JLabel("Parcelas:");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelJuros = new JLabel("Definir juros:");
        labelJuros.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        carregarSolicitacoes();
        comboSolicitacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Credito selecionado = (Credito) comboSolicitacoes.getSelectedItem();
                onComboBoxChange(selecionado);
            }
        });

        Dimension campoSize = new Dimension(300, 30);
        comboSolicitacoes.setMaximumSize(campoSize);
        comboSolicitacoes.setPreferredSize(campoSize);
        campoTipo.setPreferredSize(campoSize);
        campoTipo.setMaximumSize(campoSize);
        campoValorCredito.setPreferredSize(campoSize);
        campoValorCredito.setMaximumSize(campoSize);
        campoEntrada.setPreferredSize(campoSize);
        campoEntrada.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoJuros.setPreferredSize(campoSize);
        campoJuros.setMaximumSize(campoSize);

        campoTipo.setEditable(false);
        campoValorCredito.setEditable(false);
        campoEntrada.setEditable(false);
        campoPrazo.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(comboSolicitacoes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(campoSize);
        scrollPane.setPreferredSize(campoSize);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConfirmar.addActionListener(new AprovarCreditoListener());
        btnNegar.addActionListener(e -> {
            int index = comboSolicitacoes.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma solicitação", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, "Crédito negado!", "Mensagem", JOptionPane.ERROR_MESSAGE);
            CreditoPersistence cp = new CreditoPersistence();
            Credito credito = cp.getSolicitacoes().get(index);
            cp.removerSolicitacao(credito);
            carregarSolicitacoes();
        });
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.setPreferredSize(new Dimension(150, 30));
        btnNegar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNegar.setPreferredSize(new Dimension(150, 30));

        add(labelSolicitacoes);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
        add(labelTipo);
        add(campoTipo);
        add(Box.createVerticalStrut(10));
        add(labelValorCredito);
        add(campoValorCredito);
        add(Box.createVerticalStrut(10));
        add(labelEntrada);
        add(campoEntrada);
        add(Box.createVerticalStrut(10));
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(labelJuros);
        add(campoJuros);
        add(Box.createVerticalStrut(10));
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
        add(Box.createVerticalStrut(10));
        add(btnNegar);
    }

    private static void onComboBoxChange(Credito novoCredito) {
        if (novoCredito != null) {
            campoTipo.setText(String.valueOf(novoCredito.getTipo()));
            campoValorCredito.setText(String.valueOf(novoCredito.getValor()));
            campoEntrada.setText(String.valueOf(novoCredito.getEntrada()));
            campoPrazo.setText(String.valueOf(novoCredito.getParcelas()));
        } else {
            // Limpa os campos caso a combobox esteja vazia
            campoTipo.setText("");
            campoValorCredito.setText("");
            campoEntrada.setText("");
            campoPrazo.setText("");
            campoJuros.setText("");
        }
    }

    private void carregarSolicitacoes() {
        comboSolicitacoes.removeAllItems();
        CreditoPersistence cp = new CreditoPersistence();
        List<Credito> solicitacoes = cp.getSolicitacoes();

        for (Credito c : solicitacoes) {
            comboSolicitacoes.addItem(c);
        }
    }

    private class AprovarCreditoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = comboSolicitacoes.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma solicitação", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CreditoPersistence cp = new CreditoPersistence();
            Credito credito = cp.getSolicitacoes().get(index);

            // Solicita a senha do cliente
            String senhaDigitada = JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:");

            if (senhaDigitada.isEmpty() || !senhaDigitada.equals(credito.getCliente().getConta().getSenhaTransacao())) {
                JOptionPane.showMessageDialog(null, "Senha inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ClientePersistence clp = new ClientePersistence();
            List<Cliente> clientes = clp.findAll();
            Cliente cliente = null;
            for (Cliente c : clientes) {
                if (c.getId() == (credito.getCliente().getId())) {
                    cliente = c;
                }
            }

            if (campoTipo.getText().equals("Empréstimo")) {
                cliente.getConta().setSaldo(cliente.getConta().getSaldo() + Double.parseDouble(campoValorCredito.getText()));
            }
            if (campoTipo.getText().equals("Financiamento")) {
                cliente.getConta().setSaldo(cliente.getConta().getSaldo() - Double.parseDouble(campoEntrada.getText()));
            }
            clp.save(clientes);

            // Aprovação da transferência pelo caixa
            JOptionPane.showMessageDialog(null, "Crédito aprovado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            cp.removerSolicitacao(credito);
            carregarSolicitacoes();
        }
    }
}
