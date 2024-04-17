package fr.matthis.sae201_202;

public class Coordonnee {

    public Coordonnee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;

    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "Coordonnées (" + x +"; " + y + ")";
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}