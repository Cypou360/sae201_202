package fr.matthis.sae201_202.model;

public class Vide extends Sector {

    public Vide() {
        super();
        this.stockage = 0;
        this.robot = null;
        this.maxStockage = 0;
    }

    public Vide(int x, int y) {
        super(x, y);
        this.stockage = 0;
        this.robot = null;
        this.maxStockage = 0;
    }

    /* Affiche une section vide */
    public String toString() {
        return "   |   /";
    }
}