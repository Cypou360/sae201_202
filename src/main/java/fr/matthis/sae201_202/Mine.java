package fr.matthis.sae201_202;

import java.util.Random;

public class Mine extends Sector {

    public Mine() {
        super();
        Random r = new Random();
        this.maxCapacity = r.nextInt(50,100);
        this.capacity = this.maxCapacity;
        this.minerai = Ore.gold;
    }

    public Mine(int x, int y, Ore m) {
        super(x, y);
        Random r = new Random();
        this.maxCapacity = r.nextInt(50,100);
        this.capacity = this.maxCapacity;
        this.minerai = m;
    }

    private Ore minerai;

    private int id;

    public String toString() {
        return "/ " + minerai + " " + id + " /";
    }
    public Ore getMinerai() {
        return minerai;
    }

    public String getType() {
        return "M";
    }
    public String getId() {
        return Integer.toString(id);
    }

}