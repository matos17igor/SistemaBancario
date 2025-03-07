/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
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
        JButton btn = tela.getButton();
        JButton btnCadastro = tela.getButtonCadastro();
        btnCadastro.addActionListener(e -> exibirCadastro());
        btn.addActionListener(new AutenticarUsuario(this));
    }

    public Usuario autenticarUsuario() {

        String login = tela.getjTextField1().getText();
        String senha = new String(tela.getjPasswordField1().getPassword());

        if (login.equals("admin") && senha.equals("admin")) {
            TelaAdmin telaadm = new TelaAdmin();
            telaadm.desenha();
            return null;
        }

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
        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        return null;
    }

    public void cadastrarCliente() {
    }

    public void exibirCadastro() {

        TelaCadastro tc = new TelaCadastro();
        tc.desenha();
    }

    public void exibirMenu() {

        Usuario user = autenticarUsuario();
        if (user != null) {
            if (user instanceof Cliente) {
                Cliente cliente = (Cliente) user; // Cast seguro após verificação
                TelaCliente tc = new TelaCliente();
                tc.desenha(cliente);
            }
            if (user instanceof Gerente) {
                Gerente gerente = (Gerente) user; // Cast seguro após verificação
                TelaGerente tg = new TelaGerente();
                tg.desenha(gerente);
            }
            if (user instanceof Caixa) {
                Caixa caixa = (Caixa) user; // Cast seguro após verificação
                TelaCaixa tcx = new TelaCaixa();
                tcx.desenha(caixa);
            }
        }
    }

}
