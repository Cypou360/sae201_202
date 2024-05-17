package fr.matthis.sae201_202.model;

import java.util.Random;

public class Mine extends Sector {

    public Mine() {
        super();
        Random r = new Random();
        this.maxStockage = r.nextInt(50,101);
        this.stockage = this.maxStockage;
        this.minerai = Ore.gold;
        this.id = idCounter;
        idCounter++;
    }

    public Mine(int x, int y, Ore m) {
        super(x, y);
        Random r = new Random();
        this.maxStockage = r.nextInt(50,101);
        this.stockage = this.maxStockage;
        this.minerai = m;
        this.id = idCounter;
        idCounter++;
    }

    private Ore minerai;

    private int id;
    private static int idCounter = 1;

    /* affiche toutes les informations sur la mine */
    public String toString() {
        if (minerai == Ore.gold) {
            if (this.stockage > 10){
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + stockage + " / " + maxStockage + " \t|";
            }else{
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "OR" + "  " + stockage + " / " + maxStockage + " \t\t|";

            }
        }
        else {
            if (this.stockage > 10){
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + stockage + " / " + maxStockage + " \t|";

            }else{
                return "| M" + id + "  " + position.getX() + "  " + position.getY() + "  " + "NI" + "  " + stockage + " / " + maxStockage + " \t\t|";

            }
        }
    }


    /* Montre le type de minerai disponible dans la grille */
    public Ore getMinerai() {
        return minerai;
    }

    /* Permet de montrer le type de batiment (ici, une Mine) */
    public Ore getType() {
        return minerai;
    }

    /* Affiche l'ID de la Mine */
    public String getId() {
        return Integer.toString(id);
    }

    public String getmaxStockage(){
        return Integer.toString(maxStockage);
    }

    public void setIdCounter(int idCounter) {
        Mine.idCounter = idCounter;
    }
}