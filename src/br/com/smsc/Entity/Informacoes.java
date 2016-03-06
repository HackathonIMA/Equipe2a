package br.com.smsc.Entity;

import java.util.List;
import java.util.Map;

/**
 * Created by danielamorais on 3/5/16.
 */
public class Informacoes {
    private String nome;
    private int ano;
    private List<Map<String, String>> listaCampos;
    public String getNome() {
        return nome;
    }

    public Informacoes setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public List<Map<String, String>> getListaCampos() {
        return listaCampos;
    }

    public Informacoes setListaCampos(List<Map<String, String>> listaCampos) {
        this.listaCampos = listaCampos;
        return this;
    }

    public int getAno() {
        return ano;
    }

    public Informacoes setAno(int ano) {
        this.ano = ano;
        return this;
    }

}
