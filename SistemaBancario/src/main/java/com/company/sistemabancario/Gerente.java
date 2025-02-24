package com.company.sistemabancario;

import com.company.exception.CPFException;
import com.company.exception.NameException;

public class Gerente extends Usuario{

    public Gerente(String nome, Endereco logradouro, String nascimento, String cpf, Email email, String senha, String telefone) throws NameException, CPFException {
        super(nome, logradouro, nascimento, cpf, email, senha, telefone);
    }
    
    @Override
    public void exibirMenu(){
        System.out.println("Menu Gerente:");
        System.out.println("1. Apoio em Movimentações Financeiras");
        System.out.println("2. Cadastro de Investimentos em Renda Fixa");
        System.out.println("3. Cadastro de Investimentos em Renda Variável");
        System.out.println("4. Avaliação de Crédito");
    }
    
    public void cadastrarInvestimentoRendaFixa(InvestimentoRendaFixa investimento){
        System.out.println("Investimento em Renda Fixa cadastrado: " + investimento.getNome());
    }
    
    public void cadastrarInvestimentoRendaVariavel(InvestimentoRendaVariavel investimento){
        System.out.println("Investimento em Renda Variavel cadastrado: " + investimento.getNome());
    }
    
    public void avaliarCredito(Cliente cliente){
        System.out.println("Credito avalaido para o cliente " + cliente.getName());
    }
}
