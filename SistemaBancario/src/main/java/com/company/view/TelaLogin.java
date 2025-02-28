package com.company.view;

import com.company.model.*;
import javax.swing.*;
import com.company.view.frames.LoginFrame;
import java.util.List;
import com.company.controller.AutenticarUsuario;
import com.company.persistence.CaixaPersistence;
import com.company.persistence.ClientePersistence;
import com.company.persistence.GerentePersistence;
import com.company.persistence.Persistence;

public class TelaLogin {

    private LoginFrame tela;

    public void desenha() {

        tela = new LoginFrame();
        tela.setVisible(true);
        JButton btn = new JButton();
        btn = tela.getButton();
        btn.addActionListener(new AutenticarUsuario(this));
    }

    public Usuario autenticarUsuario() {

        String login = tela.getjTextField1().getText();
        String senha = new String(tela.getjPasswordField1().getPassword());

        Persistence<Cliente> clientePersistence = new ClientePersistence();
        List<Cliente> clientes = clientePersistence.findAll();

        Persistence<Gerente> gerentePersistence = new GerentePersistence();
        List<Gerente> gerentes = gerentePersistence.findAll();

        Persistence<Caixa> caixaPersistence = new CaixaPersistence();
        List<Caixa> caixas = caixaPersistence.findAll();

        for (Cliente c : clientes) {
            if (c.getLogin().equals(login) && c.getSenha().equals(senha)) {
                return c;
            }
        }
        for (Gerente g : gerentes) {
            if (g.getLogin().equals(login) && g.getSenha().equals(senha)) {
                return g;
            }
        }
        for (Caixa cx : caixas) {
            if (cx.getLogin().equals(login) && cx.getSenha().equals(senha)) {
                return cx;
            }
        }
        return null;
    }

    public void exibirMenu() {

        Usuario user = autenticarUsuario();
        if (user != null) {
            if (user instanceof Cliente) {
                Cliente cliente = (Cliente) user; // Cast seguro após verificação
                TelaCliente tc = new TelaCliente();
                tc.desenha(cliente);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
