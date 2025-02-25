package com.company.sistemabancario;

import com.company.exception.CPFException;
import com.company.exception.NameException;
import com.company.exception.SaldoException;

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
    
    public void processarSaque(Cliente cliente, double valor, String senhaTransacao) throws SaldoException {
        Conta conta = cliente.getConta();
        if(!conta.getSenhaTransacao().equals(senhaTransacao)) {
            System.out.println("Senha de transacao invalida!");
            return;
        } 
        
        if(conta.getSaldo() < valor){
            throw new SaldoException();
        }
        
        conta.setSaldo(conta.getSaldo() - valor);
        System.out.println("Saque de R$ " + valor + " realizado com sucesso para o cliente " + cliente.getName());
    }
    
    public void processarDeposito(Cliente cliente, double valor){
        Conta conta = cliente.getConta();
        conta.setSaldo(conta.getSaldo() + valor);
        System.out.println("Deposito de R$ " + valor + " realizado com sucesso para o cliente " + cliente.getName());
    }
    
    public void processarTransferencia(Cliente clienteOrigem, Conta contaDestino, double valor, String senhaTransacao) throws SaldoException {
        Conta contaOrigem = clienteOrigem.getConta();
        
        if(!contaOrigem.getSenhaTransacao().equals(senhaTransacao)){
            System.out.println("Senha de transacao invalida!");
            return;
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
