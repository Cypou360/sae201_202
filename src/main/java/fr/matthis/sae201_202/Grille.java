package fr.matthis.sae201_202;

import java.util.ArrayList;

public class Grille {
    public Grille() {
        this.nbLigne = 10;
        this.nbColonne = 10;
        grille = new ArrayList<>();
        robots = new ArrayList<>();
    }

    private int nbLigne;

    private int nbColonne;

    private ArrayList<Robots> robots;

    private ArrayList<Sector> grille;

    public String toString() {
        String out = "";
        int tmp = -1;
        for (Sector sector : grille) {
            tmp += 1;
            if (tmp % 10 == 0){
                out += "\n" + sector;
            }

            else{
                out += "" + sector;
            }
        }
        return out;
    }

    public void addSector(Sector s, int x, int y) {
        grille.add(s);
    }

    public void addRobots(Robots r, int x, int y) {
        // TODO implement here
        robots.add(new Robots());
    }
}