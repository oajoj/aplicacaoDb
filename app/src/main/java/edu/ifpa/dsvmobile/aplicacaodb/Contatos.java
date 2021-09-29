package edu.ifpa.dsvmobile.aplicacaodb;

public class Contatos {

    int id;
    String nome;
    String numeroCelular;

    public Contatos() {
    }

    public Contatos(String nome, String numeroCelular) {
        this.nome = nome;
        this.numeroCelular = numeroCelular;
    }

    public Contatos(int id, String nome, String numeroCelular) {
        this.id = id;
        this.nome = nome;
        this.numeroCelular = numeroCelular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
}
