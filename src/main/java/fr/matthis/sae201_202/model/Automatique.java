package fr.matthis.sae201_202.model;

public class Automatique {

    private Grille grilleV1;
    private Sector[][] grilleV2;


    public Automatique(Grille g) {
        grilleV1 = g;
        grilleV2 = new Sector[10][10];
    }

    public void afficherGrille(){
        for (Sector[] s : grilleV2){
            System.out.println();
            for (Sector ss : s){
                System.out.print(ss);
            }
        }
    }
}
