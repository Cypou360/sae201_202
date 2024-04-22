package fr.matthis.sae201_202;

import java.util.Random;

public class Mine extends Sector {

    public Mine() {
        super();
        Random r = new Random();
        this.maxCapacity = r.nextInt(50,101);
        this.capacity = this.maxCapacity;
        this.minerai = Ore.gold;
        this.id = idCounter;
        idCounter++;
    }

    public Mine(int x, int y, Ore m) {
        super(x, y);
        Random r = new Random();
        this.maxCapacity = r.nextInt(50,101);
        this.capacity = this.maxCapacity;
        this.minerai = m;
        this.id = idCounter;
        idCounter++;
    }

    private Ore minerai;

    private int id;
    private static int idCounter = 1;

    /* affiche toutes les informations sur la mine */
    public String toString() {
        if (capacity < 100) {
            if (minerai == Ore.gold) {
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + capacity + " / " + maxCapacity + "   |";
            } else {
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + capacity + " / " + maxCapacity + "   |";
            }
        }else{
            if (minerai == Ore.gold) {
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + capacity + " / " + maxCapacity + " |";
            } else {
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + capacity + " / " + maxCapacity + " |";
            }
        }
    }


    /* Montre le type de minerai disponible dans la grille */
    public Ore getMinerai() {
        return minerai;
    }

    /* Permet de montrer le type de batiment (ici, une Mine) */
    public String getType() {
        return "M";
    }

    /* Affiche l'ID de la Mine */
    public String getId() {
        return Integer.toString(id);
    }

}