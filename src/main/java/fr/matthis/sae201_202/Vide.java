package fr.matthis.sae201_202;

public class Vide extends Sector {

    public Vide() {
        super();
        this.capacity = 0;
        this.robot = null;
        this.maxCapacity = 0;
    }

    public Vide(int x, int y) {
        super(x, y);
        this.capacity = 0;
        this.robot = null;
        this.maxCapacity = 0;
    }

    public String toString() {
        return "/ " + " " + " " + " /";
    }
}