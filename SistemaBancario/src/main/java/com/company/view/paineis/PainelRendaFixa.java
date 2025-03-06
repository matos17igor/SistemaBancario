package com.company.view.paineis;

import com.company.model.Cliente;
import com.company.model.InvestimentoRendaFixa;
import com.company.persistence.InvestimentoRendaFixaPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PainelRendaFixa extends JPanel {

    private JComboBox<InvestimentoRendaFixa> comboInvestimentos;
    private JButton btnConfirmar;
    private static JTextField campoValorMin;
    private static JTextField campoTaxa;
    private static JTextField campoPrazo;
    private static JTextField campoValor;
    private Cliente cliente;

    public PainelRendaFixa(Cliente cliente) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.cliente = cliente;
        
        comboInvestimentos = new JComboBox<>();
        btnConfirmar = new JButton("Investir");
        campoValorMin = new JTextField();
        campoTaxa = new JTextField();
        campoPrazo = new JTextField();
        campoValor = new JTextField();

        JLabel labelInvestimentos = new JLabel("Investimento cadastrados:");
        labelInvestimentos.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelValorMin = new JLabel("Valor mínimo:");
        labelValorMin.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelTaxa = new JLabel("Taxa de rentabilidade:");
        labelTaxa.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelPrazo = new JLabel("Prazo mínimo (meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel labelValor = new JLabel("Valor a ser investido:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        carregarSolicitacoes();
        comboInvestimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvestimentoRendaFixa selecionado = (InvestimentoRendaFixa) comboInvestimentos.getSelectedItem();
                onComboBoxChange(selecionado);
            }
        });

        Dimension campoSize = new Dimension(300, 30);
        comboInvestimentos.setMaximumSize(campoSize);
        comboInvestimentos.setPreferredSize(campoSize);
        campoValorMin.setPreferredSize(campoSize);
        campoValorMin.setMaximumSize(campoSize);
        campoTaxa.setPreferredSize(campoSize);
        campoTaxa.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);

        campoPrazo.setEditable(false);
        campoValorMin.setEditable(false);
        campoTaxa.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(comboInvestimentos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(campoSize);
        scrollPane.setPreferredSize(campoSize);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConfirmar.addActionListener(new InvestirListener());
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConfirmar.setPreferredSize(new Dimension(150, 30));

        add(labelInvestimentos);
        add(Box.createVerticalStrut(10));
        add(scrollPane);
        add(labelValorMin);
        add(campoValorMin);
        add(Box.createVerticalStrut(10));
        add(labelTaxa);
        add(campoTaxa);
        add(Box.createVerticalStrut(10));
        add(labelPrazo);
        add(campoPrazo);
        add(Box.createVerticalStrut(10));
        add(labelValor);
        add(campoValor);
        add(Box.createVerticalStrut(20));
        add(btnConfirmar);
    }

    private static void onComboBoxChange(InvestimentoRendaFixa novoInvestimento) {
        if (novoInvestimento != null) {
            campoValorMin.setText(String.valueOf(novoInvestimento.getValorMinimo()));
            campoTaxa.setText(String.valueOf(novoInvestimento.getTaxaRendimento()));
            campoPrazo.setText(String.valueOf(novoInvestimento.getPrazoMinimo()));
        } else {
            // Limpa os campos caso a combobox esteja vazia
            campoValorMin.setText("");
            campoTaxa.setText("");
            campoPrazo.setText("");
        }
    }

    private void carregarSolicitacoes() {
        comboInvestimentos.removeAllItems();
        InvestimentoRendaFixaPersistence cp = new InvestimentoRendaFixaPersistence();
        List<InvestimentoRendaFixa> solicitacoes = cp.getSolicitacoes();

        for (InvestimentoRendaFixa c : solicitacoes) {
            comboInvestimentos.addItem(c);
        }
    }

    private class InvestirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int index = comboInvestimentos.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um investimento.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            InvestimentoRendaFixaPersistence cp = new InvestimentoRendaFixaPersistence();
            InvestimentoRendaFixa investimento = cp.getSolicitacoes().get(index);

            // Solicita a senha do cliente
            String senhaDigitada = JOptionPane.showInputDialog("Digite a senha do cliente para confirmar:");

            if (senhaDigitada.isEmpty() || !senhaDigitada.equals(cliente.getConta().getSenhaTransacao())) {
                JOptionPane.showMessageDialog(null, "Senha inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Aprovação da transferência pelo caixa
            JOptionPane.showMessageDialog(null, "Investimento realizado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carregarSolicitacoes();
        }
    }
}
