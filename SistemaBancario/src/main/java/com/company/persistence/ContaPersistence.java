package com.company.persistence;

import com.google.gson.reflect.TypeToken;
import com.company.model.Conta;
import static com.company.persistence.Persistence.DIRECTORY;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class ContaPersistence implements Persistence<Conta> {

    private static final String PATH = DIRECTORY+ File.separator +"contas.json";
    @Override
    public void save(List<Conta> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);


    }

    @Override
    public List<Conta> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Conta> contas = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Conta>>() {
            }.getType();
        contas = gson.fromJson(json, tipoLista);

            if (contas == null)
                contas = new ArrayList<>();
        }

        return contas;
    }


}