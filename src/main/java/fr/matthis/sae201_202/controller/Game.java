package fr.matthis.sae201_202.controller;

import fr.matthis.sae201_202.model.Entrepot;
import fr.matthis.sae201_202.model.Grille;
import fr.matthis.sae201_202.model.Mine;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Grille grille = new Grille();
        grille.initialisation();
        boolean bool = true;
        int nombre = 0;
        int idRobots = grille.getNbRobot()+1;
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

            boolean bool2 = true;
            while (!Arrays.equals(movedRobot, lstTrue) && bool2) {
                while (idRobots > grille.getNbRobot()) {
                    System.out.println("quel id de robots voulez vous déplacez : ");
                    Scanner nb1 = new Scanner(System.in);
                    idRobots = nb1.nextInt();
                }

                System.out.println("Donnez une direction: Droite(1), Gauche(2), Haut(3), Bas(4), Extraire(5), deposer(6), Ne rien faire(7) :");
                Scanner nb = new Scanner(System.in);
                nombre = nb.nextInt();

                if (!movedRobot[idRobots - 1]) {
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
                            if(grille.getSector(grille.getRobot(idRobots).getPosition().getX(), grille.getRobot(idRobots).getPosition().getY()) instanceof Mine){
                                grille.getRobot(idRobots).extraction(grille.getSector(grille.getRobot(idRobots).getPosition().getX(), grille.getRobot(idRobots).getPosition().getY()));
                                movedRobot[idRobots - 1] = true;
                            }else{
                                System.out.println("Pas possible car pas dans une mine");
                            }
                    } else if(nombre == 6){
                        if(grille.getSector(grille.getRobot(idRobots).getPosition().getX(), grille.getRobot(idRobots).getPosition().getY()) instanceof Entrepot){
                            grille.getRobot(idRobots).deposer(grille.getSector(grille.getRobot(idRobots).getPosition().getX(),grille.getRobot(idRobots).getPosition().getY()));
                            movedRobot[idRobots - 1] = true;
                        }else{
                            System.out.println("Pas possible car pas sur un entrepôt");
                        }

                    } else if (nombre == 7) {
                        System.out.println("Ne rien faire");
                        movedRobot[idRobots - 1] = true;
                    }
                    else {
                        System.out.println("Fin du jeu");
                        bool2 = false;
                    }

                }
                idRobots = grille.getNbRobot()+1;
                System.out.println(grille);
            }

            if (nombre > 8) {
                bool = false;
            } else {
                System.out.println("Tour fini");
                nbtour++;
            }
        }
    }
}


