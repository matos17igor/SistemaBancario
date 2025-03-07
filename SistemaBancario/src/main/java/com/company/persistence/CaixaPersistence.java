/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.persistence;

import com.company.model.Caixa;
import com.google.gson.reflect.TypeToken;
import com.company.model.Usuario;
import static com.company.persistence.Persistence.DIRECTORY;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class CaixaPersistence implements Persistence<Caixa> {

    private static final String PATH = DIRECTORY + File.separator + "caixas.json";

    @Override
    public void save(List<Caixa> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Caixa> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Caixa> caixas = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Caixa>>() {
            }.getType();
            caixas = gson.fromJson(json, tipoLista);

            if (caixas == null) {
                caixas = new ArrayList<>();
            }
        }

        return caixas;
    }

    public List<Caixa> load() {
        Gson gson = new Gson();
        File arquivo = new File(PATH);

        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        }

        String json = Arquivo.le(PATH); // Método para ler o conteúdo do arquivo
        Type listType = new TypeToken<List<Caixa>>() {
        }.getType();

        return gson.fromJson(json, listType);
    }

    @Override
    public void add(Caixa novoCliente) {
        Gson gson = new Gson();
        List<Caixa> caixasExistentes = load(); // Carrega os clientes já salvos

        if (caixasExistentes == null) {
            caixasExistentes = new ArrayList<>();
        }

        caixasExistentes.add(novoCliente); // Adiciona o novo cliente

        String json = gson.toJson(caixasExistentes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json); // Salva a lista atualizada
    }

}
