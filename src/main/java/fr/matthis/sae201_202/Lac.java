package fr.matthis.sae201_202;

public class Lac extends Sector {

    public Lac() {
        super();
        this.maxCapacity = 0;
        this.capacity = 0;
        this.disponible = false;
    }

    public Lac(int x, int y) {
        super(x, y);
        this.maxCapacity = 0;
        this.capacity = 0;
        this.disponible = false;
    }

    public String toString() {
        return "/X X/";
    }

}