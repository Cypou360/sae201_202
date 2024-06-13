package fr.matthis.sae201_202.model;

public class Distance {
    private int distance;
    private Sector current;
    private Sector previous;

    /* Constructeur personnalisé de la classe Distance */
    public Distance(int distance, Sector current, Sector previous) {
        this.distance = distance;
        this.current = current;
        this.previous = previous;
    }

    /* Récupère la distance actuelle */
    public int getDistance() {
        return distance;
    }

    /* Initialise la distance */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /* Récupère le secteur actuel */
    public Sector getCurrent() {
        return current;
    }

    /* Initialise le secteur actuel */
    public void setCurrent(Sector current) {
        this.current = current;
    }

    /* Récupère le secteur précédent */
    public Sector getPrevious() {
        return previous;
    }

    /* Initialise le secteur précédent */
    public void setPrevious(Sector previous) {
        this.previous = previous;
    }

    /* Affiche la distance et le secteur actuel */
    @Override
    public String toString() {
        return "Distance{" +
                "distance=" + distance +
                ", current=" + current +
                '}';
    }
}
