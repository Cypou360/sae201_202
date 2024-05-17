package fr.matthis.sae201_202.Junite;

import fr.matthis.sae201_202.model.*;
import javafx.scene.transform.Rotate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRobot {

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

    /** Teste si un robot peut sortir de la grille **/
    @Test
    public void OutGrilleNSEO() throws Exception {
        rbts = new Robots(0, 0, Ore.gold);
        assertFalse(rbts.goTo("N", grille), "Je ne peux pas sortir de la grille vers le Nord");
        assertFalse(rbts.goTo("O", grille), "Je ne peux pas sortir de la grille vers l'Ouest");
        assertFalse(rbts.goTo("S", grille), "Je ne peux pas sortir de la grille vers le Sud");
        assertFalse(rbts.goTo("E", grille), "Je ne peux pas sortir de la grille vers l'Est");
    }

    /** Teste si un robot peut bouger dans la grille **/
    @Test
    public void MooveNSEO() throws Exception {
        assertTrue(rbts.goTo("N", grille), "Je peux me déplacer au Nord");
        assertTrue(rbts.goTo("O", grille), "Je peux me déplacer a l'Ouest");
        assertTrue(rbts.goTo("S", grille), "Je peux me déplacer au Sud");
        assertTrue(rbts.goTo("E", grille), "Je peux me déplacer a l'est");
    }


    /** Teste si un robot peut entrer dans un lac ou un autre robot **/
    @Test
    public void MooveInObjects() throws Exception {
        Lac l = new Lac(6, 5);
        Robots rbts2 = new Robots(4, 5, Ore.nickel);
        assertFalse(rbts.goTo("O", grille), "Je peux rentrer dans un autre Robot");
        assertFalse(rbts.goTo("E", grille), "Je ne peux pas rentrer dans un lac");
    }

    /** Teste si un robot entrer dans un entrepot du meme type et du type différent de lui **/
    @Test
    public void MooveInEntrepot() throws Exception{
        Entrepot EO = new Entrepot(Ore.gold, 4,5);
        Entrepot EN = new Entrepot(Ore.nickel, 6,5);
        assertTrue(rbts.goTo("O", grille), "Je peux rentrer dans l'entrepot d'Or avec un robot Or");
        assertTrue(rbts.goTo("E", grille), "Je peux rentrer dans l'entrepot de Nickel avec un robot Or");
        rbts = new Robots(7,7,Ore.nickel);
        EO = new Entrepot(Ore.gold, 7,8);
        EN = new Entrepot(Ore.nickel, 7,6);
        assertTrue(rbts.goTo("N", grille), "Je peux rentrer dans l'entrepot d'Or avec un robot de Nickel");
        assertTrue(rbts.goTo("S", grille), "Je peux rentrer dans l'entrepot de Nickel avec un robot Nickel");
    }

    /** Teste si un robot peut aller dans une mine possédant le meme type de minerai que lui et une en ayant un différent **/
    @Test
    public void MooveInMine() throws Exception{
        Mine MO = new Mine(4,5, Ore.gold);
        Robots rbts2 = new Robots(6,6,Ore.nickel);
        Mine MN = new Mine(6,5, Ore.nickel);
        assertTrue(rbts.goTo("O", grille),"Je peux rentrer dans la mine d'Or");
        assertFalse(rbts2.goTo("N", grille), "Je peux rentrer dans la mine de Nickel");
        rbts = new Robots(7,7,Ore.nickel);
        MO = new Mine(7,8, Ore.gold);
        MN = new Mine(7,6, Ore.nickel);
        assertTrue(rbts.goTo("N", grille), "Je peux rentrer dans la mine d'Or avec un robot de Nickel");
        assertTrue(rbts.goTo("S", grille), "Je peux rentrer dans la mine de Nickel avec un robot Nickel");
    }

    /** Teste si un robot peut extraire du minerai **/
    @Test
    public void Extract() throws Exception {
        Mine mO = new Mine(5, 5, Ore.gold);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Mine mN = new Mine(5,6,Ore.nickel);
        assertTrue(rbts.extraction(grille),"Je peux extraire les minerais d'Or des mines avec le robot d'Or");
        assertTrue(rbts2.extraction(grille),"Je peux extraire les minerais de Nickel des mines avec le robot de Nickel");
        mN = null;
        mO = new Mine(5, 6, Ore.gold);
        mN = new Mine(5,5,Ore.nickel);
        assertFalse(rbts.extraction(grille),"Je ne peux pas extraire les minerais d'Or des mines avec le robot de Nickel");
        assertFalse(rbts2.extraction(grille),"Je peux extraire les minerais de Nickel des mines avec le robot d'Or");
    }

    /** Teste si un robot peut déposer des minerais **/
    @Test
    public void Deposit() throws Exception {
        Entrepot mO = new Entrepot(Ore.gold, 5, 5);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Entrepot mN = new Entrepot(Ore.nickel, 5,6);
        assertTrue(rbts.deposer(grille), "Je peux déposer de l'Or dans l'entrepot d'Or");
        assertTrue(rbts2.deposer(grille), "Je peux déposer de l'Nickel dans l'entrepot de Nickel");
        mN = null;
        mO = new Entrepot(Ore.gold, 5, 6);
        mN = new Entrepot(Ore.nickel, 5,5);
        assertFalse(rbts.deposer(grille), "Je ne peux pas déposer de l'Or dans l'entrepot d'Or");
        assertFalse(rbts2.deposer(grille), "Je ne peux pas déposer de l'Nickel dans l'entrepot de Nickel");
    }

    /** Teste si un robot peut déposer des minerais en dehors des entrepots**/
    @Test
    public void DepositOutEntrepot() throws Exception {
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        assertFalse(rbts.deposer(grille),"Je ne peux pas déposer de minerai d'Or en dehors de l'entrepot");
        assertFalse(rbts2.deposer(grille),"Je ne peux pas déposer de minerai de Nickel en dehors de l'entrepot");
    }

    /** Teste si un robot peut déposer des minerais **/
    @Test
    public void ExtractOutMine() throws Exception {
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        assertFalse(rbts.extraction(grille),"Je ne peux pas extraire de minerai de Nickel en dehors de la mine");
        assertFalse(rbts2.extraction(grille),"Je ne peux pas extraire de minerai de Nickel en dehors de la mine");
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
