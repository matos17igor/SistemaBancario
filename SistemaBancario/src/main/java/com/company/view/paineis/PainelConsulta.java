package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.Movimentacao;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelConsulta extends JPanel {

    private JLabel lblSaldo;
    private JTextArea txtExtrato;
    private JScrollPane scrollPane;

    public PainelConsulta(Cliente cliente) {
        setLayout(new BorderLayout());
        lblSaldo = new JLabel("Saldo atual: R$" + cliente.getConta().getSaldo(), JLabel.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblSaldo, BorderLayout.NORTH);

        txtExtrato = new JTextArea(20, 5);  
        txtExtrato.setEditable(false);  
        txtExtrato.setFont(new Font("Arial", Font.PLAIN, 14)); 

        // Preenche a tabela com as movimentações
        List<Movimentacao> movimentacoes = cliente.getConta().getMovimentacoes();
        StringBuilder extrato = new StringBuilder("Extrato de Movimentações:\n\n");
        for (Movimentacao movimentacao : movimentacoes) {
            extrato.append("Data: ").append(movimentacao.getData()).append("\n");
            extrato.append("Valor: R$").append(movimentacao.getValor()).append("\n");
            extrato.append("Descrição: ").append(movimentacao.getDescricao()).append("\n");
            extrato.append("Origem: ").append(movimentacao.getOrigem()).append("\n");
            extrato.append("-----------------------------\n");
        }

        txtExtrato.setText(extrato.toString());

        scrollPane = new JScrollPane(txtExtrato);
        add(scrollPane, BorderLayout.CENTER);
    }
}
