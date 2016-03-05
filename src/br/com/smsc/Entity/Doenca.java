package br.com.smsc.Entity;

import java.util.HashMap;

/**
 * Created by danielamorais on 3/5/16.
 */
public class Doenca {
    private String nome;
    private int ano;
    private int total;
    private HashMap<String, IdadeInfo> bairros;

    public String getNome() {
        return nome;
    }

    public Doenca setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public HashMap<String, IdadeInfo> getBairros() {
        return bairros;
    }

    public void setBairros(HashMap<String, IdadeInfo> bairros) {
        this.bairros = bairros;
    }

    public int getAno() {
        return ano;
    }

    public Doenca setAno(int ano) {
        this.ano = ano;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public Doenca setTotal(int total) {
        this.total = total;
        return this;
    }
}
