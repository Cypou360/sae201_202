package fr.matthis.sae201_202;

import java.util.ArrayList;
import java.util.Random;

public class Grille {
    public Grille() {
        this.nbLigne = 10;
        this.nbColonne = 10;
        grille = new Sector[nbLigne][nbColonne];
        robots = new ArrayList<>();
    }

    private int nbLigne;

    private int nbColonne;

    private ArrayList<Robots> robots;

    private Sector[][] grille;

    public String toString() {
        String out = "";
        for (Sector[] s : grille) {
            out += "/---+---/---+---/---+---/---+---/---+---/---+---/---+---/---+---/---+---/---+---/\n/";
            for (Sector ss : s) {
                out += ss.toString();
            }
            out += "\n";
        }
        out += "/---+---/---+---/---+---/---+---/---+---/---+---/---+---/---+---/---+---/---+---/";
        return out;
    }

    public void addSector(Sector s, int x, int y) {
        //grille.add(s);
    }

    public void initialisation() {
        Random r = new Random();
        int nbMineOr = r.nextInt(1,2);
        int nbMineNickel = r.nextInt(1,2);
        int nbLac = r.nextInt(0,4);
        int nbEntrepot = 2;
        int nbVide = this.nbColonne*this.nbLigne - nbMineOr - nbMineNickel - nbLac;

        int nbRobot = r.nextInt(5);

        for (int l = 0; l < nbLigne; l++) {
            for (int c = 0; c < nbColonne; c++) {
                grille[l][c] = new Vide(l, c);
            }
        }
    }
}