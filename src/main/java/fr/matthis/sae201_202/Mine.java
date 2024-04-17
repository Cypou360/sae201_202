package fr.matthis.sae201_202;

import java.util.Random;

public class Mine extends Sector {

    public Mine() {
        super();
        Random r = new Random();
        this.maxCapacity = r.nextInt(50,100);
        this.capacity = this.maxCapacity;
    }

    private Ore minerai;

    private int id;

    public String toString() {
        return "/ " + minerai + " " + id + " /";
    }
    public Ore getMinerai() {
        return minerai;
    }

}