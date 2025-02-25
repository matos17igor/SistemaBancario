package com.company.model;

import com.company.exception.CPFException;
import com.company.exception.NameException;
import com.company.exception.SaldoException;
import com.company.exception.PasswordException;

public class Caixa extends Usuario{
    
    public Caixa(String nome, Endereco logradouro, String nascimento, String cpf, Email email, String senha, 
    String telefone) throws NameException, CPFException {
        super(nome, logradouro, nascimento, cpf, email, senha, telefone);
    }
    
    @Override
    public void exibirMenu() {
        System.out.println("Menu Caixa:");
        System.out.println("1. Processar Saque");
        System.out.println("2. Processar Depósito");
        System.out.println("3. Executar Transferência");
    }
    
    public void processarSaque(Cliente cliente, double valor, String senhaTransacao) throws SaldoException, PasswordException {
        Conta conta = cliente.getConta();
        if(!conta.getSenhaTransacao().equals(senhaTransacao)) {
            throw new PasswordException();
        } 
        
        if(conta.getSaldo() < valor){
            throw new SaldoException();
        }
        
        Saque saque = new Saque(conta, valor);
        conta.getSaques().add(saque);
        conta.setSaldo(conta.getSaldo() - valor);
        System.out.println("Saque de R$ " + valor + " realizado com sucesso para o cliente " + cliente.getName());
    }
    
    public void processarDeposito(Conta origem, Conta destino, double valor){
        Deposito deposito = new Deposito(origem, valor, destino);
        
        //Se o deposito for destinado a propria conta
        if(origem.equals(destino)){
            origem.getDepositos().add(deposito);
            origem.setSaldo(origem.getSaldo() + valor);
        }
        else{
            //Se o deposito for destinado à outra conta
            destino.getDepositos().add(deposito);
            destino.setSaldo(destino.getSaldo() + valor);
        }
        
        System.out.println("Deposito de R$ " + valor + " realizado com sucesso");
    }
    
    public void processarTransferencia(Cliente clienteOrigem, Conta contaDestino, double valor, String senhaTransacao) 
        throws SaldoException, PasswordException {
        
        Conta contaOrigem = clienteOrigem.getConta();
        
        if(!contaOrigem.getSenhaTransacao().equals(senhaTransacao)){
            throw new PasswordException();
        }
        
        if(contaOrigem.getSaldo() < valor){
            throw new SaldoException();
        }
        
        //Processa a transferencia
        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        
        //Registra a transferencia nas contas
        Transferencia transferencia = new Transferencia(contaOrigem, valor, contaDestino);
        contaOrigem.getTransferencias().add(transferencia);
        contaDestino.getTransferencias().add(transferencia);
        
    }
    
}
