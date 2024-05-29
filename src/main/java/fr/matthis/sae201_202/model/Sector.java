package fr.matthis.sae201_202.model;

public abstract class Sector {

    public Sector() {
        this.maxStockage = 0;
        this.stockage = 0;
        this.robot = null;
        this.position = new Coordonnee(0, 0);
        this.discover = false;
    }

    public Sector(int x, int y) {
        this.maxStockage = 0;
        this.stockage = 0;
        this.robot = null;
        this.position = new Coordonnee(x, y);
        this.discover = false;
    }

    protected int maxStockage;

    protected int stockage;

    protected Robots robot;

    protected Coordonnee position;
    private boolean discover;

    public boolean isDiscover() {
        return discover;
    }

    public void setDiscover(boolean discover) {
        this.discover = discover;
    }

    /* Permet d'afficher un secteur (partagé aux différents secteurs) */
    public abstract String toString();

    /* Sélectionne le robot voulu */
    public void setRobot(Robots r) {
        this.robot = r;
    }

    /* Permet de savoir si un robot peut venir */
    public boolean getDisponible() {
        if (this instanceof Lac) {
            return false;
        }
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

    public int getStockage() {
        return this.stockage;
    }
}