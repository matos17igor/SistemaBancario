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


        tela.setResizable(false);

        
        tela.setResizable(false);   //usado para definir se uma janela (por exemplo, um JFrame) pode ser redimensionada pelo usuário ou não
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);

    }

    public void desenhaPainelSuperior() {
        painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        mensagem = new JLabel("Bem-vindo " + getCliente().getName());
        saldo = new JLabel("Saldo: R$0,00");
        mensagem.setFont(new Font("Arial", Font.BOLD, 18));
        saldo.setFont(new Font("Arial", Font.PLAIN, 16));

        painelSuperior.add(mensagem);
        painelSuperior.add(saldo);

        tela.add(painelSuperior, BorderLayout.NORTH);
    }

    public void desenhaPainelBotoes() {
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        btnConsulta = new JButton("Consulta de Saldo/Extrato");
        btnTransferencia = new JButton("Realizar transferência");
        btnRendaFixa = new JButton("Investimento em Renda Fixa");
        btnRendaVariavel = new JButton("Investimento em Renda Variável");
        btnSolicitacao = new JButton("Solicitação de Crédito");

        Dimension btnSize = new Dimension(200, 50);
        btnTransferencia.setPreferredSize(btnSize);
        btnTransferencia.setMaximumSize(btnSize); 
        btnTransferencia.setAlignmentX(Component.CENTER_ALIGNMENT); // utilizado em Java para definir o alinhamento horizontal de um componente dentro de um contêiner
        

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
        painelBotoes.setBorder(new EmptyBorder(0, 100, 0, 0));

        tela.add(painelBotoes, BorderLayout.WEST);

        btnTransferencia.addActionListener(e -> mostrarPainel("transferencia"));
        btnConsulta.addActionListener(e -> mostrarPainel("consulta"));
        btnRendaFixa.addActionListener(e -> mostrarPainel("rendaFixa"));
        btnRendaVariavel.addActionListener(e -> mostrarPainel("rendaVariavel"));
        btnSolicitacao.addActionListener(e -> mostrarPainel("solicitacao"));
    }

    private void desenhaPainelPrincipal() {
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(new JPanel(), "vazio");
        painelPrincipal.add(new PainelTransferencia(), "transferencia");
        painelPrincipal.add(new PainelConsulta(), "consulta");
        painelPrincipal.add(new PainelRendaFixa(), "rendaFixa");
        painelPrincipal.add(new PainelRendaVariavel(), "rendaVariavel");
        painelPrincipal.add(new PainelSolicitacao(), "solicitacao");
        painelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 70));

        tela.add(painelPrincipal, BorderLayout.EAST);
        cardLayout.show(painelPrincipal, "vazio");
    }

    private void mostrarPainel(String nomePainel) {
        cardLayout.show(painelPrincipal, nomePainel);
    }

}
