package com.projeto.pokedex;

public class Teste {
    public static void main(String[] args) {
        Javadex Javadex = new Javadex();
        int[] result = Javadex.javadex("Paras", "Charmander");
        System.out.println("Pontos do Paras: " + result[0]);
        System.out.println("Pontos do Charmander: " + result[1]);
    }
}