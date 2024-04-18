package fr.matthis.sae201_202;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        Grille grille = new Grille();
        grille.initialisation();
        boolean bool = true;
        int nbtour = 1;
        System.out.println(grille);
        while (bool){
            System.out.println("tour : " + nbtour + "\n");

            System.out.println("quel id de robots voulez vous déplacez : ");
            Scanner nb1 = new Scanner(System.in);
            int idRobots = nb1.nextInt();

            System.out.println("Donnez une direction: Droite(1), Gauche(2), Haut(3), Bas(4) :");
            Scanner nb = new Scanner(System.in);
            int nombre = nb.nextInt();

            if (nombre == 1){
                if(grille.getRobot(idRobots).goTo("E",grille)){
                    System.out.println("Déplacement Impossible");
                }
                else{
                    nbtour += 1;
                }
            }
            else if (nombre == 2) {
               if(grille.getRobot(idRobots).goTo("O",grille)){
                   System.out.println("Déplacement Impossible");
               }
               else{
                   nbtour += 1;
               }
            }
            else if (nombre == 3) {
                if(!grille.getRobot(idRobots).goTo("N",grille)){
                    System.out.println("Déplacement Impossible");
                }
                else{
                    nbtour += 1;
                }
            }
            else if (nombre == 4) {
                if(grille.getRobot(idRobots).goTo("S",grille)){
                    System.out.println("Déplacement Impossible");
                }
                else{
                    nbtour += 1;
                }
            }
            System.out.println(grille);
        }
    }
}