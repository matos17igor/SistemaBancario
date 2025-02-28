package com.company.controller;

import com.company.model.Usuario;
import com.company.persistence.UsuarioPersistence;
import com.company.persistence.Persistence;
import com.company.view.TelaLogin;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class GerenciarUsuarios implements WindowListener {

    private final TelaLogin tela;

    public GerenciarUsuarios(TelaLogin tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Usuario> UsuarioPersistence = new UsuarioPersistence();
        List<Usuario> all = UsuarioPersistence.findAll();
        //tela.carregaUsuarios(all);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Usuario> UsuarioPersistence = new UsuarioPersistence();
        //UsuarioPersistence.save(tela.listaUsuarios());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
