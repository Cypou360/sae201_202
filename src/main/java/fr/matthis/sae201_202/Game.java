package fr.matthis.sae201_202;

public class Game {

    public static void main(String[] args) {
        Grille grille = new Grille();
        grille.initialisation();
        System.out.println(grille.toString());
    }

    private int tour;

}