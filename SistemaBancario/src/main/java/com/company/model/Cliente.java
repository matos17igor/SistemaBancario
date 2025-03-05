package com.company.model;

import com.company.exception.CPFException;
import com.company.exception.NameException;
import com.company.exception.SaldoException;
import com.company.exception.PasswordException;

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
    
    public void realizaTransferencia(double valor, Conta destino, String senha) throws SaldoException, PasswordException{

        if(this.conta.getSaldo() < valor)
            throw new SaldoException();
        if(!senha.equals(this.conta.getSenhaTransacao()))
            throw new PasswordException();
        
        Transferencia tr = new Transferencia(this.conta , valor, destino);
        this.conta.getTransferencias().add(tr);
        destino.getTransferencias().add(tr);
        
        double saldoOrigem = this.conta.getSaldo();
        double saldoDestino = destino.getSaldo();
        saldoOrigem -= valor;
        saldoDestino += valor;
        this.conta.setSaldo(saldoOrigem);
        destino.setSaldo(saldoDestino);
    }
    
    public void realizaInvestimento(){
        
    }
 
    public void solicitaEmprestimo(double valor, int parcelas){
    }
    
    public void solicitaFinanciamento(double valor, double entrada, int parcelas){
    }
}
