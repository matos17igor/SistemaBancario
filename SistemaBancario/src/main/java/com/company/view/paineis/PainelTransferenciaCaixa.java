package com.company.view.paineis;

import com.company.model.Transferencia;
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
            comboSolicitacoes.addItem("Cliente: " + t.getOrigem().getTitular() +
                                      " | Destino: " + t.getDestino().getTitular() + 
                                      " | Valor: R$" + t.getValor());
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

            TransferenciaPersistence tp = new TransferenciaPersistence();
            Transferencia transferencia = tp.getSolicitacoes().get(index);
            
            // Solicita a senha do cliente
            String senhaDigitada = JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:");

            if (senhaDigitada.isEmpty() || !senhaDigitada.equals(transferencia.getOrigem().getSenhaTransacao())) {
                JOptionPane.showMessageDialog(null, "Senha inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aprovação da transferência pelo caixa
                JOptionPane.showMessageDialog(null, "Transferência aprovada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                TransferenciaPersistence.removerSolicitacao(transferencia);
                carregarSolicitacoes();
        }
    }
}
