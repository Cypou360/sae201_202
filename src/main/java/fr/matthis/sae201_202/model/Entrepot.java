package fr.matthis.sae201_202.model;

public class Entrepot extends Sector {

    /* Constructeur par défaut de la classe entrepot */
    public Entrepot() {
        this.type = null;
        this.position = new Coordonnee(0,0);
        this.stockage = 0;
        this.maxStockage = 999999999;
        this.id = idCounter;
        idCounter++;
    }

    /* Constructeur modifiable de la classe entrepot */
    public Entrepot( Ore type, int x, int y) {
        super();
        this.type = type;
        this.position = new Coordonnee(x,y);
        this.stockage = 0;
        this.maxStockage = 999999999;
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
            return "| E" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + stockage + "\t\t\t|";
        }
        else{
            return "| E" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + stockage + "\t\t\t|";
        }
    }


    /* Récupère le type de batiment (ici, un Entrepot) */
    public Ore getType() {
        return type;
    }

    /* Récupère le nom*/
    public String getNom(){
        return "E";
    }

    /* Récupère Id de l'entrepot */
    public String getId() {
        return Integer.toString(id);
    }

    /* Récupère l'idCounter */
    public void setIdCounter(int idCounter) {
        Entrepot.idCounter = idCounter;
    }
}