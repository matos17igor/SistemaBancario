package com.company.persistence;

import com.company.model.InvestimentoRendaFixa;//
import static com.company.persistence.Persistence.DIRECTORY;//
import com.google.gson.Gson;//
import com.google.gson.reflect.TypeToken;//
import java.io.*;//
import java.lang.reflect.Type;//
import java.util.ArrayList;//
import java.util.List;//

public class InvestimentoRendaFixaPersistence {
    private static final String FILE_PATH = DIRECTORY + File.separator + "investimento_renda_fixa.json";
    private static List<InvestimentoRendaFixa> solicitacoes = new ArrayList<>();

    // Carrega as transferências do arquivo JSON ao iniciar
    static {
        carregarSolicitacoes();
    }

    public static void adicionarSolicitacao(InvestimentoRendaFixa investimento) {
        solicitacoes.add(investimento);
        salvarSolicitacoes();
    }

    public static List<InvestimentoRendaFixa> getSolicitacoes() {
        return new ArrayList<>(solicitacoes);
    }

    public static void removerSolicitacao(InvestimentoRendaFixa investimento) {
        solicitacoes.remove(investimento);
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
            Type listType = new TypeToken<ArrayList<InvestimentoRendaFixa>>() {}.getType();
            List<InvestimentoRendaFixa> listaCarregada = gson.fromJson(reader, listType);
            if (listaCarregada != null) {
                solicitacoes = listaCarregada;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
