/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.Movimentacao;
import com.company.model.Transferencia;
import com.company.persistence.ClientePersistence;
import com.company.persistence.Persistence;
import com.company.persistence.TransferenciaPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelTransferenciaCaixa extends JPanel {

    private JComboBox<String> comboSolicitacoes;
    private JButton btnConfirmar;

    public PainelTransferenciaCaixa() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        comboSolicitacoes = new JComboBox<>();
        btnConfirmar = new JButton("Aprovar Transferência");
        JLabel labelSolicitacoes = new JLabel("Solicitações Pendentes:");
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

        btnConfirmar.addActionListener(new AprovarTransferenciaListener());
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
        List<Transferencia> solicitacoes = TransferenciaPersistence.getSolicitacoes();
        for (Transferencia t : solicitacoes) {
            comboSolicitacoes.addItem("Origem: " + t.getOrigem().getNumero()
                    + " | Destino: " + t.getDestino().getNumero()
                    + " | Valor: R$" + t.getValor());
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
                List<Cliente> clientes = clientePersistence.findAll();

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
}
