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
        assertFalse(rbts.goTo("N", grille));
        assertFalse(rbts.goTo("O", grille));
        assertFalse(rbts.goTo("S", grille));
        assertFalse(rbts.goTo("E", grille));
    }

    /** Teste si un robot peut bouger dans la grille **/
    @Test
    public void MooveNSEO() throws Exception {
        assertTrue(rbts.goTo("N", grille));
        assertTrue(rbts.goTo("O", grille));
        assertTrue(rbts.goTo("S", grille));
        assertTrue(rbts.goTo("E", grille));
    }


    /** Teste si un robot peut entrer dans un lac ou un autre robot **/
    @Test
    public void MooveInObjects() throws Exception {
        Lac l = new Lac(6, 5);
        Robots rbts2 = new Robots(4, 5, Ore.nickel);
        assertFalse(rbts.goTo("O", grille));
        assertFalse(rbts.goTo("E", grille));
    }

    /** Teste si un robot entrer dans un entrepot du meme type et du type différent de lui **/
    @Test
    public void MooveInEntrepot() throws Exception{
        Entrepot EO = new Entrepot(Ore.gold, 4,5);
        Entrepot EN = new Entrepot(Ore.nickel, 6,5);
        assertTrue(rbts.goTo("O", grille));
        assertFalse(rbts.goTo("E", grille));
        rbts = new Robots(7,7,Ore.nickel);
        EO = new Entrepot(Ore.gold, 7,8);
        EN = new Entrepot(Ore.nickel, 7,6);
        assertTrue(rbts.goTo("N", grille));
        assertFalse(rbts.goTo("S", grille));
    }

    /** Teste si un robot peut aller dans une mine possédant le meme type de minerai que lui et une en ayant un différent **/
    @Test
    public void MooveInMine() throws Exception{
        Mine MO = new Mine(4,5, Ore.gold);
        Mine MN = new Mine(6,5, Ore.nickel);
        assertTrue(rbts.goTo("O", grille));
        assertFalse(rbts.goTo("E", grille));
        rbts = new Robots(7,7,Ore.nickel);
        MO = new Mine(7,8, Ore.gold);
        MN = new Mine(7,6, Ore.nickel);
        assertTrue(rbts.goTo("N", grille));
        assertFalse(rbts.goTo("S", grille));
    }

    /** Teste si un robot peut extraire du minerai **/
    @Test
    public void Extract() throws Exception {
        Mine mO = new Mine(5, 5, Ore.gold);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Mine mN = new Mine(5,6,Ore.nickel);
        assertTrue(rbts.extraction(grille));
        assertTrue(rbts2.extraction(grille));
    }

    /** Teste si un robot peut déposer des minerais **/
    @Test
    public void Deposit() throws Exception {
        Entrepot mO = new Entrepot(Ore.gold, 5, 5);
        Robots rbts2 = new Robots(5,6,Ore.nickel);
        Entrepot mN = new Entrepot(Ore.nickel, 5,6);
        assertTrue(rbts.deposer(grille));
        assertTrue(rbts2.deposer(grille));
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
