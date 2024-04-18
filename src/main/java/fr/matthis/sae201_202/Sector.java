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

    /* Permet d'afficher un secteur (partagé aux différents secteurs) */
    public abstract String toString();

    /* Sélectionne le robot voulu */
    public void setRobot(Robots r) {
        this.robot = r;
    }

    /* Permet de savoir si un robot peut venir */
    public boolean getDisponible() {
        return this.robot == null;
    }

    /* Permet de récupérer le robot actuel */
    public Robots getRobot() {
        return this.robot;
    }

    /* Permet de récupérer les coordonnées actuelles */
    public Coordonnee getPosition() {
        return this.position;
    }

    public int getCapacity() {
        return this.capacity;
    }
}