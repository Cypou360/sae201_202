package fr.matthis.sae201_202.model;

import java.util.Objects;

public abstract class Sector {

    /* Constructeur par défaut de la classe secteur */
    public Sector() {
        this.maxStockage = 0;
        this.stockage = 0;
        this.robot = null;
        this.position = new Coordonnee(0, 0);
        this.discover = false;
    }

    /* Constructeur modifiable de la classe secteur */
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

    /* Récupère si le secteur est découvert*/
    public boolean isDiscover() {
        return discover;
    }

    /* Initialise si le secteur est découvert */
    public void setDiscover(boolean discover) {
        this.discover = discover;
    }

    /* Affiche un secteur (partagé aux différents secteurs) */
    public abstract String toString();

    /* Sélectionne le robot voulu */
    public void setRobot(Robots r) {
        this.robot = r;
    }

    /* Récupère si un robot peut venir */
    public boolean getDisponible() {
        /* vérifie si le secteur est un lac */
        if (this instanceof Lac) {
            return false;
        }
        return this.robot == null;
    }

    /* Récupère le robot actuel */
    public Robots getRobot() {
        return this.robot;
    }

    /* Récupere les coordonnées actuelles */
    public Coordonnee getPosition() {
        return this.position;
    }

    /* Récupère le stockage du secteur actuel */
    public int getStockage() {
        return this.stockage;
    }

    /* Compare 2 secteurs */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sector sector)) return false;
        return maxStockage == sector.maxStockage && getStockage() == sector.getStockage() && isDiscover() == sector.isDiscover() && Objects.equals(getRobot(), sector.getRobot()) && Objects.equals(getPosition(), sector.getPosition());
    }

    /* Récupère les hash du Stockage max, stockage actuel, robot, position et de la condition si le secteur est découvert */
    @Override
    public int hashCode() {
        return Objects.hash(maxStockage, getStockage(), getRobot(), getPosition(), isDiscover());
    }
}