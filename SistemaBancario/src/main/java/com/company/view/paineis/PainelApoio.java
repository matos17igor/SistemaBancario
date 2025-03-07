/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
 */
package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.Movimentacao;
import com.company.model.Saque;
import com.company.model.Transferencia;
import com.company.persistence.ClientePersistence;
import com.company.persistence.Persistence;
import com.company.persistence.SaquePersistence;
import com.company.persistence.TransferenciaPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelApoio extends JPanel {

    private JComboBox<String> comboSolicitacoes;
    private JComboBox<String> comboSaques;
    private JButton btnConfirmar;
    private JButton btnConfirmarSaque;

    public PainelApoio() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboSolicitacoes = new JComboBox<>();
        comboSaques = new JComboBox<>();
        btnConfirmar = new JButton("Aprovar Transferência");
        btnConfirmarSaque = new JButton("Aprovar Saque");
        JLabel labelSolicitacoes = new JLabel("Transferências Pendentes:");
        labelSolicitacoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelSaques = new JLabel("Saques Pendentes:");
        labelSaques.setAlignmentX(Component.CENTER_ALIGNMENT);

        carregarSolicitacoes();
        carregarSaques();

        Dimension comboSize = new Dimension(300, 30);
        comboSolicitacoes.setMaximumSize(comboSize);
        comboSolicitacoes.setPreferredSize(comboSize);
        comboSaques.setMaximumSize(comboSize);
        comboSaques.setPreferredSize(comboSize);

        JScrollPane scrollPane = new JScrollPane(comboSolicitacoes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(comboSize);
        scrollPane.setPreferredSize(comboSize);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane scrollPaneS = new JScrollPane(comboSaques);
        scrollPaneS.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPaneS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneS.setMaximumSize(comboSize);
        scrollPaneS.setPreferredSize(comboSize);
        scrollPaneS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConfirmar.addActionListener(new AprovarTransferenciaListener());
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.setPreferredSize(new Dimension(150, 30));

        btnConfirmarSaque.addActionListener(new AprovarSaqueListener());
        btnConfirmarSaque.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmarSaque.setPreferredSize(new Dimension(150, 30));

        add(labelSolicitacoes);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
        add(Box.createVerticalStrut(20));
        add(labelSaques);
        add(Box.createVerticalStrut(10));
        add(scrollPaneS);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
        add(Box.createVerticalStrut(10));
        add(btnConfirmarSaque);
    }

    private void carregarSolicitacoes() {
        comboSolicitacoes.removeAllItems();
        java.util.List<Transferencia> solicitacoes = TransferenciaPersistence.getSolicitacoes();
        for (Transferencia t : solicitacoes) {
            if(t.getValor() >= 1000000){
                comboSolicitacoes.addItem("Origem: " + t.getOrigem().getNumero()
                        + " | Destino: " + t.getDestino().getNumero()
                        + " | Valor: R$" + t.getValor());
            }
        }
    }

    private void carregarSaques() {
        comboSaques.removeAllItems();
        java.util.List<Saque> solicitacoes = SaquePersistence.getSolicitacoes();
        for (Saque s : solicitacoes) {
            if (s.getValor() >= 1000000) {
                comboSaques.addItem("Origem: " + s.getOrigem().getNumero()
                        + " | Valor: R$" + s.getValor());
            }
        }
    }

    private class AprovarTransferenciaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = comboSolicitacoes.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma solicitação para aprovar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Transferencia transferencia = TransferenciaPersistence.getSolicitacoes().get(index);

            // Solicita a senha do cliente
            String senhaDigitada = JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:");

            if (senhaDigitada.isEmpty() || !senhaDigitada.equals(transferencia.getOrigem().getSenhaTransacao())) {
                JOptionPane.showMessageDialog(null, "Senha inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar conta de destino no sistema
            Persistence<Cliente> clientePersistence = new ClientePersistence();
            java.util.List<Cliente> clientes = clientePersistence.findAll();

            Cliente contaDestino = null;
            Cliente contaOrigem = null;

            for (Cliente c : clientes) {
                if (c.getConta().getNumero().equals(transferencia.getDestino().getNumero())) {
                    contaDestino = c;
                }
                if (c.getConta().getNumero().equals(transferencia.getOrigem().getNumero())) {
                    contaOrigem = c;  // Encontramos o cliente de origem dentro da lista
                }
            }

            // Aprovação da transferência pelo caixa
            contaOrigem.getConta().setSaldo(contaOrigem.getConta().getSaldo() - transferencia.getValor());
            contaDestino.getConta().setSaldo(contaDestino.getConta().getSaldo() + transferencia.getValor());

            // Adiciona a movimentacao
            Movimentacao movimentacao = new Movimentacao(transferencia.getValor(), "Transferência", contaOrigem.getConta().getTitular());
            contaOrigem.getConta().setMovimentacoes(movimentacao);
            contaDestino.getConta().setMovimentacoes(movimentacao);

            JOptionPane.showMessageDialog(null, "Transferência aprovada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            TransferenciaPersistence.removerSolicitacao(transferencia);
            clientePersistence.save(clientes);
            carregarSolicitacoes();
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
            java.util.List<Cliente> clientes = clientePersistence.findAll();

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
