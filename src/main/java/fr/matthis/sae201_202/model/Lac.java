package fr.matthis.sae201_202.model;

public class Lac extends Sector {

    /* Constructeur par défaut des lacs */
    public Lac() {
        super();
        this.maxStockage = 0;
        this.stockage = 0;
        this.robot = null;
    }

    /* Constructeurs modifiables des lacs */
    public Lac(int x, int y) {
        super(x, y);
        this.maxStockage = 0;
        this.stockage = 0;
        this.robot = null;
    }

    /* Permet d'afficher le lac */
    /*public String toString() {
        return "/X X/";
    }*/

    /* Défini que le lac est une zone ou les robots ne peuvent pas aller */
    /* @Override
    public boolean getDisponible() {
        return false;
    }*/
    public String toString() {
        return "Lac{" +
                "position=" + position +
                '}';
    }
}