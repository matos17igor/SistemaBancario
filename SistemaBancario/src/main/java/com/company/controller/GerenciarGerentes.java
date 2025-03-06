package com.company.controller;

import com.company.model.Gerente;
import com.company.persistence.GerentePersistence;
import com.company.persistence.Persistence;
import com.company.view.TelaAdmin;
import com.company.view.TelaGerenciamentoGerente;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class GerenciarGerentes implements WindowListener {

    private final TelaGerenciamentoGerente tela;

    public GerenciarGerentes(TelaGerenciamentoGerente tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Gerente> gerentePersistence = new GerentePersistence();
        List<Gerente> all = gerentePersistence.findAll();
        tela.carregaGerentes(all);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Gerente> gerentePersistence = new GerentePersistence();
        gerentePersistence.save(tela.listaGerentes());
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
