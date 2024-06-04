package fr.matthis.sae201_202.model;
import java.util.ArrayList;
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
        this.pioche = false;
    }

    public Robots(int x, int y, Ore type){
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,10);
        this.capacity = 0;
        this.type = type;
        this.id = idCounter;
        this.position = new Coordonnee(x, y);
        idCounter++;
        this.pioche = false;
    }

    private int maxCapacity;
    private int capacity;
    private Ore type;
    private int id;
    private Coordonnee position;
    private static int idCounter = 1;
    private boolean pioche;
    private Algo algo;
    private ArrayList<Sector> path;

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
    public Boolean extraction(Grille grille) {
        if (grille.getSector(getPosition().getX(),getPosition().getY()) instanceof Mine m){
            if (m.getMinerai() == type){
                if (m.stockage > 0){
                    Random r = new Random();
                    int extraction = 0;
                    if (maxCapacity-capacity +1 <4) {
                        extraction += r.nextInt(1, (maxCapacity - capacity + 1));
                    }else {
                        extraction += r.nextInt(1, 4);
                    }
                    if (capacity <= maxCapacity - extraction){
                        if (m.stockage >= extraction){
                            m.stockage -= extraction;
                            capacity += extraction;
                        }
                        else{
                            capacity += m.stockage;
                            m.stockage = 0;
                        }
                    } else if (capacity == maxCapacity) {
                        System.out.println("Capcity trop elevée");
                    } else{
                        capacity += (maxCapacity - capacity);
                    }
                }
            }
        }
        return false;
    }


    /* Permet au robot de se déplacer */
    public boolean goTo(String orientation, Grille grille) {
        // vérifiction NORD
        if (orientation.equals("N") && position.getX() > 0 && grille.getSector(position.getX()-1, position.getY()).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setX(position.getX()-1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else
        // vérification SUD
            if (orientation.equals("S") && position.getX() < grille.getNbLigne()-1 && grille.getSector(position.getX()+1, position.getY()).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setX(position.getX()+1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else
            // vérification EST
            if (orientation.equals("E") && position.getY() < grille.getNbColonne()-1 && grille.getSector(position.getX(), position.getY()+1).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setY(position.getY()+1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else
            // vérification OUEST
            if (orientation.equals("O") && position.getY() > 0 && grille.getSector(position.getX(), position.getY()-1).getDisponible()) {
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setY(position.getY()-1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;

        } else {
            return false;
        }
    }

    /* Permet de déposer les minerais que possède le robot dans l'entrepot approprié */
    public Boolean deposer(Grille grille){
        if (grille.getSector(getPosition().getX(),getPosition().getY()) instanceof Entrepot e){
            if (e.getType() == type){
                if (capacity != 0){
                    e.stockage += capacity;
                    capacity = 0;
                }
            }
        }
        return false;
    }


    /* Permet de récupérer l'ID du robot */
    public int getId(){
        return this.id;
    }

    /* Récupère la position du robot */
    public Coordonnee getPosition() {
        return position;
    }

    /* Récupère le type du robot */
    public Ore getType() {
        return type;
    }

    /* Récupère la capacité maximale du robot */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /* Récupère la capacité actuelle du robot */
    public int getCapacity() {
        return capacity;
    }

    /* Initialise l'idCounter du robot */
    public void setIdCounter(int idCounter) {
        Robots.idCounter = idCounter;
    }

    /* Vérifie si le robot a une pioche */
    public boolean isPioche() {
        return pioche;
    }

    /* Initialise la pioche du robot */
    public void setPioche(boolean pioche) {
        this.pioche = pioche;
    }

    public void setAlgo(Algo algo) {
        this.algo = algo;
    }

    /* Initialise le path */
    public void setPath(ArrayList<Sector> path) {
        this.path = path;
    }

    /* Execute l'algo de Dijkstra */
    public void automation(Grille grid) {
        if (algo != null) {
            Sector start = grid.getSector(position.getX(), position.getY());
            int remainingOre = grid.getRemainingOre(type);
            Sector end;
            if (remainingOre > 0 && capacity < maxCapacity) {
                end = findMine(grid);
            } else {
                end = findEntrepot(grid);
            }
            if (end != null) {
                algo.Dijkstra(start, end);
                path = algo.getPath();
            }
        }
    }

    /* Déplace le robot si le secteur désiré est libre */
    public void executePath(Sector s, Grille grid) {
        /* Vérifie si le secteur est null */
        if (!(s == null)) {
            if (getPosition().getX() < s.getPosition().getX() && getPosition().getX() < 9) { //Ouest
                /* Vérifie si le Sud est libre */
                if (grid.getSector(getPosition().getX() + 1, getPosition().getY()).getDisponible()) {
                    goTo("S", grid);
                }
                /* Vérifie si le Nord est libre */
            } else if (getPosition().getX() > s.getPosition().getX() && getPosition().getX() > 0) { //Est
                if (grid.getSector(getPosition().getX() - 1, getPosition().getY()).getDisponible()) {
                    goTo("N", grid);
                }
                /* Vérifie si l'Est est libre */
            } else if (getPosition().getY() < s.getPosition().getY() && getPosition().getY() < 9) { //Sud
                if (grid.getSector(getPosition().getX(), getPosition().getY() + 1).getDisponible()) {
                    goTo("E", grid);
                }
                /* Vérifie si l'Ouest est libre */
            } else if (getPosition().getY() > s.getPosition().getY() && getPosition().getY() > 0) { //Nord
                if (grid.getSector(getPosition().getX(), getPosition().getY() - 1).getDisponible()) {
                    goTo("O", grid);
                }
            }
        }
    }

    /* Trouve la mine approprié au robot */
    public Mine findMine(Grille grille) {
        for (Mine m : grille.getMines()) {
            if (m.getMinerai() == type && m.getStockage() > 0 && m.isDiscover()) {
                return m;
            }
        }
        return null;
    }

    /* Trouve l'entrepot approprié au robot */
    public Entrepot findEntrepot(Grille grille) {
        for (Entrepot e : grille.getEntrepots()) {
            if (e.getType() == type && e.isDiscover()) {
                return e;
            }
        }
        return null;
    }

    //TODO : faire execution dans le programme principal
    //TODO : modifier action bouton automatique eventmanager

    //partie Matthis

    /*  */
    public void exectuteChemin(Grille grille){
        ArrayList<Sector> chemin = getPath();
        if (!chemin.isEmpty()){
            executePath(chemin.get(0),grille);
            chemin.remove(0);
        }
    }

    /* Récupère le path du robot */
    public ArrayList<Sector> getPath() {
        return path;
    }
}