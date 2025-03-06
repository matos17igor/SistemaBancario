package com.company.persistence;

import com.company.model.Saque;
import static com.company.persistence.Persistence.DIRECTORY;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaquePersistence {
    private static final String FILE_PATH = DIRECTORY + File.separator + "saques_pendentes.json";
    private static List<Saque> solicitacoes = new ArrayList<>();

    // Carrega os saques do arquivo JSON ao iniciar
    static {
        carregarSolicitacoes();
    }

    public static void adicionarSolicitacao(Saque saques) {
        solicitacoes.add(saques);
        salvarSolicitacoes();
    }

    public static List<Saque> getSolicitacoes() {
        return new ArrayList<>(solicitacoes);
    }

    public static void removerSolicitacao(Saque saques) {
        solicitacoes.remove(saques);
        salvarSolicitacoes();
    }

    // Salva a lista de transferências em um arquivo JSON
    private static void salvarSolicitacoes() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(solicitacoes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega a lista de transferências do arquivo JSON
    private static void carregarSolicitacoes() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Saque>>() {}.getType();
            List<Saque> listaCarregada = gson.fromJson(reader, listType);
            if (listaCarregada != null) {
                solicitacoes = listaCarregada;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
