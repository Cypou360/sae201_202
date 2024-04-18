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
        this.idCounter++;
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
        this.idCounter++;
    }

    private int nbDeplacement;

    private int maxCapacity;

    private int capacity;

    private Ore type;

    private int id;

    private int extraction;

    private Coordonnee position;

    private static int idCounter = 1;

    /* Permet de récupérer toutes les informations sur le robot */
    public String toString() {
        return "R" + id + " " + position.getX() + position.getY() + " " + type + " " + capacity + "/" + maxCapacity;
    }

    /* Permet d'extraire un minerai si la mine possède le minerai adéquat */
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

    /* Permet au robot de se déplacer */
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

    /* Permet de déposer les minerais que possède le robot dans l'entrepot approprié */
    public void deposer(int quantite, Entrepot e){
        e.deposer(quantite);
    }

    /* Permet de récupérer l'ID du robot */
    public int getId(){
        return this.id;
    }
}