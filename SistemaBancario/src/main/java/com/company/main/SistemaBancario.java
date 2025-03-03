package com.company.main;

import com.company.exception.NameException;
import com.company.model.*;
import com.company.persistence.GerentePersistence;
import com.company.persistence.Persistence;
import com.company.persistence.ClientePersistence;
import com.company.persistence.ContaPersistence;
import com.company.view.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaBancario {

    public static void main(String[] args) {
        TelaLogin tela = new TelaLogin();
        tela.desenha();
    }
}
