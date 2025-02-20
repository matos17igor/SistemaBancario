package com.company.sistemabancario;

public class Nome {

    private String[] nome;

    public Nome(String[] nome) throws NameException {
        setName(nome);
    }

    public boolean isValidName(String[] nome) {

        return nome.length > 1;
    }

    public String[] getName() {
        return nome;
    }

    public void setName(String[] nome) throws NameException {
        if (!isValidName(nome)) {
            throw new NameException();
        }

        this.nome = nome;
    }

}
