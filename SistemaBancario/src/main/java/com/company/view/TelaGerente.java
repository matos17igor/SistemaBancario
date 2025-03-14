/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view;

import com.company.model.Gerente;
import com.company.view.paineis.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaGerente {
    
    private JFrame tela;
    private JPanel painelSuperior;
    private JPanel painelBotoes;    //div dos botoes
    private JPanel painelPrincipal; //div dos conteudos dos botoes
    
    private CardLayout cardLayout;  //usada para organizar componentes dentro de um contêiner de forma que apenas um componente seja visível de cada vez
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    private JButton btnApoio;
    private JButton btnCadastroRendaFixa;
    private JButton btnCadastroRendaVariavel;
    private JButton btnAvaliacao;
    private JButton btnLogout;
    private JLabel mensagem;
    
    private Gerente gerente;

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }
    
    public void desenha(Gerente g) {
        tela = new JFrame("Menu Gerente");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());
        
        setGerente(g);
        
        desenhaPainelSuperior();
        desenhaPainelBotoes();
        desenhaPainelPrincipal();
        
        tela.setResizable(false);   //usado para definir se uma janela (por exemplo, um JFrame) pode ser redimensionada pelo usuário ou não
        tela.setVisible(true);
        tela.setLocationRelativeTo(null);
    }
    
    public void desenhaPainelSuperior(){
        painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        
        mensagem = new JLabel("Bem-vindo, " + getGerente().getName());
        mensagem.setFont(new Font("Arial", Font.BOLD, 18));
        
        painelSuperior.add(mensagem);
        
        tela.add(painelSuperior, BorderLayout.NORTH);
    }
    
    public void desenhaPainelBotoes(){
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        
        btnApoio = new JButton("Apoio em Movimentações Financeiras");        
        btnCadastroRendaFixa = new JButton("Cadastro de Investimentos em Renda Fixa");       
        btnCadastroRendaVariavel = new JButton("Cadastro de Investimentos em Renda Variável");
        btnAvaliacao = new JButton("Avaliação de Crédito");
        btnLogout = new JButton("Sair");
        
        Dimension btnSize = new Dimension(300, 50);
        
        btnApoio.setPreferredSize(btnSize);
        btnApoio.setMaximumSize(btnSize); 
        btnApoio.setAlignmentX(Component.CENTER_ALIGNMENT); // utilizado em Java para definir o alinhamento horizontal de um componente dentro de um contêiner
        
        btnCadastroRendaFixa.setPreferredSize(btnSize);
        btnCadastroRendaFixa.setMaximumSize(btnSize); 
        btnCadastroRendaFixa.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnCadastroRendaVariavel.setPreferredSize(btnSize);
        btnCadastroRendaVariavel.setMaximumSize(btnSize); 
        btnCadastroRendaVariavel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnAvaliacao.setPreferredSize(btnSize);
        btnAvaliacao.setMaximumSize(btnSize); 
        btnAvaliacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnLogout.setPreferredSize(new Dimension(110, 23));
        btnLogout.setMaximumSize(new Dimension(110, 23));
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        painelBotoes.add(Box.createVerticalStrut(50));  //cria um espaço fixo vertical entre componentes em um layout
        painelBotoes.add(btnApoio);
        
        painelBotoes.add(Box.createVerticalStrut(15));
        painelBotoes.add(btnCadastroRendaFixa);
        
        painelBotoes.add(Box.createVerticalStrut(15));
        painelBotoes.add(btnCadastroRendaVariavel);
        
        painelBotoes.add(Box.createVerticalStrut(15));
        painelBotoes.add(btnAvaliacao);
        
        painelBotoes.add(Box.createVerticalStrut(30));
        painelBotoes.add(btnLogout);
        
        painelBotoes.setBorder(new EmptyBorder(0, 60, 0, 0)); //setBorder: utilizado para definir a borda de um componente em Java, EmptyBorder: usado para adicionar espaçamento interno (margem) dentro do componente
        
        tela.add(painelBotoes, BorderLayout.WEST);
        
        btnApoio.addActionListener(e -> mostrarPainel("apoio"));
        btnCadastroRendaFixa.addActionListener(e -> mostrarPainel("cadastroRendaFixa"));
        btnCadastroRendaVariavel.addActionListener(e -> mostrarPainel("cadastroRendaVariável"));
        btnAvaliacao.addActionListener(e -> mostrarPainel("avaliacao"));
        btnLogout.addActionListener(e -> tela.setVisible(false));
    }
    
    private void desenhaPainelPrincipal() {
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(new JPanel(), "vazio");
        painelPrincipal.add(new PainelApoio(), "apoio");
        painelPrincipal.add(new PainelCadastroRendaFixa(), "cadastroRendaFixa");
        painelPrincipal.add(new PainelCadastroRendaVariavel(), "cadastroRendaVariável");
        painelPrincipal.add(new PainelAvaliacao(), "avaliacao");
        painelPrincipal.setBorder(new EmptyBorder(0, 0, 0, 60));

        tela.add(painelPrincipal, BorderLayout.EAST);
        cardLayout.show(painelPrincipal, "vazio");
    }
    
    private void mostrarPainel(String nomePainel) {
        cardLayout.show(painelPrincipal, nomePainel);
    }
}