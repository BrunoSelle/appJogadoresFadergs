package com.selle.appjogares;

public class Jogador {

    public int id;
    public String nome;
    public int numero;

    public Jogador() {

    }

    public Jogador(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public Jogador(int id, String nome, int numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
    }


}
