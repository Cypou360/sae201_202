package fr.matthis.sae201_202;

public abstract class Sector {

    public Sector() {
        this.maxCapacity = 0;
        this.capacity = 0;
        this.robot = null;
        this.position = new Coordonnee(0, 0);
    }

    public Sector(int x, int y) {
        this.maxCapacity = 0;
        this.capacity = 0;
        this.robot = null;
        this.position = new Coordonnee(x, y);
    }

    protected int maxCapacity;

    protected int capacity;

    protected Robots robot;

    protected Coordonnee position;

    public abstract String toString();

    public void setRobot(Robots r) {
        this.robot = r;
    }

    public boolean getDisponible() {
        return this.robot == null;
    }

    public Robots getRobot() {
        return this.robot;
    }

    public Coordonnee getPosition() {
        return this.position;
    }

    public int getCapacity() {
        return this.capacity;
    }

}