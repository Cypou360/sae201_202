package fr.matthis.sae201_202;

import java.util.ArrayList;

public class Grille {
    public Grille() {
        this.nbLigne = 10;
        this.nbColonne = 10;
        grille = new ArrayList<Sector>();
        for(int i = 0; i != nbLigne; i++) {
            for (int j = 0; j != nbColonne; j++) {
               grille.add(new Mine());
            }
        }
    }

    private int nbLigne;

    private int nbColonne;

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
        // TODO implement here
    }

    public void addRobots(Robots r, int x, int y) {
        // TODO implement here
    }

}