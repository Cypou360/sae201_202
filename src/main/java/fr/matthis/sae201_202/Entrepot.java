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

    private int id;
    private Ore type;
    private static int idCounter = 0;

    public String toString() {
        return "E" + this.id + this.position.getX() + this.position.getY() + this.type + this.capacity;
    }

    public void deposer(int qte) {
        this.capacity += qte;
    }
    public String getType() {
        return "E";
    }
    public String getId() {
        return Integer.toString(id);
    }
}