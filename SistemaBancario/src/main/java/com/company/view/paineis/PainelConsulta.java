package com.company.view.paineis;

import com.company.model.Cliente;
import javax.swing.*;
import java.awt.*;

public class PainelConsulta extends JPanel {
    private JLabel lblSaldo;
    private JTextArea txtExtrato;

    public PainelConsulta(Cliente cliente) {
        setLayout(new BorderLayout());

        lblSaldo = new JLabel("Saldo atual: R$" + cliente.getConta().getSaldo(), JLabel.CENTER);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblSaldo, BorderLayout.NORTH);

        txtExtrato = new JTextArea(10, 30);
        txtExtrato.setEditable(false);
        txtExtrato.setText("Extrato de movimentações...");
        add(new JScrollPane(txtExtrato), BorderLayout.CENTER);
    }
}