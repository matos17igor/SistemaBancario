/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.persistence;

import com.company.model.Credito;
import static com.company.persistence.Persistence.DIRECTORY;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CreditoPersistence {
    private static final String FILE_PATH = DIRECTORY + File.separator + "creditos_pendentes.json";
    private static List<Credito> solicitacoes = new ArrayList<>();

    // Carrega as transferências do arquivo JSON ao iniciar
    static {
        carregarSolicitacoes();
    }

    public static void adicionarSolicitacao(Credito credito) {
        solicitacoes.add(credito);
        salvarSolicitacoes();
    }

    public static List<Credito> getSolicitacoes() {
        return new ArrayList<>(solicitacoes);
    }

    public static void removerSolicitacao(Credito credito) {
        solicitacoes.remove(credito);
        salvarSolicitacoes();
    }

    // Salva a lista de transferências em um arquivo JSON
    private static void salvarSolicitacoes() {  //save
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
            Type listType = new TypeToken<ArrayList<Credito>>() {}.getType();
            List<Credito> listaCarregada = gson.fromJson(reader, listType);
            if (listaCarregada != null) {
                solicitacoes = listaCarregada;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
