package fr.matthis.sae201_202;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        Grille grille = new Grille();
        grille.initialisation();
        boolean bool = true;
        int nbtour = 0;
        while (bool){
            nbtour += 1;
            System.out.println(grille);
            System.out.println("tour : " + nbtour);
            Thread.sleep(10000);

        }
    }
}