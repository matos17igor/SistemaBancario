/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
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
