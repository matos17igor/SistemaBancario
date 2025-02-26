package com.company.view;

import javax.swing.*;

public class TelaLogin {

    private JFrame tela;
    private final int WIDTH = 500;
    private final int HEIGHT = 200;

    private JTextField tfLogin;
    private JTextField tfSenha;
    private JButton btnLogin;

    public void desenha() {

        tela = new JFrame("Login");
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLocationRelativeTo(null);
        tela.setLayout(null);
        tela.setVisible(true);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(20, 20, 80, 25);
        tela.add(lblUsuario);

        tfLogin = new JTextField();
        tfLogin.setBounds(100, 20, 160, 25);
        tela.add(tfLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 60, 80, 25);
        tela.add(lblSenha);

        tfSenha = new JPasswordField();
        tfSenha.setBounds(100, 60, 160, 25);
        tela.add(tfSenha);

        btnLogin = new JButton("Entrar");
        btnLogin.setBounds(100, 100, 100, 30);
        tela.add(btnLogin);
    }

}
