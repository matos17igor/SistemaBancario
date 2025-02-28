package com.company.main;

import com.company.exception.NameException;
import com.company.model.*;
import com.company.persistence.ClientePersistence;
import com.company.persistence.Persistence;
import com.company.view.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaBancario {

    public static void main(String[] args) {
        TelaLogin tela = new TelaLogin();
        Cliente user = new Cliente();
        try{
            user.setName("joao");
        }catch(NameException e){
            System.out.print("Erro "+ e);
        }
        user.setLogin("admin");
        user.setSenha("admin");
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(user);
        Persistence<Cliente> clientePersistence = new ClientePersistence();
        clientePersistence.save(clientes);
        tela.desenha();
    }
}
