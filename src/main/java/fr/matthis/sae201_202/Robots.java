package fr.matthis.sae201_202;
import java.util.Random;

public class Robots {

    public Robots() {
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,9);
        this.capacity = 0;
        this.nbDeplacement = 0;
        this.type = null;
        this.id = 0;
        this.exctration = r.nextInt(1,3);
        this.position = new Coordonnee(0, 0);
    }

    public Robots(int x, int y, Ore type){
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,9);
        this.capacity = 0;
        this.nbDeplacement = 0;
        this.type = type;
        this.id = 0;
        this.exctration = r.nextInt(1,3);
        this.position = new Coordonnee(x, y);

    }


    private int nbDeplacement;

    private int maxCapacity;

    private int capacity;

    private Ore type;

    private int id;

    private int exctration;

    private Coordonnee position;

    public String toString() {
        return "R n°" + id  + position.getX() + position.getY() + " " + type + " " + capacity + "/" + maxCapacity;
    }

    public void extration(Sector s) {
        if (s.getDisponible()){
            if (s instanceof Mine){
                Mine m = (Mine) s;
                if (m.getMinerai() == type){



                    }
                }
            }

    }

    public boolean goTo(Coordonnee c) {
        // TODO implement here
        return false;
    }



    public void  deposer(int quantite, Sector s) {
        s.deposer(quantite);

    }

}