package com.company.persistence;

import com.company.model.Credito;
import com.google.gson.reflect.TypeToken;
import static com.company.persistence.Persistence.DIRECTORY;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class CreditoPersistence implements Persistence<Credito> {

    private static final String PATH = DIRECTORY + File.separator + "creditos_pendentes.json";

    @Override
    public void save(List<Credito> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Credito> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Credito> creditos = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Credito>>() {
            }.getType();
            creditos = gson.fromJson(json, tipoLista);

            if (creditos == null) {
                creditos = new ArrayList<>();
            }
        }

        return creditos;
    }

    public List<Credito> load() {
        Gson gson = new Gson();
        File arquivo = new File(PATH);

        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        }

        String json = Arquivo.le(PATH); // Método para ler o conteúdo do arquivo
        Type listType = new TypeToken<List<Credito>>() {
        }.getType();

        return gson.fromJson(json, listType);
    }

    @Override
    public void add(Credito novoCredito) {
        Gson gson = new Gson();
        List<Credito> creditosExistentes = load(); // Carrega os Creditos já salvos

        if (creditosExistentes == null) {
            creditosExistentes = new ArrayList<>();
        }

        creditosExistentes.add(novoCredito); // Adiciona o novo Credito

        String json = gson.toJson(creditosExistentes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json); // Salva a lista atualizada
    }

}
