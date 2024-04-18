package fr.matthis.sae201_202;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) throws InterruptedException {
        Grille grille = new Grille();
        grille.initialisation();
        boolean bool = true;
        int nombre = 0;
        int idRobots = 0;
        int nbtour = 1;
        System.out.println(grille);
        while (bool) {
            System.out.println("tour : " + nbtour);
            System.out.println(grille.afficherRecap());

            boolean[] movedRobot = new boolean[grille.getNbRobot()];
            boolean[] lstTrue = new boolean[grille.getNbRobot()];

            for (int i = 0; i < grille.getNbRobot(); i++) {
                movedRobot[i] = false;
                lstTrue[i] = true;
            }

            while (!Arrays.equals(movedRobot, lstTrue)) {
                System.out.println("quel id de robots voulez vous déplacez : ");
                Scanner nb1 = new Scanner(System.in);
                idRobots = nb1.nextInt();

                System.out.println("Robot " + idRobots);
                System.out.println(Arrays.toString(movedRobot));
                System.out.println("Donnez une direction: Droite(1), Gauche(2), Haut(3), Bas(4), Extraire(5), deposer(6), Ne rien faire(7) :");
                Scanner nb = new Scanner(System.in);
                nombre = nb.nextInt();

                System.out.println(movedRobot[idRobots - 1]);
                if (!movedRobot[idRobots - 1] || idRobots < grille.getNbRobot()) {
                    System.out.println("Robot non bouger");
                    if (nombre == 1) {
                        if (!grille.getRobot(idRobots).goTo("E", grille)) {
                            System.out.println("Déplacement Impossible");
                        } else {
                            movedRobot[idRobots - 1] = true;
                        }
                    } else if (nombre == 2) {
                        if (!grille.getRobot(idRobots).goTo("O", grille)) {
                            System.out.println("Déplacement Impossible");
                        } else {
                            movedRobot[idRobots - 1] = true;
                        }
                    } else if (nombre == 3) {
                        if (!grille.getRobot(idRobots).goTo("N", grille)) {
                            System.out.println("Déplacement Impossible");
                        } else {
                            movedRobot[idRobots - 1] = true;
                        }
                    } else if (nombre == 4) {
                        if (!grille.getRobot(idRobots).goTo("S", grille)) {
                            System.out.println("Déplacement Impossible");
                        } else {
                            movedRobot[idRobots - 1] = true;
                        }
                    } else if (nombre == 5) {
                        grille.getRobot(idRobots).extraction(grille.getSector(grille.getRobot(idRobots).getPosition().getX(), grille.getRobot(idRobots).getPosition().getY()));
                        movedRobot[idRobots - 1] = true;
                    } else if(nombre == 6){
                        grille.getRobot(idRobots).deposer(grille.getSector(grille.getRobot(idRobots).getPosition().getX(),grille.getRobot(idRobots).getPosition().getY()));
                        movedRobot[idRobots - 1] = true;
                    } else if (nombre == 7) {
                        System.out.println("Ne rien faire");
                        movedRobot[idRobots - 1] = true;
                    }
                    else {
                        System.out.println("Fin du jeu");
                        break;
                    }

                } else {
                    System.out.println("Robot déjà bouger");
                }
                System.out.println(grille);
            }

            if (nombre > 5 || idRobots > grille.getNbRobot()) {
                break;
            } else {
                System.out.println("Tour fini");
                nbtour++;
            }
        }
    }
}


