package fr.matthis.sae201_202;
import java.util.Random;

public class Robots {

    public Robots() {
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,9);
        this.capacity = 0;
        this.nbDeplacement = 0;
        this.type = null;
        this.id = idCounter;
        this.extraction = r.nextInt(1,3);
        this.position = new Coordonnee(0, 0);
        idCounter++;
    }

    public Robots(int x, int y, Ore type){
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,9);
        this.capacity = 0;
        this.nbDeplacement = 0;
        this.type = type;
        this.id = idCounter;
        this.extraction = r.nextInt(1,3);
        this.position = new Coordonnee(x, y);
        idCounter++;
    }

    private int nbDeplacement;

    private int maxCapacity;

    private int capacity;

    private Ore type;

    private int id;

    private int idCounter = 1;

    private int extraction;

    private Coordonnee position;

    public String toString() {
        return "R" + id + " " + position.getX() + position.getY() + " " + type + " " + capacity + "/" + maxCapacity;
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

    public boolean goTo(String orientation, Grille grille) {
        /* x sera ordonnée et y sera en abscisse car x;y sera en position (0;0) en haut à gauche*/
        for (int i = 0; i < grille.getNbLigne(); i++){
            for( int j = 0; j < grille.getNbColonne(); j++){
                if ("N".equals(orientation) && position.getX() > 0) {
                    if(grille.getSector(i-1,j).getDisponible()){
                        this.position.setX(position.getX()-1);
                        return true;
                    }
                }

                if ("S".equals(orientation) && position.getX() < 10){
                    if(grille.getSector(i+1,j).getDisponible()){
                        this.position.setX(position.getX()+1);
                        return true;
                    }
                }

                if ("O".equals(orientation) && position.getY() >0){
                    if(grille.getSector(i,j-1).getDisponible()){
                        this.position.setY(position.getY()-1);
                        return true;
                    }
                }

                if ("E".equals(orientation) && position.getY() < 10){
                    if(grille.getSector(i,j+1).getDisponible()){
                        this.position.setY(position.getY()+1);
                        return true;
                    }

                }
            }
        }
        return false;
    }
    public void deposer(int quantite, Entrepot e){
        e.deposer(quantite);
    }
    public int getId(){
        return this.id;
    }
}