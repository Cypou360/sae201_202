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
        this.extraction = r.nextInt(1,3);
        this.position = new Coordonnee(0, 0);
    }

    public Robots(int x, int y, Ore type){
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,9);
        this.capacity = 0;
        this.nbDeplacement = 0;
        this.type = type;
        this.id = 0;
        this.extraction = r.nextInt(1,3);
        this.position = new Coordonnee(x, y);

    }


    private int nbDeplacement;

    private int maxCapacity;

    private int capacity;

    private Ore type;

    private int id;

    private int extraction;

    private Coordonnee position;

    public String toString() {
        return "R n°" + id  + position.getX() + position.getY() + " " + type + " " + capacity + "/" + maxCapacity;
    }

    public void extraction(Sector s) {
        if (s.getDisponible()){
            if (s instanceof Mine){
                Mine m = (Mine) s;
                if (m.getMinerai() == type){
                    if (m.capacity > 0){
                        if (m.capacity >= extraction){
                            m.capacity -= extraction;
                            capacity += extraction;
                        }
                        else{
                            capacity += m.capacity;
                            m.capacity = 0;
                        }
                    }


                    }
                }
            }

    }

    public boolean goTo(String orientation ) {
        /* x sera ordonnée et y sera en abscisse car x;y sera en position (0;0) en haut à gauche*/

        if (orientation == 'N' && position.getX() >0){
            this.position.setX(position.getX()-1);
        }

        if (orientation == 'S' && position.getX() < 10){
            this.position.setX(position.getX()+1);
        }

        if (orientation == 'O' && position.getY() >0){
            this.position.setY(position.getY()-1);
        }

        if (orientation == 'E' && position.getY() < 10){
            this.position.setY(position.getY()+1);
        }

    }



    public void  deposer(int quantite, Entrepot e){
        e.deposer(quantite);

    }

}