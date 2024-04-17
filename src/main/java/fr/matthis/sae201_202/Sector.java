package fr.matthis.sae201_202;

public abstract class Sector {

    public Sector() {
        this.maxCapicty = 0;
        this.capacity = 0;
        this.disponible = true;
        this.position = new Coordonnee(0, 0);
    }

    public Sector(int x, int y) {
        this.maxCapicty = 0;
        this.capacity = 0;
        this.disponible = true;
        this.position = new Coordonnee(x, y);
    }

    protected int maxCapicty;

    protected int capacity;

    protected boolean disponible;

    protected Coordonnee position;

    public abstract String toString();

    public void setDisponible(boolean s) {
        this.disponible = s;
    }

    public boolean getDisponible() {
        return this.disponible;
    }

}