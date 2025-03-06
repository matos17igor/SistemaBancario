package com.company.controller;

import com.company.model.Caixa;
import com.company.persistence.CaixaPersistence;
import com.company.persistence.Persistence;
import com.company.view.TelaGerenciamentoCaixa;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class GerenciarCaixas implements WindowListener {

    private final TelaGerenciamentoCaixa tela;

    public GerenciarCaixas(TelaGerenciamentoCaixa tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Caixa> CaixaPersistence = new CaixaPersistence();
        List<Caixa> all = CaixaPersistence.findAll();
        tela.carregaCaixas(all);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Caixa> CaixaPersistence = new CaixaPersistence();
        CaixaPersistence.save(tela.listaCaixas());
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
