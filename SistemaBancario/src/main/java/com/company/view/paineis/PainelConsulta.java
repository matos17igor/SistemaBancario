package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelConsulta extends JPanel {
    private JLabel lblSaldo;
    private JTextArea txtExtrato;

    public PainelConsulta() {
        setLayout(new BorderLayout());

        lblSaldo = new JLabel("Saldo atual: R$0,00", JLabel.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblSaldo, BorderLayout.NORTH);

        txtExtrato = new JTextArea(10, 30);
        txtExtrato.setEditable(false);
        txtExtrato.setText("Extrato de movimentações...");
        add(new JScrollPane(txtExtrato), BorderLayout.CENTER);
    }
}