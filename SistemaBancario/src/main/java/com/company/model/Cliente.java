package com.company.model;

import com.company.exception.CPFException;
import com.company.exception.NameException;
import com.company.exception.SaldoException;
import com.company.exception.PasswordException;

public class Cliente extends Usuario {
    
    private Conta conta;
    
    public Cliente(String nome, Endereco logradouro, String nascimento, String cpf, Email email, 
    String senha, String telefone, Conta conta)
            
    throws NameException, CPFException
    {
        super(nome, logradouro, nascimento, cpf, email, senha, telefone);
        this.conta = conta;
    }
    
    @Override
    public void exibirMenu() {
        System.out.println("Menu Cliente:");
        System.out.println("1. Transferência");
        System.out.println("2. Consulta de Saldo/Extrato");
        System.out.println("3. Investimento em Renda Fixa");
        System.out.println("4. Investimento em Renda Variável");
        System.out.println("5. Solicitação de Crédito");
    }
    
    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public void realizaTransferencia(double valor, Conta destino, String senha) throws SaldoException, PasswordException{
        
        //Se o saldo da conta de origem for menor que o valor da transferecnia,
        //cancela a operação.
        if(this.conta.getSaldo() < valor)
            throw new SaldoException();
        if(!senha.equals(this.conta.getSenhaTransacao()))
            throw new PasswordException();
        
        //Cria nova transferencia e a adiciona as contas de origem e destino
        Transferencia tr = new Transferencia(this.conta , valor, destino);
        this.conta.getTransferencias().add(tr);
        destino.getTransferencias().add(tr);
        
        //Atualiza o saldo das contas pós transferencia
        double saldoOrigem = this.conta.getSaldo();
        double saldoDestino = destino.getSaldo();
        saldoOrigem -= valor;
        saldoDestino += valor;
        this.conta.setSaldo(saldoOrigem);
        destino.setSaldo(saldoDestino);
    }
    
    public void realizaInvestimento(){
        
    }
    
    //Para estes dois metodos, deve-se implementar a classe gerente primeiro,
    //de forma que se tenha um metodo analiseCredito por parte do gerente
    //antes de concluir o emprestimo/financiamento
    public void solicitaEmprestimo(double valor, int parcelas){
    }
    
    public void solicitaFinanciamento(double valor, double entrada, int parcelas){
    }
}
