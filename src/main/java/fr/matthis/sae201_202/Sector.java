package fr.matthis.sae201_202;

public abstract class Sector {

    public Sector() {
        this.maxCapicty = 0;
        this.capacity = 0;
        this.disponible = false;
    }

    protected int maxCapicty;

    protected int capacity;

    protected boolean disponible;

    public abstract String toString();

    public void setDisponible(boolean s) {
        // TODO implement here
    }

    public boolean getDisponible() {
        return this.disponible;
    }

}