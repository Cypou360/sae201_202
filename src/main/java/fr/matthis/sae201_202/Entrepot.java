package fr.matthis.sae201_202;

public class Entrepot extends Sector {

    public Entrepot() {
        this.id = 0;
        this.type = null;
        this.position = new Coordonnee(0,0);
        this.capacity = 0;
        this.maxCapacity = 999999999;
        this.id = idCounter;
        idCounter++;
    }
    public Entrepot( Ore type, int x, int y) {
        super();
        this.type = type;
        this.position = new Coordonnee(x,y);
        this.capacity = 0;
        this.maxCapacity = 999999999;
        this.id = idCounter;
        idCounter++;
    }

    /* maxCapacity à 999999999 pour simuler le fait de ne pas avoir de limite */
    private int id;
    private Ore type;
    private static int idCounter = 1;

    /* Avoir toutes les infos sur les entrepots */
    public String toString() {
        if (type == Ore.gold) {
            return "| E" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + capacity + "         |";
        }
        else{
            return "| E" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + capacity + "         |";
        }
    }


    /* Permet de montrer le type de batiment (ici, un Entrepot) */
    public Ore getType() {
        return type;
    }

    public String getNom(){
        return "E";
    }

    /* Permet d'avoir l'Id de l'entrepot */
    public String getId() {
        return Integer.toString(id);
    }
}