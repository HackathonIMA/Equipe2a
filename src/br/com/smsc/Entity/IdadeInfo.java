package br.com.smsc.Entity;

/**
 * Created by danielamorais on 3/5/16.
 */
public class IdadeInfo {
    private String idade;
    private int valor;

    public String getIdade() {
        return idade;
    }

    public IdadeInfo setIdade(String idade) {
        this.idade = idade;
        return this;
    }

    public int getValor() {
        return valor;
    }

    public IdadeInfo setValor(int valor) {
        this.valor = valor;
        return this;
    }
}
