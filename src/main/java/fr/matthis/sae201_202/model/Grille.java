package fr.matthis.sae201_202.model;

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
        entrepots = new ArrayList<>();
        mines = new ArrayList<>();
    }

    private final int nbLigne;
    private final int nbColonne;
    private final ArrayList<Robots> robots;
    private final Sector[][] grille;
    private final ArrayList<Entrepot> entrepots;
    private final ArrayList<Mine> mines;

    /* Permet d'afficher la grille dans la console */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("    0       1       2       3       4       5       6       7       8       9" + "\n");
        StringBuilder tmp = new StringBuilder("/");
        for (Sector[] s : grille) {
            out.append("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n/");
            for (Sector ss : s) {
                if (ss instanceof Mine m) {
                    out.append(" ").append("M").append(" | ").append(m.getId()).append(" /");
                    if (!m.getDisponible()) {
                        tmp.append(" R | ").append(m.getRobot().getId()).append(" /");
                    }
                    else {
                        tmp.append("   |   /");
                    }
                } else if (ss instanceof Lac) {
                    out.append(" X | X /");
                    tmp.append(" X | X /");
                } else if (ss instanceof Entrepot e) {
                    out.append(" ").append(e.getNom()).append(" | ").append(e.getId()).append(" /");
                    if (!e.getDisponible()) {
                        tmp.append(" R | ").append(e.getRobot().getId()).append(" /");
                    }
                    else {
                        tmp.append("   |   /");
                    }
                } else if (ss instanceof Vide m) {
                    out.append("   |   /");
                    if (!m.getDisponible()) {
                        tmp.append(" R | ").append(m.getRobot().getId()).append(" /");
                    }
                    else {
                        tmp.append("   |   /");
                    }
                }
            }
            out.append("\n").append(tmp).append("\n");
            tmp = new StringBuilder("/");
        }
        out.append("+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+");
        return out.toString();
    }

    /* Permet d'initialiser les différents secteurs */
    public void initialisation() {
        Random r = new Random();
        int nbMineOr = r.nextInt(1,3);
        int nbMineNickel = r.nextInt(1,3);
        int nbRobot = r.nextInt(2,6);

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
                if (a > 80 && a <91){
                    grille[l][c] = new Lac(l,c);
                }
                else if(a == 91){
                    Entrepot e = new Entrepot(Ore.nickel, l, c);
                    grille[l][c] = e;
                    entrepots.add(e);
                }
                else if(a == 92){
                    Entrepot e = new Entrepot(Ore.gold, l, c);
                    grille[l][c] = e;
                    entrepots.add(e);
                }
                else if((a == 93 | a == 94) && nbMineNickel != 0){
                    nbMineNickel--;
                    Mine m = new Mine(l, c, Ore.nickel);
                    grille[l][c] = m;
                    mines.add(m);
                }
                else if((a == 95 | a == 96) && nbMineOr != 0){
                    nbMineOr--;
                    Mine m = new Mine(l, c, Ore.gold);
                    grille[l][c] = m;
                    mines.add(m);
                }
                else{
                    grille[l][c] = new Vide(l, c);
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
        StringBuilder out = new StringBuilder();
        out.append("|---------------------------|\n");
        // génération affichage mines
        for(Mine m: mines){
            out.append(m).append("\n");
        }
        // génération affichage entrepots
        for(Entrepot e: entrepots){
            out.append(e).append("\n");
        }
        // génération affichage robots
        for (Robots r : robots) {
            out.append(r).append("\n");
        }

        out.append("|---------------------------|");
        return out.toString();
    }

    public Sector[][] getGrille() {
        return grille;
    }

    public ArrayList<Robots> getRobots() {
        return robots;
    }

    public ArrayList<Entrepot> getEntrepots() {
        return entrepots;
    }

    public ArrayList<Mine> getMines() {
        return mines;
    }

    public ArrayList<Sector> getVoisin(Sector s){
        ArrayList<Sector> out = new ArrayList<>();
        Coordonnee pos = s.getPosition();
        if (pos.getX() < this.getNbLigne() - 1) {
            Sector s2 = this.getSector(pos.getX() + 1, pos.getY());
            if (s2.isDiscover() && !(s2 instanceof Lac)){
                out.add(s2);
            }
        }
        if (pos.getX() > 0) {
            Sector s2 = this.getSector(pos.getX() - 1, pos.getY());
            if (s2.isDiscover() && !(s2 instanceof Lac)){
                out.add(s2);
            }
        }
        if (pos.getY() < this.getNbColonne() - 1) {
            Sector s2 = this.getSector(pos.getX(), pos.getY() + 1);
            if (s2.isDiscover() && !(s2 instanceof Lac)){
                out.add(s2);
            }
        }
        if (pos.getY() > 0) {
            Sector s2 = this.getSector(pos.getX(), pos.getY() - 1);
            if (s2.isDiscover() && !(s2 instanceof Lac)){
                out.add(s2);
            }
        }
        return out;
    }
}