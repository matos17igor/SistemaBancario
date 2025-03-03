package com.company.persistence;

import com.google.gson.reflect.TypeToken;
import com.company.model.Conta;
import static com.company.persistence.Persistence.DIRECTORY;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ContaPersistence implements Persistence<Conta> {

    private static final String PATH = DIRECTORY + File.separator + "contas.json";

    @Override
    public void save(List<Conta> itens) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Formatar JSON
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);

        System.out.println("Contas salvas com sucesso!"); // Debug
    }

    @Override
    public List<Conta> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        if (json == null || json.trim().isEmpty()) { // Verificação correta
            System.out.println("Nenhuma conta encontrada no arquivo!");
            return new ArrayList<>();
        }

        Type tipoLista = new TypeToken<List<Conta>>() {}.getType();
        List<Conta> contas = gson.fromJson(json, tipoLista);

        if (contas == null) {
            contas = new ArrayList<>();
        }

        System.out.println("Contas carregadas: " + contas.size()); // Debug

        return contas;
    }
}
