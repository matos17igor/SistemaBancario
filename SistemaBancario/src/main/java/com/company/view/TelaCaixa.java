/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view;

import com.company.model.Caixa;
import com.company.view.paineis.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCaixa {
    
    private JFrame tela;
    private JPanel painelBotoes;
    private JPanel painelSuperior;
    private JPanel painelPrincipal;
    private CardLayout cardLayout;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private JButton btnSaque;
    private JButton btnDeposito;
    private JButton btnTransferencia;
    private JButton btnLogout;
    private JLabel mensagem;
    private Caixa caixa;

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public void desenha(Caixa caixa) {
        tela = new JFrame("Menu Caixa");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());
        setCaixa(caixa);

        desenhaPainelSuperior();
        desenhaPainelBotoes();
        desenhaPainelPrincipal();

        tela.setResizable(false);
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
    }

    public void desenhaPainelSuperior() {
        painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        mensagem = new JLabel("Bem-vindo " + caixa.getName());
        mensagem.setFont(new Font("Arial", Font.BOLD, 18));

        painelSuperior.add(mensagem);

        tela.add(painelSuperior, BorderLayout.NORTH);
    }

    public void desenhaPainelBotoes() {
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        btnSaque = new JButton("Realizar Saque");
        btnDeposito = new JButton("Receber Depósito");
        btnTransferencia = new JButton("Realizar Transferência");
        btnLogout = new JButton("Sair");

        Dimension btnSize = new Dimension(200, 50);
        btnSaque.setPreferredSize(btnSize);
        btnSaque.setMaximumSize(btnSize);
        btnSaque.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnDeposito.setPreferredSize(btnSize);
        btnDeposito.setMaximumSize(btnSize);
        btnDeposito.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnTransferencia.setPreferredSize(btnSize);
        btnTransferencia.setMaximumSize(btnSize);
        btnTransferencia.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogout.setPreferredSize(new Dimension(110, 23));
        btnLogout.setMaximumSize(new Dimension(110, 23));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnSaque);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnDeposito);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnTransferencia);
        painelBotoes.add(Box.createVerticalStrut(50));
        painelBotoes.add(btnLogout);
        painelBotoes.setBorder(new EmptyBorder(0, 100, 0, 0));

        tela.add(painelBotoes, BorderLayout.WEST);

        btnSaque.addActionListener(e -> mostrarPainel("saque"));
        btnDeposito.addActionListener(e -> mostrarPainel("deposito"));
        btnTransferencia.addActionListener(e -> mostrarPainel("transferencia"));
        btnLogout.addActionListener(e -> tela.setVisible(false));
    }

    private void desenhaPainelPrincipal() {
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(new JPanel(), "vazio");
        painelPrincipal.add(new PainelSaqueCaixa(), "saque");
        painelPrincipal.add(new PainelDeposito(), "deposito");
        painelPrincipal.add(new PainelTransferenciaCaixa(), "transferencia");
        painelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 70));

        tela.add(painelPrincipal, BorderLayout.EAST);
        cardLayout.show(painelPrincipal, "vazio");
    }

    private void mostrarPainel(String nomePainel) {
        cardLayout.show(painelPrincipal, nomePainel);
    }
}
