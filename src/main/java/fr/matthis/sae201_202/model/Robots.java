package fr.matthis.sae201_202.model;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class Robots {


    /* Constructeur de robots par défaut */
    public Robots() {
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,10);
        this.capacity = 0;
        this.type = null;
        this.id = idCounter;
        this.position = new Coordonnee(0, 0);
        idCounter++;
        this.pioche = false;
        path = new ArrayList<>();
    }

    /* Constructeur de robots personnaliable */
    public Robots(int x, int y, Ore type){
        Random r = new Random();
        this.maxCapacity = r.nextInt(5,10);
        this.capacity = 0;
        this.type = type;
        this.id = idCounter;
        this.position = new Coordonnee(x, y);
        idCounter++;
        this.pioche = false;
        path = new ArrayList<>();
        this.end = null;
    }

    private int maxCapacity;
    private int capacity;
    private Ore type;
    private int id;
    private Coordonnee position;
    private static int idCounter = 1;
    private boolean pioche;
    private ArrayList<Sector> path;
    private Sector end;

    /* Permet de récupérer toutes les informations sur le robot */
    public String toString() {
        /* vérifie si le type du robot correspond au type de la mine */
        if (type == Ore.gold) {
            return "| R" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + capacity + " / " + maxCapacity + "\t\t|";
        }
        else {
            return "| R" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + capacity + " / " + maxCapacity + "\t\t|";
        }
    }

    /* Permet d'extraire un minerai si la mine possède le minerai adéquat */
    public Boolean extraction(Grille grille) {
        /* vérifie si le secteur actuel est une mine */
        if (grille.getSector(getPosition().getX(),getPosition().getY()) instanceof Mine m){
            /* vérifie si le type du robot correspond au type de la mine */
            if (m.getMinerai() == type) {
                if (capacity != maxCapacity) {
                    /* vérifie si la mine n'est pas vide */
                    if (m.stockage > 0) {
                        Random r = new Random();
                        int extraction = 0;
                        if (maxCapacity - capacity + 1 < 4) {
                            extraction += r.nextInt(1, (maxCapacity - capacity + 1));
                        } else {
                            extraction += r.nextInt(1, 4);
                        }
                        /* vérifie si le robot a assez de place pour prendre tout le minerai */
                        if (capacity <= maxCapacity - extraction) {
                            /* vérifie si il y a assez de minerai dans la mine pour extraire */
                            if (m.stockage >= extraction) {
                                m.stockage -= extraction;
                                capacity += extraction;
                            } else {
                                capacity += m.stockage;
                                m.stockage = 0;
                            }
                        } else if (capacity == maxCapacity) {
                            return false;
                        } else {
                            capacity += (maxCapacity - capacity);
                        }
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

        } else if (orientation.equals("S") && position.getX() < grille.getNbLigne()-1 && grille.getSector(position.getX()+1, position.getY()).getDisponible()) { // vérification SUD
                grille.getSector(position.getX(), position.getY()).setRobot(null);
                position.setX(position.getX()+1);
                grille.getSector(position.getX(), position.getY()).setRobot(this);
                return true;

        } else if (orientation.equals("E") && position.getY() < grille.getNbColonne()-1 && grille.getSector(position.getX(), position.getY()+1).getDisponible()) { // vérification EST
            grille.getSector(position.getX(), position.getY()).setRobot(null);
            position.setY(position.getY()+1);
            grille.getSector(position.getX(), position.getY()).setRobot(this);
            return true;
        } else if (orientation.equals("O") && position.getY() > 0 && grille.getSector(position.getX(), position.getY()-1).getDisponible()) { // vérification OUEST
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
        /* vérifie si le secteur est un entrepot */
        if (grille.getSector(getPosition().getX(),getPosition().getY()) instanceof Entrepot e){
            /* vérifie si le type de l'entrepot correspond au type du robot */
            if (e.getType() == type){
                /* vérifie si le robot n'est pas vide */
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


    /* Initialise le path */
    public void setPath(ArrayList<Sector> path) {
        this.path = path;
    }


    /* Execute l'algo de Dijkstra */
    public void automation(Grille grid) {
        Sector start = grid.getSector(position.getX(), position.getY());
        int remainingOre = grid.getRemainingOre(type);
        if (this.path.size() == 1){ // si le robot est sur l'arrivée
            this.path.clear();
            this.end = null;
        }

        if (start instanceof Mine && this.capacity < this.maxCapacity && this.type == ((Mine) start).getMinerai() && start.getStockage() > 0) {
            this.extraction(grid);
            return;
        } else if (start instanceof Entrepot && this.capacity != 0 && ((Entrepot) start).getType() == this.type) {
            this.deposer(grid);
            return;
        }

        //recherche du point de destination
        if (!(start instanceof Entrepot) && this.nbEntrepot(grid) > 0) {
            this.end = findEntrepot(grid);
        } else if (remainingOre > 0 && this.capacity < this.maxCapacity && !(start instanceof Mine) && this.nbMine(grid) > 0) {
            this.end = findMine(grid);
        } else {
            this.end = findSector(grid);
        }

        if (start instanceof Entrepot && this.capacity == 0) {
            this.end = findMine(grid);
        }

        if (end == null) {
            moveAleatoire(grid);
        }
        if (this.end != null && this.path.isEmpty()) {
            this.path = Dijkstrat.genPath(start, this.end, grid);
            executePath(grid);
        } else if (this.end != null) {
            executePath(grid);
        } else {
            executePath(grid); // provoque un aleatoire
        }

    }

    public void executePath(Grille grid) {
        Sector s = grid.getSector(position.getX(), position.getY());
        if (this.path.isEmpty()) {
            moveAleatoire(grid);


        } else {
            int sid = path.indexOf(grid.getSector(this.position.getX(), this.position.getY()));
            if (sid != path.size() - 1) {
                Sector s2 = path.get(sid + 1);
                if (s2.getPosition().getX() < s.getPosition().getX() && s.getPosition().getX() > 0) {
                    if (goTo("N", grid)) {
                        this.path.remove(s);
                    } else {
                        moveAleatoire(grid);
                    }
                } else if (s2.getPosition().getX() > s.getPosition().getX() && s.getPosition().getX() < grid.getNbLigne()) {
                    if (goTo("S", grid)) {
                        this.path.remove(s);
                    } else {
                        moveAleatoire(grid);
                    }

                } else if (s2.getPosition().getY() < s.getPosition().getY() && s.getPosition().getY() > 0) {
                    if (goTo("O", grid)) {
                        this.path.remove(s);
                    } else {
                        moveAleatoire(grid);
                    }
                } else if (s2.getPosition().getY() > s.getPosition().getY() && s.getPosition().getY() < grid.getNbColonne()) {
                    if (goTo("E", grid)) {
                        this.path.remove(s);
                    } else {
                        moveAleatoire(grid);
                    }
                } else {
                    moveAleatoire(grid);
                }
            }
        }
    }

    private void moveAleatoire(Grille grid) {
        Sector s = grid.getSector(this.position.getX(), this.position.getY());
        Random r = new Random();
        int i = -1;
        int cpt = 0;
        while (i == -1 && cpt < 10) {
            i = r.nextInt(4);
            if (i == 0 && s.getPosition().getX() > 0) {
                if (!goTo("N", grid)) {
                    i = -1;
                } else i = 5465;
            } else if (i == 1 && s.getPosition().getX() < grid.getNbLigne() - 1) {
                if (!goTo("S", grid)) {
                    i = -1;
                } else i = 5465;
            } else if (i == 2 && s.getPosition().getY() < grid.getNbColonne() - 1) {
                if (!goTo("E", grid)) {
                    i = -1;
                } else i = 5465;
            } else if (i == 3 && s.getPosition().getY() > 0) {
                if (!goTo("O", grid)) {
                    i = -1;
                } else i = 5465;
            } else {
                i = -1;
            }
            cpt += 1;
        }
    }

    /* Trouve la mine approprié au robot */
    public Mine findMine(Grille grille) {
        for (Mine m : grille.getMines()) {
            /* vérifie si le type du robot correspond au type de la mine, si le stockage est supperieur a 0 et si la mine est découverte */
            if (m.getMinerai() == type && m.getStockage() > 0 && m.isDiscover()) {
                return m;
            }
        }
        return null;
    }

    /* Trouve l'entrepot approprié au robot */
    public Entrepot findEntrepot(Grille grille) {
        for (Entrepot e : grille.getEntrepots()) {
            /* vérifie si le type du robot correspond au type de l'entrepôt et si l'entrepôt est découvert */
            if (e.getType() == type && e.isDiscover()) {
                return e;
            }
        }
        return null;
    }

    /* vérifie le nombre de mine découverte pour un type de minerai */
    public int nbMine(Grille grille){
        int nb = 0;
        for (Mine m : grille.getMines()) {
            if (m.isDiscover() && m.getMinerai() == this.type) {
                nb+=1;
            }
        }
        return nb;
    }

    /* vérifie le nombre d'entrepôt découvert pour un type de minerai */
    public int nbEntrepot(Grille grille){
        int nb = 0;
        for (Entrepot m : grille.getEntrepots()) {
            if (m.isDiscover() && m.getType() == this.type && this.capacity > 0) {
                nb+=1;
            }
        }
        return nb;
    }

    /* Trouve un secteur non visité */
    public Sector findSector(Grille grille) {
        ArrayList<Sector> sectors = new ArrayList<>();
        for (Sector[] s : grille.getGrille()){
            for (Sector ss : s){
                if (!ss.isDiscover() && !(ss instanceof Lac)){
                    sectors.add(ss);
                }
            }
        }
        int dist = 0;
        int good = 100;
        int x = 0;
        int y = 0;
        for (Sector d : sectors){
            dist = abs(this.getPosition().getX() - d.getPosition().getX()) + abs(this.getPosition().getY() - d.getPosition().getY());

            if (dist < good) {
                good = dist;
                x = d.getPosition().getX();
                y = d.getPosition().getY();
            }
        }
        return grille.getSector(x, y);
    }
}
