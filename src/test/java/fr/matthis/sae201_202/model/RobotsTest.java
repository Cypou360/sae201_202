package fr.matthis.sae201_202.model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class RobotsTest {

    protected Robots rbts;
    protected Grille grille;

    /** Avertit que les tests ont commencés **/
    @BeforeAll
    static void advert() throws Exception {
        System.out.println("Test commencés");
    }

    /**
     * Initialise un robot a la position 5 et 5 et une grille a chaque début de test
     **/
    @BeforeEach
    void init() {
        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(5, 5, Ore.gold);
    }

    @Test
    void extraction() {
        /** Teste si un robot peut extraire du minerai **/
        Mine mO = new Mine(5, 5, Ore.gold);
        Mine mN = new Mine(5,6,Ore.nickel);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        System.out.println(grille);
        Assertions.assertTrue(rbts.extraction(grille), "Je peux extraire les minerais d'Or des mines avec le robot d'Or");
        Assertions.assertTrue(rbts2.extraction(grille), "Je peux extraire les minerais de Nickel des mines avec le robot de Nickel");

        grille = new Grille();
        grille.initialisation();
        mN = null;
        mO = new Mine(5, 6, Ore.gold);
        mN = new Mine(5,5,Ore.nickel);
        Assertions.assertFalse(rbts.extraction(grille), "Je ne peux pas extraire les minerais d'Or des mines avec le robot de Nickel");
        Assertions.assertFalse(rbts2.extraction(grille), "Je peux extraire les minerais de Nickel des mines avec le robot d'Or");

        /** Teste si un robot peut Extraire des minerais en dehors des mines**/
        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(5, 5, Ore.gold);
        rbts2 = new Robots(5,6,Ore.nickel);
        Assertions.assertFalse(rbts.extraction(grille), "Je ne peux pas extraire de minerai de Nickel en dehors de la mine");
        Assertions.assertFalse(rbts2.extraction(grille), "Je ne peux pas extraire de minerai de Nickel en dehors de la mine");
    }

    @Test
    void goTo() {
        /** Teste si un robot peut bouger dans la grille **/
        Assertions.assertTrue(rbts.goTo("N", grille), "Je peux me déplacer au Nord");
        Assertions.assertTrue(rbts.goTo("O", grille), "Je peux me déplacer a l'Ouest");
        Assertions.assertTrue(rbts.goTo("S", grille), "Je peux me déplacer au Sud");
        Assertions.assertTrue(rbts.goTo("E", grille), "Je peux me déplacer a l'est");

        /** Teste si un robot peut sortir de la grille **/
        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(0, 0, Ore.gold);
        Assertions.assertFalse(rbts.goTo("N", grille), "Je ne peux pas sortir de la grille vers le Nord");
        Assertions.assertFalse(rbts.goTo("O", grille), "Je ne peux pas sortir de la grille vers l'Ouest");
        rbts = new Robots(9,9, Ore.gold);
        Assertions.assertFalse(rbts.goTo("S", grille), "Je ne peux pas sortir de la grille vers le Sud");
        Assertions.assertFalse(rbts.goTo("E", grille), "Je ne peux pas sortir de la grille vers l'Est");

        /** Teste si un robot peut entrer dans un lac ou un autre robot **/
        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(5, 5, Ore.gold);
        Lac l = new Lac(6, 5);
        Robots rbts2 = new Robots(4, 5, Ore.nickel);
        Assertions.assertFalse(rbts.goTo("O", grille), "Je peux rentrer dans un autre Robot");
        Assertions.assertFalse(rbts.goTo("E", grille), "Je ne peux pas rentrer dans un lac");

        /** Teste si un robot entrer dans un entrepot du meme type et du type différent de lui **/
        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(5, 5, Ore.gold);
        Entrepot EO = new Entrepot(Ore.gold, 4,5);
        Entrepot EN = new Entrepot(Ore.nickel, 6,5);
        Assertions.assertTrue(rbts.goTo("O", grille), "Je peux rentrer dans l'entrepot d'Or avec un robot Or");
        Assertions.assertTrue(rbts.goTo("E", grille), "Je peux rentrer dans l'entrepot de Nickel avec un robot Or");

        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(7,7,Ore.nickel);
        EO = new Entrepot(Ore.gold, 7,8);
        EN = new Entrepot(Ore.nickel, 7,6);
        Assertions.assertTrue(rbts.goTo("N", grille), "Je peux rentrer dans l'entrepot d'Or avec un robot de Nickel");
        Assertions.assertTrue(rbts.goTo("S", grille), "Je peux rentrer dans l'entrepot de Nickel avec un robot Nickel");

        /** Teste si un robot peut aller dans une mine possédant le meme type de minerai que lui et une en ayant un différent **/
        grille = new Grille();
        grille.initialisation();
        Mine MO = new Mine(4,5, Ore.gold);
        rbts2 = new Robots(6,6,Ore.nickel);
        Mine MN = new Mine(6,5, Ore.nickel);
        Assertions.assertTrue(rbts.goTo("O", grille), "Je peux rentrer dans la mine d'Or");
        Assertions.assertFalse(rbts2.goTo("N", grille), "Je peux rentrer dans la mine de Nickel");

        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(7,7,Ore.nickel);
        MO = new Mine(7,8, Ore.gold);
        MN = new Mine(7,6, Ore.nickel);
        Assertions.assertTrue(rbts.goTo("N", grille), "Je peux rentrer dans la mine d'Or avec un robot de Nickel");
        Assertions.assertTrue(rbts.goTo("S", grille), "Je peux rentrer dans la mine de Nickel avec un robot Nickel");
    }

    @Test
    void deposer() {
        /** Teste si un robot peut déposer des minerais **/
        Entrepot mO = new Entrepot(Ore.gold, 5, 5);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Entrepot mN = new Entrepot(Ore.nickel, 5,6);
        Assertions.assertTrue(rbts.deposer(grille), "Je peux déposer de l'Or dans l'entrepot d'Or");
        Assertions.assertTrue(rbts2.deposer(grille), "Je peux déposer de Nickel dans l'entrepot de Nickel");

        grille = new Grille();
        grille.initialisation();
        mN = null;
        mO = new Entrepot(Ore.gold, 5, 6);
        mN = new Entrepot(Ore.nickel, 5,5);
        Assertions.assertFalse(rbts.deposer(grille), "Je ne peux pas déposer de l'Or dans l'entrepot d'Or");
        Assertions.assertFalse(rbts2.deposer(grille), "Je ne peux pas déposer de Nickel dans l'entrepot de Nickel");

        /** Teste si un robot peut déposer des minerais en dehors des entrepots**/
        grille = new Grille();
        grille.initialisation();
        rbts = new Robots(5, 5, Ore.gold);
        rbts2 = new Robots(5,6,Ore.nickel);
        Assertions.assertFalse(rbts.deposer(grille), "Je ne peux pas déposer de minerai d'Or en dehors de l'entrepot");
        Assertions.assertFalse(rbts2.deposer(grille), "Je ne peux pas déposer de minerai de Nickel en dehors de l'entrepot");


    }

    /** Nettoie **/
    @AfterEach
    void clean() {
        rbts = null;
    }

    /** Avertit que les tests sont terminés **/
    @AfterAll
    static void advertEnd() {
        System.out.println("Tests robots terminés");
    }
}