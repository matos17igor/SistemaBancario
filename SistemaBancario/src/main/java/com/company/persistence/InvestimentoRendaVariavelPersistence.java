package com.company.persistence;

import com.company.model.InvestimentoRendaVariavel;//
import static com.company.persistence.Persistence.DIRECTORY;//
import java.lang.reflect.Type;//
import java.util.ArrayList;//
import java.util.List;//
import com.google.gson.Gson;
import java.io.*;//
import com.google.gson.reflect.TypeToken;//

public class InvestimentoRendaVariavelPersistence{

    private static final String FILE_PATH = DIRECTORY + File.separator + "investimentos_renda_variavel.json";
    private static List<InvestimentoRendaVariavel> solicitacoes = new ArrayList<>();

    // Carrega as transferências do arquivo JSON ao iniciar
    static {
        carregarSolicitacoes();
    }

    public static void adicionarSolicitacao(InvestimentoRendaVariavel investimento) {
        solicitacoes.add(investimento);
        salvarSolicitacoes();
    }

    public static List<InvestimentoRendaVariavel> getSolicitacoes() {
        return new ArrayList<>(solicitacoes);
    }

    public static void removerSolicitacao(InvestimentoRendaVariavel investimento) {
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
            Type listType = new TypeToken<ArrayList<InvestimentoRendaVariavel>>() {}.getType();
            List<InvestimentoRendaVariavel> listaCarregada = gson.fromJson(reader, listType);
            if (listaCarregada != null) {
                solicitacoes = listaCarregada;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
