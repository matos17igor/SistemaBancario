package com.company.main;

import com.company.view.*;

public class SistemaBancario {

    public static void main(String[] args) {
//        // Criando uma conta para o cliente Igor
//        Conta conta1 = new Conta("12345", 500.0, "senha123", "Igor"); 
//
//        // Criando o cliente e associando a conta
//        Cliente user1 = new Cliente();
//        try {
//            user1.setName("Igor");
//        } catch (NameException e) {
//            System.out.print("Erro " + e);
//        }
//        user1.setLogin("igor");
//        user1.setSenha("igor123");
//        user1.setConta(conta1); // Associando a conta ao cliente
//
//        // Salvando o cliente na persistência
//        List<Cliente> clientes = new ArrayList<>();
//        clientes.add(user1);
//        Persistence<Cliente> clientePersistence = new ClientePersistence();
//        clientePersistence.save(clientes);
//
//        // Salvar a conta separadamente
//        List<Conta> contas = new ArrayList<>();
//        contas.add(conta1);
//        Persistence<Conta> contaPersistence = new ContaPersistence();
//        contaPersistence.save(contas);
//        
//        // Criando uma conta para o cliente Joao
//        Conta conta2 = new Conta("12345", 500.0, "senha123", "Joao"); 
//
//        // Criando o cliente e associando a conta
//        Cliente user2 = new Cliente();
//        try {
//            user2.setName("Joao");
//        } catch (NameException e) {
//            System.out.print("Erro " + e);
//        }
//        user2.setLogin("joao");
//        user2.setSenha("joao123");
//        user2.setConta(conta2); // Associando a conta ao cliente
//
//        // Salvando o cliente na persistência
//        clientes.add(user2);
//        clientePersistence.save(clientes);
//
//        // Salvar a conta separadamente
//        contas.add(conta2);
//        contaPersistence.save(contas);

        TelaLogin tela = new TelaLogin();
        tela.desenha();
    }
}
