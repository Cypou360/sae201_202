package fr.matthis.sae201_202.model;

import javafx.scene.control.ChoiceBox;

import java.util.List;

public class Automatique {

    private Grille grilleV1;

    private Sector[][] grilleV2;


    public Automatique(Grille g) {
        this.grilleV1 = g;
        this.grilleV2 = new Sector[10][10];
    }

    public void afficherGrille() {
        for (Sector[] s : grilleV1.getGrille()) {
            System.out.println();
            for (Sector ss : s) {
                System.out.print(ss);
            }
        }
    }

    public void dejaExplorer() {
        for (Robots r : grilleV1.getRobots()) {
            Coordonnee pos = r.getPosition();
            grilleV2[pos.getX()][pos.getY()] = grilleV1.getSector(pos.getX(), pos.getY());
            if (pos.getX() < grilleV1.getNbLigne() - 1) {
                grilleV2[pos.getX() + 1][pos.getY()] = grilleV1.getSector(pos.getX() + 1, pos.getY());
            }
            if (pos.getX() > 0) {
                grilleV2[pos.getX() - 1][pos.getY()] = grilleV1.getSector(pos.getX() - 1, pos.getY());
            }
            if (pos.getY() < grilleV1.getNbColonne() - 1) {
                grilleV2[pos.getX()][pos.getY() + 1] = grilleV1.getSector(pos.getX(), pos.getY() + 1);
            }
            if (pos.getY() > 0) {
                grilleV2[pos.getX()][pos.getY() - 1] = grilleV1.getSector(pos.getX(), pos.getY() - 1);
            }
        }
    }

    public Sector[][] getGrilleV2(){
        return grilleV2;
    }
}


