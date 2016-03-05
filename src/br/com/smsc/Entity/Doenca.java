package br.com.smsc.Entity;

import java.util.HashMap;

/**
 * Created by danielamorais on 3/5/16.
 */
public class Doenca {
    private String nome;
    private HashMap<String, Integer> faixaEtaria;
    private int ano;
    private int total;
    private HashMap<String, Integer> bairros;

    public String getNome() {
        return nome;
    }

    public Doenca setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public HashMap<String, Integer> getFaixaEtaria() {
        return faixaEtaria;
    }

    public Doenca setFaixaEtaria(HashMap<String, Integer> faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
        return this;
    }

    public HashMap<String, Integer> getBairros() {
        return bairros;
    }

    public Doenca setBairros(HashMap<String, Integer> bairros) {
        this.bairros = bairros;
        return this;
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
