package fr.matthis.sae201_202.model;

public class QueuElement {
    private int priority;
    private Sector secteur;

    public QueuElement(int priority, Sector secteur) {
        this.priority = priority;
        this.secteur = secteur;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Sector getSecteur() {
        return secteur;
    }

    public void setSecteur(Sector secteur) {
        this.secteur = secteur;
    }



}
