package fr.matthis.sae201_202.model;

public class Vide extends Sector {

    /* Constructeur par défaut de la classe Vide */
    public Vide() {
        super();
        this.stockage = 0;
        this.robot = null;
        this.maxStockage = 0;
    }

    /* Constructeur par défaut de la classe Vide */
    public Vide(int x, int y) {
        super(x, y);
        this.stockage = 0;
        this.robot = null;
        this.maxStockage = 0;
    }

    /* Affiche une section vide */
    /*public String toString() {
        return "   |   /";
    }*/
    public String toString() {
        return "Vide{" +
                "position=" + position +
                '}';
    }
}