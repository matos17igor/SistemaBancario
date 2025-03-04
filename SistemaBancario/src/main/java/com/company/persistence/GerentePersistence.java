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

    private static final String PATH = DIRECTORY + File.separator + "gerentes.json";

    @Override
    public void save(List<Gerente> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Gerente> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Gerente> gerentes = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Gerente>>() {
            }.getType();
            gerentes = gson.fromJson(json, tipoLista);

            if (gerentes == null) {
                gerentes = new ArrayList<>();
            }
        }

        return gerentes;
    }

    public List<Gerente> load() {
        Gson gson = new Gson();
        File arquivo = new File(PATH);

        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        }

        String json = Arquivo.le(PATH); // Método para ler o conteúdo do arquivo
        Type listType = new TypeToken<List<Gerente>>() {
        }.getType();

        return gson.fromJson(json, listType);
    }

    @Override
    public void add(Gerente novoCliente) {
        Gson gson = new Gson();
        List<Gerente> gerentesExistentes = load(); // Carrega os clientes já salvos

        if (gerentesExistentes == null) {
            gerentesExistentes = new ArrayList<>();
        }

        gerentesExistentes.add(novoCliente); // Adiciona o novo cliente

        String json = gson.toJson(gerentesExistentes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json); // Salva a lista atualizada
    }

}
