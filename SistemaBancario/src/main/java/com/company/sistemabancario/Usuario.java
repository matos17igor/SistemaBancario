package com.company.sistemabancario;

public class Usuario {

    private String nome;
    private String nascimento;
    private String cpf;
    private String telefone;
    private Email email;
    private Endereco logradouro;
    private String login;
    private String senha;
    private Conta conta;

    public Usuario() {}

    public Usuario(String nome, Endereco logradouro, Conta conta, String nascimento, String cpf, Email email, String senha, String telefone) {
        this.nome = nome;
        this.login = login;
        this.logradouro = logradouro;
        this.conta = conta;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Endereco logradouro) {
        this.logradouro = logradouro;
    }

    public Conta getConta() {
        return conta;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public void setEmail(String email) throws EmailException {
        this.email = new Email(email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }
}
