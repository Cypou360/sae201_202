package fr.matthis.sae201_202.model;

import org.junit.jupiter.api.*;
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
        Grille grille = new Grille();
        grille.initialisation();
        rbts = new Robots(5, 5, Ore.gold);
    }

    @Test
    void extraction() {
        /** Teste si un robot peut extraire du minerai **/
        Mine mO = new Mine(5, 5, Ore.gold);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Mine mN = new Mine(5,6,Ore.nickel);
        assertTrue("Je peux extraire les minerais d'Or des mines avec le robot d'Or", rbts.extraction(grille));
        assertTrue("Je peux extraire les minerais de Nickel des mines avec le robot de Nickel", rbts2.extraction(grille));
        mN = null;
        mO = new Mine(5, 6, Ore.gold);
        mN = new Mine(5,5,Ore.nickel);
        assertFalse("Je ne peux pas extraire les minerais d'Or des mines avec le robot de Nickel", rbts.extraction(grille));
        assertFalse("Je peux extraire les minerais de Nickel des mines avec le robot d'Or", rbts2.extraction(grille));

        /** Teste si un robot peut Extraire des minerais en dehors des mines**/
        rbts = new Robots(5, 5, Ore.gold);
        rbts2 = new Robots(5,6,Ore.nickel);
        assertFalse("Je ne peux pas extraire de minerai de Nickel en dehors de la mine", rbts.extraction(grille));
        assertFalse("Je ne peux pas extraire de minerai de Nickel en dehors de la mine", rbts2.extraction(grille));
    }

    @Test
    void goTo() {
        /** Teste si un robot peut bouger dans la grille **/
        assertTrue("Je peux me déplacer au Nord", rbts.goTo("N", grille));
        assertTrue("Je peux me déplacer a l'Ouest", rbts.goTo("O", grille));
        assertTrue("Je peux me déplacer au Sud", rbts.goTo("S", grille));
        assertTrue("Je peux me déplacer a l'est", rbts.goTo("E", grille));

        /** Teste si un robot peut sortir de la grille **/
        rbts = new Robots(0, 0, Ore.gold);
        assertFalse("Je ne peux pas sortir de la grille vers le Nord", rbts.goTo("N", grille));
        assertFalse("Je ne peux pas sortir de la grille vers l'Ouest", rbts.goTo("O", grille));
        rbts = new Robots(9,9, Ore.gold);
        assertFalse("Je ne peux pas sortir de la grille vers le Sud", rbts.goTo("S", grille));
        assertFalse("Je ne peux pas sortir de la grille vers l'Est", rbts.goTo("E", grille));

        /** Teste si un robot peut entrer dans un lac ou un autre robot **/
        rbts = new Robots(5, 5, Ore.gold);
        Lac l = new Lac(6, 5);
        Robots rbts2 = new Robots(4, 5, Ore.nickel);
        assertFalse("Je peux rentrer dans un autre Robot", rbts.goTo("O", grille));
        assertFalse("Je ne peux pas rentrer dans un lac", rbts.goTo("E", grille));

        /** Teste si un robot entrer dans un entrepot du meme type et du type différent de lui **/
        rbts = new Robots(5, 5, Ore.gold);
        Entrepot EO = new Entrepot(Ore.gold, 4,5);
        Entrepot EN = new Entrepot(Ore.nickel, 6,5);
        assertTrue("Je peux rentrer dans l'entrepot d'Or avec un robot Or", rbts.goTo("O", grille));
        assertTrue("Je peux rentrer dans l'entrepot de Nickel avec un robot Or", rbts.goTo("E", grille));
        rbts = new Robots(7,7,Ore.nickel);
        EO = new Entrepot(Ore.gold, 7,8);
        EN = new Entrepot(Ore.nickel, 7,6);
        assertTrue("Je peux rentrer dans l'entrepot d'Or avec un robot de Nickel", rbts.goTo("N", grille));
        assertTrue("Je peux rentrer dans l'entrepot de Nickel avec un robot Nickel", rbts.goTo("S", grille));

        /** Teste si un robot peut aller dans une mine possédant le meme type de minerai que lui et une en ayant un différent **/
        Mine MO = new Mine(4,5, Ore.gold);
        rbts2 = new Robots(6,6,Ore.nickel);
        Mine MN = new Mine(6,5, Ore.nickel);
        assertTrue("Je peux rentrer dans la mine d'Or", rbts.goTo("O", grille));
        assertFalse("Je peux rentrer dans la mine de Nickel", rbts2.goTo("N", grille));
        rbts = new Robots(7,7,Ore.nickel);
        MO = new Mine(7,8, Ore.gold);
        MN = new Mine(7,6, Ore.nickel);
        assertTrue("Je peux rentrer dans la mine d'Or avec un robot de Nickel", rbts.goTo("N", grille));
        assertTrue("Je peux rentrer dans la mine de Nickel avec un robot Nickel", rbts.goTo("S", grille));
    }

    @Test
    void deposer() {
        /** Teste si un robot peut déposer des minerais **/
        Entrepot mO = new Entrepot(Ore.gold, 5, 5);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Entrepot mN = new Entrepot(Ore.nickel, 5,6);
        assertTrue("Je peux déposer de l'Or dans l'entrepot d'Or", rbts.deposer(grille));
        assertTrue("Je peux déposer de Nickel dans l'entrepot de Nickel", rbts2.deposer(grille));
        mN = null;
        mO = new Entrepot(Ore.gold, 5, 6);
        mN = new Entrepot(Ore.nickel, 5,5);
        assertFalse("Je ne peux pas déposer de l'Or dans l'entrepot d'Or", rbts.deposer(grille));
        assertFalse("Je ne peux pas déposer de Nickel dans l'entrepot de Nickel", rbts2.deposer(grille));

        /** Teste si un robot peut déposer des minerais en dehors des entrepots**/
        rbts = new Robots(5, 5, Ore.gold);
        rbts2 = new Robots(5,6,Ore.nickel);
        assertFalse("Je ne peux pas déposer de minerai d'Or en dehors de l'entrepot", rbts.deposer(grille));
        assertFalse("Je ne peux pas déposer de minerai de Nickel en dehors de l'entrepot", rbts2.deposer(grille));


    }

    /** Nettoie **/
    @AfterEach
    void clean() {
        rbts = null;
        grille = null;
    }

    /** Avertit que les tests sont terminés **/
    @AfterAll
    static void advertEnd() {
        System.out.println("Tests robots terminés");
    }
}