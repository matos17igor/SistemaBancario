package com.company.model;

import com.company.exception.CPFException;
import com.company.exception.NameException;
import com.company.exception.PasswordException;
import java.util.List;
import java.util.ArrayList;

public class Gerente extends Usuario{

    protected List<InvestimentoRendaFixa> investimentosRendaFixa = new ArrayList<>();
    protected List<InvestimentoRendaVariavel> investimentosRendaVariavel = new ArrayList<>();
    
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
    
    public void apoiarMovimentacao(Conta cliente, String operacao, String senhaConfirmacao) throws PasswordException{
        if(!cliente.getSenhaTransacao().equals(senhaConfirmacao)) {
            throw new PasswordException();
        }
        
        if(operacao.equalsIgnoreCase("transferencia") || operacao.equalsIgnoreCase("saque")){
            // verificar se o valor da operacao esta acima de 1 milhao
        } 
        
        System.out.println("Apoio concedido para a operacao de " + operacao + "do cliente " + cliente.getTitular());
    }
    
    public void cadastrarInvestimentoRendaFixa(InvestimentoRendaFixa investimento){
        investimentosRendaFixa.add(investimento);
        System.out.println("Investimento em Renda Fixa cadastrado: " + investimento.getNome());
    }
    
    public void cadastrarInvestimentoRendaVariavel(InvestimentoRendaVariavel investimento){
        investimentosRendaVariavel.add(investimento);
        System.out.println("Investimento em Renda Variavel cadastrado: " + investimento.getNome());
    }
    
    public void avaliarCredito(Conta cliente, String senhaConfirmacao) throws PasswordException{
        // esta avaliando somente o saldo disponivel
        if(!cliente.getSenhaTransacao().equals(senhaConfirmacao)){
            throw new PasswordException();
        }
        
        double saldo = cliente.getSaldo();
        String resultado;
        if(saldo >= 1000){
            resultado = "aprovado";
        } else {
            resultado = "rejeitado";
        }
        System.out.println("Credito " + resultado + " para o cliente " + cliente.getTitular() + "(Saldo: R$ " +  saldo + ")");
    }
    
}
