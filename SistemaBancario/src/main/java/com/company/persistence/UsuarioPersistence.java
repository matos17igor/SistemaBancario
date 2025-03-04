package com.company.persistence;

import com.google.gson.reflect.TypeToken;
import com.company.model.Usuario;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class UsuarioPersistence implements Persistence<Usuario> {

    private static final String PATH = DIRECTORY + File.separator + "usuarios.json";

    @Override
    public void save(List<Usuario> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json);

    }

    @Override
    public List<Usuario> findAll() {
        Gson gson = new Gson();

        String json = Arquivo.le(PATH);

        List<Usuario> usuarios = new ArrayList<>();
        if (!json.trim().equals("")) {

            Type tipoLista = new TypeToken<List<Usuario>>() {
            }.getType();
            usuarios = gson.fromJson(json, tipoLista);

            if (usuarios == null) {
                usuarios = new ArrayList<>();
            }
        }

        return usuarios;
    }

    @Override
    public void add(Usuario novoCliente) {
        Gson gson = new Gson();
        List<Usuario> usuariosExistentes = load(); // Carrega os clientes já salvos

        if (usuariosExistentes == null) {
            usuariosExistentes = new ArrayList<>();
        }

        usuariosExistentes.add(novoCliente); // Adiciona o novo cliente

        String json = gson.toJson(usuariosExistentes);

        File diretorio = new File(DIRECTORY);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        Arquivo.salva(PATH, json); // Salva a lista atualizada
    }

    public List<Usuario> load() {
        Gson gson = new Gson();
        File arquivo = new File(PATH);

        if (!arquivo.exists()) {
            return new ArrayList<>(); // Retorna uma lista vazia se o arquivo não existir
        }

        String json = Arquivo.le(PATH); // Método para ler o conteúdo do arquivo
        Type listType = new TypeToken<List<Usuario>>() {
        }.getType();

        return gson.fromJson(json, listType);
    }

}
