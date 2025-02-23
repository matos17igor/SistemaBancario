package com.company.sistemabancario;

import com.company.exception.CPFException;
import com.company.exception.NameException;

public class Cliente extends Usuario{
    
    public Cliente(String nome, Endereco logradouro, String nascimento, String cpf, Email email, 
    String senha, String telefone, Conta conta)
    throws NameException, CPFException
    {
        super(nome, logradouro, nascimento, cpf, email, senha, telefone, conta);
    }
    
    public void realizaTransferencia(Conta origem,double valor, Conta destino){
        
        Transferencia tr = new Transferencia(origem, valor, destino);
        origem.getTransferencias().add(tr);
        destino.getTransferencias().add(tr);
        
        double saldoOrigem = origem.getSaldo();
        double saldoDestino = destino.getSaldo();
        saldoOrigem -= valor;
        saldoDestino += valor;
        origem.setSaldo(saldoOrigem);
        destino.setSaldo(saldoDestino);
    }
}
