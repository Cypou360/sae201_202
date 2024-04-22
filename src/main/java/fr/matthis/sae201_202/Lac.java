package fr.matthis.sae201_202;

public class Lac extends Sector {

    public Lac() {
        super();
        this.maxCapacity = 0;
        this.capacity = 0;
        this.robot = null;
    }

    public Lac(int x, int y) {
        super(x, y);
        this.maxCapacity = 0;
        this.capacity = 0;
        this.robot = null;
    }

    /* Permet d'afficher le lac */
    public String toString() {
        return "/X X/";
    }

    /* Défini que le lac est une zone ou les robots ne peuvent pas aller */
    @Override
    public boolean getDisponible() {
        return false;
    }

}