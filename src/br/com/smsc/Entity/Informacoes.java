package br.com.smsc.Entity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by danielamorais on 3/5/16.
 */
public class Informacoes {
    private String nome;
    private int ano;
    private int total;
    private HashMap<String, List<IdadeInfo>> bairros;

    public String getNome() {
        return nome;
    }

    public Informacoes setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public HashMap<String, List<IdadeInfo>> getBairros() {
        return bairros;
    }

    public Informacoes setBairros(HashMap<String, List<IdadeInfo>> bairros) {
        this.bairros = bairros;
        return this;
    }

    public int getAno() {
        return ano;
    }

    public Informacoes setAno(int ano) {
        this.ano = ano;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public Informacoes setTotal(int total) {
        this.total = total;
        return this;
    }
}
