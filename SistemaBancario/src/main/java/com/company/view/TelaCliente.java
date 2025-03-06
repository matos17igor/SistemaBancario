package com.company.view;

import com.company.model.Cliente;
import com.company.view.paineis.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaCliente {

    private JFrame tela;
    private JPanel painelBotoes;    //div dos botoes
    private JPanel painelSuperior;  //div das msgs
    private JPanel painelPrincipal; //div dos conteudos dos botoes
    private CardLayout cardLayout;  //usada para organizar componentes dentro de um contêiner de forma que apenas um componente seja visível de cada vez

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private JButton btnTransferencia;
    private JButton btnConsulta;
    private JButton btnRendaFixa;
    private JButton btnRendaVariavel;
    private JButton btnSolicitacao;
    private JButton btnSaque;
    private JButton btnLogout;
    private JLabel mensagem;
    private JLabel saldo;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void desenha(Cliente c) {
        tela = new JFrame("Menu Cliente");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());
        setCliente(c);

        desenhaPainelSuperior();
        desenhaPainelBotoes();
        desenhaPainelPrincipal();

        tela.setResizable(false);   //usado para definir se uma janela (por exemplo, um JFrame) pode ser redimensionada pelo usuário ou não
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);

    }

    public void desenhaPainelSuperior() {
        if (painelSuperior != null) {
            tela.remove(painelSuperior); // Remove o painel anterior
        }

        painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        mensagem = new JLabel("Bem-vindo, " + getCliente().getName());
        saldo = new JLabel("Saldo: R$" + String.format("%.2f", getCliente().getConta().getSaldo())); // Formata o saldo corretamente
        mensagem.setFont(new Font("Arial", Font.BOLD, 18));
        saldo.setFont(new Font("Arial", Font.PLAIN, 16));

        painelSuperior.add(mensagem);
        painelSuperior.add(saldo);

        tela.add(painelSuperior, BorderLayout.NORTH);

        tela.revalidate(); // Recalcula os componentes da tela
        tela.repaint();    // Redesenha a interface
    }

    public void desenhaPainelBotoes() {
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        btnConsulta = new JButton("Consulta de Saldo/Extrato");
        btnTransferencia = new JButton("Realizar transferência");
        btnRendaFixa = new JButton("Investimento em Renda Fixa");
        btnRendaVariavel = new JButton("Investimento em Renda Variável");
        btnSolicitacao = new JButton("Solicitação de Crédito");
        btnSaque = new JButton("Solicitação de Saque");
        btnLogout = new JButton("Sair");

        Dimension btnSize = new Dimension(200, 50);
        btnTransferencia.setPreferredSize(btnSize);
        btnTransferencia.setMaximumSize(btnSize);
        btnTransferencia.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnConsulta.setPreferredSize(btnSize);
        btnConsulta.setMaximumSize(btnSize);
        btnConsulta.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRendaFixa.setPreferredSize(btnSize);
        btnRendaFixa.setMaximumSize(btnSize);
        btnRendaFixa.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRendaVariavel.setPreferredSize(btnSize);
        btnRendaVariavel.setMaximumSize(btnSize);
        btnRendaVariavel.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnSolicitacao.setPreferredSize(btnSize);
        btnSolicitacao.setMaximumSize(btnSize);
        btnSolicitacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnSaque.setPreferredSize(btnSize);
        btnSaque.setMaximumSize(btnSize);
        btnSaque.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogout.setPreferredSize(new Dimension(110, 23));
        btnLogout.setMaximumSize(new Dimension(110, 23));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnTransferencia);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnConsulta);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnRendaFixa);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnRendaVariavel);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnSolicitacao);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnSaque);
        painelBotoes.add(Box.createVerticalStrut(30));
        painelBotoes.add(btnLogout);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.setBorder(new EmptyBorder(0, 100, 0, 0));

        tela.add(painelBotoes, BorderLayout.WEST);

        btnTransferencia.addActionListener(e -> mostrarPainel("transferencia"));
        btnConsulta.addActionListener(e -> mostrarPainel("consulta"));
        btnRendaFixa.addActionListener(e -> mostrarPainel("rendaFixa"));
        btnRendaVariavel.addActionListener(e -> mostrarPainel("rendaVariavel"));
        btnSolicitacao.addActionListener(e -> mostrarPainel("solicitacao"));
        btnSaque.addActionListener(e -> mostrarPainel("saque"));
        btnLogout.addActionListener(e -> tela.setVisible(false));
    }

    public void desenhaPainelPrincipal() {
        if (painelPrincipal != null) {
            tela.remove(painelPrincipal); // Remove o painel anterior
        }
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(new JPanel(), "vazio");
        painelPrincipal.add(new PainelTransferencia(getCliente(), this), "transferencia");
        painelPrincipal.add(new PainelConsulta(getCliente()), "consulta");
        painelPrincipal.add(new PainelRendaFixa(), "rendaFixa");
        painelPrincipal.add(new PainelRendaVariavel(), "rendaVariavel");
        painelPrincipal.add(new PainelSolicitacao(cliente), "solicitacao");
        painelPrincipal.add(new PainelSaque(cliente, this), "saque");
        painelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 70));

        tela.add(painelPrincipal, BorderLayout.EAST);
        cardLayout.show(painelPrincipal, "vazio");

        tela.revalidate(); // Recalcula os componentes da tela
        tela.repaint();    // Redesenha a interface
    }

    private void mostrarPainel(String nomePainel) {
        cardLayout.show(painelPrincipal, nomePainel);
    }

}
