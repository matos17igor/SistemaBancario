package com.company.persistence;

import com.company.model.Gerente;
import com.google.gson.reflect.TypeToken;
import com.company.model.Usuario;
import static com.company.persistence.Persistence.DIRECTORY;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class GerentePersistence implements Persistence<Gerente> {

    private static final String PATH = DIRECTORY+ File.separator +"gerentes.json";
    @Override
    public void save(List<Gerente> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();

        Arquivo.salva(PATH, json);


    }

    @Override
    public List<Gerente> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Gerente> gerentes = new ArrayList<>();
        if(!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Gerente>>() {
            }.getType();
        gerentes = gson.fromJson(json, tipoLista);

            if (gerentes == null)
                gerentes = new ArrayList<>();
        }

        return gerentes;
    }


}