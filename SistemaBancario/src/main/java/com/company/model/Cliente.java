package com.company.model;

import com.company.exception.CPFException;
import com.company.exception.NameException;

public class Cliente extends Usuario {
    
    private Conta conta;
    
    public Cliente (){}
    
    public Cliente(int id, String nome, Endereco logradouro, String nascimento, String cpf, Email email, 
    String senha, String telefone, Conta conta)
            
    throws NameException, CPFException
    {
        super(id, nome, logradouro, nascimento, cpf, email, senha, telefone);
        this.conta = conta;
    }
    
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
