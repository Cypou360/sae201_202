package fr.matthis.sae201_202;

public abstract class Sector {

    public Sector() {
    }

    protected int maxCapicty;

    protected int capacity;

    protected boolean disponible;

    public abstract String toString();

    public void setDisponible(boolean s) {
        // TODO implement here
    }

    public boolean getDisponible() {
        // TODO implement here
        return false;
    }

}