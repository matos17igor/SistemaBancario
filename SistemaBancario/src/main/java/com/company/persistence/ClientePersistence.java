package com.company.persistence;

import com.company.model.Cliente;
import com.google.gson.reflect.TypeToken;
import com.company.model.Usuario;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class ClientePersistence implements Persistence<Cliente> {

    private static final String PATH = DIRECTORY+ File.separator +"clientes.json";
    @Override
    public void save(List<Cliente> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);


    }

    @Override
    public List<Cliente> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Cliente> clientes = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Cliente>>() {
            }.getType();
        clientes = gson.fromJson(json, tipoLista);

            if (clientes == null)
                clientes = new ArrayList<>();
        }

        return clientes;
    }


}