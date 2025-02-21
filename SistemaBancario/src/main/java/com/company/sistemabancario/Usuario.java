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

    public Usuario() {}

    public Usuario(String nome, Endereco logradouro, String nascimento, String cpf, Email email, String senha, String telefone)
    throws NameException, CPFException
    {
        setName(nome);
        this.login = login;
        this.logradouro = logradouro;
        this.nascimento = nascimento;
        setCPF(cpf);
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public String getName() {
        return nome;
    }
    
    public boolean isValidName(String nome) {
        return nome.length() > 1;
    }

    public void setName(String nome) throws NameException {
        if (!isValidName(nome)) {
            throw new NameException();
        }
        this.nome = nome;
    }

    public Endereco getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Endereco logradouro) {
        this.logradouro = logradouro;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getCpf() {
        return cpf;
    }
    
    public void setCPF(String cpf) throws CPFException {
        if(!ValidaCPF.isCPF(cpf))
            throw new CPFException();
        this.cpf = cpf;
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
