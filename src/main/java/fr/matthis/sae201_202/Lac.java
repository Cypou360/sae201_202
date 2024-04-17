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

    public String toString() {
        return "/X X/";
    }

    public boolean getDisponible() {
        return false;
    }

}