package com.company.main;

import com.company.view.*;

public class SistemaBancario {

    public static void main(String[] args) {
        TelaLogin tela = new TelaLogin();
        //tela.desenha();
        TelaCliente telaCliente = new TelaCliente();
        //telaCliente.desenha();
        
        TelaGerente telaGerente = new TelaGerente();
        telaGerente.desenha();
        
    }
}
