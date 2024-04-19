package fr.matthis.sae201_202;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;

public class Grille {
    public Grille() {
        this.nbLigne = 10;
        this.nbColonne = 10;
        grille = new Sector[nbLigne][nbColonne];
        robots = new ArrayList<>();
    }

    private int nbLigne;

    private int nbColonne;

    private ArrayList<Robots> robots;

    private Sector[][] grille;

    /* Permet d'afficher la grille dans la console */
    public String toString() {
        String out = "";
        out += "    0       1       2       3       4       5       6       7       8       9" + "\n";
        String tmp = "/";
        for (Sector[] s : grille) {
            out += "+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n/";
            for (Sector ss : s) {
                if (ss instanceof Mine) {
                    Mine m = (Mine) ss;
                    out += " " + m.getType() + " | " + m.getId() + " /";
                    if (!m.getDisponible()) {
                        tmp += " R | " + m.getRobot().getId() + " /";
                    }
                    else {
                        tmp += "   |   /";
                    }
                } else if (ss instanceof Lac) {
                    out += " X | X /";
                    tmp += " X | X /";
                } else if (ss instanceof Entrepot) {
                    Entrepot e = (Entrepot) ss;
                    out += " " + e.getNom() + " | " + e.getId() + " /";
                    if (!e.getDisponible()) {
                        tmp += " R | " + e.getRobot().getId() + " /";
                    }
                    else {
                        tmp += "   |   /";
                    }
                } else if (ss instanceof Vide) {
                    out += "   |   /";
                    Vide m = (Vide) ss;
                    if (!m.getDisponible()) {
                        tmp += " R | " + m.getRobot().getId() + " /";
                    }
                    else {
                        tmp += "   |   /";
                    }
                }
            }
            out += "\n" + tmp + "\n";
            tmp = "/";
        }
        out += "+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+";
        return out;
    }

    /* Permet d'initialiser les différents secteurs */
    public void initialisation() {
        Random r = new Random();
        int nbMineOr = r.nextInt(1,3);
        int nbMineNickel = r.nextInt(1,3);
        int nbRobot = r.nextInt(2,4);
        Integer[] Entier = new Integer[100];
        int tmp = -1;
        for(int i = 0; i!= 100; i++){
            tmp++;
            Entier[i] = tmp;
        }
        shuffle(asList(Entier));
        int entier = -1;
        for (int l = 0; l < nbLigne; l++) {
            for (int c = 0; c < nbColonne; c++) {
                entier++;
                int a = Entier[entier];
                if (a < 91) {
                    grille[l][c] = new Vide(l, c);
                }
                else if(a == 91){
                    grille[l][c] = new Entrepot(Ore.nickel, l, c);
                }
                else if(a == 92){
                    grille[l][c] = new Entrepot(Ore.gold,l,c);
                }
                else if((a == 93 | a == 94) && nbMineNickel != 0){
                    nbMineNickel--;
                    grille[l][c] = new Mine(l, c, Ore.nickel);
                }
                else if((a == 95 | a == 96) && nbMineOr != 0){
                    nbMineOr--;
                    grille[l][c] = new Mine(l, c, Ore.gold);
                }
                else{
                    grille[l][c] = new Lac(l, c);
                }
            }
        }
        for (int i = 0; i < nbRobot; i++) {
            int x = r.nextInt(0, 10);
            int y = r.nextInt(0, 10);
            boolean dispo = false;
            while (!dispo) {
                if (grille[x][y].getDisponible()) {
                    dispo = true;
                } else {
                    x = r.nextInt(0, 10);
                    y = r.nextInt(0, 10);
                }
            }
            if (i % 2 == 0) {
                Robots ro = new Robots(x, y, Ore.nickel);
                robots.add(ro);
                Sector s = this.getSector(x, y);
                s.setRobot(ro);
            } else {
                Robots ro = new Robots(x, y, Ore.gold);
                robots.add(ro);
                Sector s = this.getSector(x, y);
                s.setRobot(ro);
            }
        }
    }

    /* Permet de récuperer le secteur situé dans l'emplacement voulu */
    public Sector getSector(int x, int y){
        return grille[x][y];
    }

    /* Permet de récupérer le nombre de colones */
    public int getNbColonne() {
        return nbColonne;
    }

    /* Permet de récupérer le nombre de lignes */
    public int getNbLigne() {
        return nbLigne;
    }

    public Robots getRobot(int id) {
        for (Robots r : robots) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
    public int getNbRobot(){
        return robots.size();
    }

    public String afficherRecap(){
        String out = "";
        out += "|-------------------------|\n";
        for(Sector[] s: grille) {
            for (Sector ss : s) {
                if (ss instanceof Mine) {
                    Sector e = (Mine) ss;
                    out += e + "\n";
                }
            }
        }
        for(Sector[] s: grille){
            for (Sector ss: s){
                if (ss instanceof Entrepot){
                    Sector e = (Entrepot) ss;
                    out += e + "\n";
                }
            }
        }
        int tmp = 0;
        for(Sector[] s: grille){
            tmp +=1;
            for (Sector ss: s) {
                if ((!ss.getDisponible()) && (ss.getRobot() !=null)){
                    out += ss.getRobot();
                    out += "\n";
                }
            }
        }

        out += "|-------------------------|";
        return out;
    }
}