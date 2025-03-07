/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.persistence;

import com.company.model.Investimento;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class InvestimentoPersistence implements Persistence<Investimento> {

    private static final String PATH = DIRECTORY + File.separator + "investimentos.json";

    @Override
    public void save(List<Investimento> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);
    }

    @Override
    public List<Investimento> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Investimento> investimentos = new ArrayList<>();
        if (!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Investimento>>() {}.getType();
            investimentos = gson.fromJson(json, tipoLista);

            if (investimentos == null) {
                investimentos = new ArrayList<>();
            }
        }

        return investimentos;
    }

    public List<Investimento> load() {
        Gson gson = new Gson();
        File arquivo = new File(PATH);

        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        }

        String json = Arquivo.le(PATH); // Método para ler o conteúdo do arquivo
        Type listType = new TypeToken<List<Investimento>>() {}.getType();

        return gson.fromJson(json, listType);
    }

    @Override
    public void add(Investimento novoInvestimento) {
        Gson gson = new Gson();
        List<Investimento> investimentosExistentes = load(); // Carrega os investimentos já salvos

        if (investimentosExistentes == null) {
            investimentosExistentes = new ArrayList<>();
        }

        investimentosExistentes.add(novoInvestimento); // Adiciona o novo investimento

        String json = gson.toJson(investimentosExistentes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json); // Salva a lista atualizada
    }
}
