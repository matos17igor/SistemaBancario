package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.Movimentacao;
import com.company.model.Saque;
import com.company.persistence.ClientePersistence;
import com.company.persistence.Persistence;
import com.company.persistence.SaquePersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelSaqueCaixa extends JPanel {

    private JComboBox<String> comboSolicitacoes;
    private JButton btnConfirmar;

    public PainelSaqueCaixa() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboSolicitacoes = new JComboBox<>();
        btnConfirmar = new JButton("Aprovar Saque");
        JLabel labelSolicitacoes = new JLabel("Saques Pendentes:");
        labelSolicitacoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        carregarSolicitacoes();

        Dimension comboSize = new Dimension(300, 30);
        comboSolicitacoes.setMaximumSize(comboSize);
        comboSolicitacoes.setPreferredSize(comboSize);

        JScrollPane scrollPane = new JScrollPane(comboSolicitacoes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(comboSize);
        scrollPane.setPreferredSize(comboSize);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConfirmar.addActionListener(new AprovarSaqueListener());
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.setPreferredSize(new Dimension(150, 30));

        add(labelSolicitacoes);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private void carregarSolicitacoes() {
        comboSolicitacoes.removeAllItems();
        List<Saque> solicitacoes = SaquePersistence.getSolicitacoes();
        for (Saque s : solicitacoes) {
            if (s.getValor() < 1000000) {
                comboSolicitacoes.addItem("Origem: " + s.getOrigem().getNumero()
                        + " | Valor: R$" + s.getValor());
            }
        }
    }

    private class AprovarSaqueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = comboSolicitacoes.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma solicitação de saque para aprovar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Saque saque = SaquePersistence.getSolicitacoes().get(index);

            // Solicita a senha do cliente
            String senhaDigitada = JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:");

            // Buscar conta de destino no sistema
            Persistence<Cliente> clientePersistence = new ClientePersistence();
            List<Cliente> clientes = clientePersistence.findAll();

            Cliente clienteOrigem = null;

            for (Cliente c : clientes) {
                if (c.getConta().getNumero().equals(saque.getOrigem().getNumero())) {
                    clienteOrigem = c;  // Encontramos o cliente de origem dentro da lista
                }
            }

            if (clienteOrigem.getConta().getSaldo() < saque.getValor()) {
                JOptionPane.showMessageDialog(null, "Saldo insufieciente!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (senhaDigitada.isEmpty() || !senhaDigitada.equals(saque.getOrigem().getSenhaTransacao())) {
                JOptionPane.showMessageDialog(null, "Senha inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aprovação da transferência pelo caixa
            clienteOrigem.getConta().setSaldo(clienteOrigem.getConta().getSaldo() - saque.getValor());

            // Adiciona a movimentacao
            Movimentacao movimentacao = new Movimentacao(saque.getValor(), "Saque", clienteOrigem.getConta().getTitular());
            clienteOrigem.getConta().setMovimentacoes(movimentacao);

            JOptionPane.showMessageDialog(null, "Saque aprovado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            SaquePersistence.removerSolicitacao(saque);
            clientePersistence.save(clientes);

            carregarSolicitacoes();
        }
    }
}
