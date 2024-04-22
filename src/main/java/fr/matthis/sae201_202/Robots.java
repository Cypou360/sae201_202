package fr.matthis.sae201_202;
import java.util.Random;

public class Robots {

    public Robots() {
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,10);
        this.capacity = 0;
        this.type = null;
        this.id = idCounter;
        this.position = new Coordonnee(0, 0);
        idCounter++;
    }

    public Robots(int x, int y, Ore type){
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,10);
        this.capacity = 0;
        this.type = type;
        this.id = idCounter;
        this.position = new Coordonnee(x, y);
        idCounter++;
    }

    private int maxCapacity;
    private int capacity;
    private Ore type;
    private int id;
    private Coordonnee position;
    private static int idCounter = 1;

    /* Permet de récupérer toutes les informations sur le robot */
    public String toString() {
        if (type == Ore.gold) {
            return "| R" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + capacity + " / " + maxCapacity + "\t\t|";
        }
        else {
            return "| R" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + capacity + " / " + maxCapacity + "\t\t|";
        }
    }

    /* Permet d'extraire un minerai si la mine possède le minerai adéquat */
    public void extraction(Sector s) {
        if (!s.getDisponible()){
            if (s instanceof Mine m){
                if (m.getMinerai() == type){
                    if (m.capacity > 0){
                        Random r = new Random();
                        int extraction = r.nextInt(1,4);
                        if (capacity <= maxCapacity - extraction){
                            if (m.capacity >= extraction){
                                m.capacity -= extraction;
                                capacity += extraction;
                            }
                            else{
                                capacity += m.capacity;
                                m.capacity = 0;
                            }
                        } else if (capacity == maxCapacity) {
                            System.out.println("Capcity trop elevée");
                        } else{
                            capacity += (maxCapacity - capacity);
                        }
                    }
                }
            }
        }
    }

    /* Permet au robot de se déplacer */
    public boolean goTo(String orientation, Grille grille) {
        if (orientation.equals("N") && position.getX() > 0 && grille.getSector(position.getX()-1, position.getY()).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setX(position.getX()-1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else if (orientation.equals("S") && position.getX() < grille.getNbLigne()-1 && grille.getSector(position.getX()+1, position.getY()).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setX(position.getX()+1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else if (orientation.equals("E") && position.getY() < grille.getNbColonne()-1 && grille.getSector(position.getX(), position.getY()+1).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setY(position.getY()+1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else if (orientation.equals("O") && position.getY() > 0 && grille.getSector(position.getX(), position.getY()-1).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setY(position.getY()-1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else {
            return false;
        }
    }

    /* Permet de déposer les minerais que possède le robot dans l'entrepot approprié */
    public void deposer(Sector s){
        if (!s.getDisponible()){
            if (s instanceof Entrepot e){
                if (e.getType() == type){
                    if (capacity != 0){
                        e.capacity += capacity;
                        capacity = 0;
                    }
                }
            }
        }
    }

    /* Permet de récupérer l'ID du robot */
    public int getId(){
        return this.id;
    }

    public Coordonnee getPosition() {
        return position;
    }
}