package fr.matthis.sae201_202;

public class Grille {

    public Grille() {
        this.nbLigne = 10;
        this.nbColonne = 10;
        this.grille = new Sector[nbLigne][nbColonne];
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.grille[i][j] = new Mine();
            }
        }
    }

    private int nbLigne;

    private int nbColonne;
    private Sector[][] grille;

    public String toString() {
        String out = "";
        for (Sector[] i : grille) {
            out += "+---+---+---+---+---+---+---+---+---+---+\n";
            for (Sector j : i) {
                out += j.toString();
            }
            out += "\n";
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