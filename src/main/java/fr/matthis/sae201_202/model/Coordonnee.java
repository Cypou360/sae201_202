package fr.matthis.sae201_202.model;

public class Coordonnee {

    /* Constructeur modifiable de la classe Coordonnee */
    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;

    private int y;

    /* Opération pour avoir la valeur de X */
    public int getX() {
        return x;
    }

    /* Opération pour avoir la valeur de Y */
    public int getY() {
        return y;
    }

    /* Opération pour avoir les informations  */
    public String toString() {
        return "Coordonnées (" + x +"; " + y + ")";
    }

    /* Renouvelle la valeur de X */
    public void setX(int x) {
        this.x = x;
    }

    /* Renouvelle la valeur de Y */
    public void setY(int y) {
        this.y = y;
    }

}