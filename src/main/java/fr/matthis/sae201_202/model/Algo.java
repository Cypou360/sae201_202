package fr.matthis.sae201_202.model;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

public class Algo {

    private Grille grid;
    private ArrayList<Sector> path;
    private ArrayList<Sector> visited;
    private ArrayList<ArrayList<Sector>> lstAjd;

    public Algo(Grille grille){
        this.grid = grille;
        this.path = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.lstAjd = this.getDiscover();
    }

    public void Dijkstra(Sector start, Sector end){
        this.path.clear();
        this.visited.clear();
        this.visited.add(start);
        this.path.add(start);
        ArrayList<Sector> file = new ArrayList<>();
        Sector current = start;
        file.add(current);
        while (current.getPosition().equals(end.getPosition())) {
            ArrayList<Sector> voisin = this.grid.getVoisin(current, false);
            for (Sector s : voisin){
                if (!this.visited.contains(s)){
                    file.add(s);
                }
            }
            if (!file.isEmpty()){
                Random rand = new Random();
                current = file.get(rand.nextInt(file.size()));
                visited.add(current);
                path.add(current);
            } else {
                current = path.get(path.size()-2);
                path.remove(path.size()-1);
            }
        }
    }

    public ArrayList<Sector> getPath() {
        return path;
    }

    public void DijkstraCalculate() {
        for (Robots r : this.grid.getRobots()) {
            Sector start = this.grid.getSector(r.getPosition().getX(), r.getPosition().getY());
            Sector end = this.grid.getMines().get(0);
            Dijkstra(start, end);
        }
    }

    public ArrayList<Coordonnee> Attent(Robots rbts, Grille grille){
        ArrayList<Coordonnee> lst = new ArrayList<Coordonnee>();
        Coordonnee coord = rbts.getPosition();
        Coordonnee coord2;
        Sector sect = grille.getSector(coord.getX(), coord.getY()-1);
        if (rbts.goTo("N", grille) == sect.getDisponible()){
            coord2 = new Coordonnee(coord.getX(),coord.getY()-1);
            lst.add(coord2);
        }
        sect = grille.getSector(coord.getX(), coord.getY()+1);
        if (rbts.goTo("S", grille) == sect.getDisponible()){
            coord2 = new Coordonnee(coord.getX(),coord.getY()+1);
            lst.add(coord2);
        }
        sect = grille.getSector(coord.getX() +1, coord.getY());
        if (rbts.goTo("E", grille) == sect.getDisponible()){
            coord2 = new Coordonnee(coord.getX()+1,coord.getY());
            lst.add(coord2);
        }
        sect = grille.getSector(coord.getX()-1, coord.getY());
        if (rbts.goTo("O", grille) == sect.getDisponible()){
            coord2 = new Coordonnee(coord.getX()-1,coord.getY());
            lst.add(coord2);
        }
        return lst;
    }
    public Grille Answer(Robots rbts, Grille grille){
        Coordonnee courant = rbts.getPosition();
        Coordonnee fin = grille.getMines().get(rbts.getId()).getPosition();
        ArrayList<Coordonnee> M = new ArrayList<Coordonnee>();
        M.add(courant);
        ArrayList<Coordonnee> P = new ArrayList<Coordonnee>();
        P.add(courant);
        ArrayList<Coordonnee> A = Attent(rbts, grille);
        while (courant != fin){
            ArrayList<Coordonnee> V = new ArrayList<Coordonnee>();
            for (int i = 0; i == A.size()-1; i++){
                if (!M.contains(A.get(i))){
                    V.add(A.get(i));
                }
            }
            if (!V.isEmpty()){
                int indice = (int) (Math.random() * (V.size() - 1));
                courant = V.get(indice);
                M.add(courant);
                P.add(courant);
            }
            else {
                courant = P.get(P.size()-2);
                P.remove(P.size()-1);
            }
        }
        for (int i = 0; i == P.size()-1; i++){
            System.out.println(P.get(i));
        }
        return grille;
    }

    public ArrayList<ArrayList<Sector>> getDiscover(){
        ArrayList<ArrayList<Sector>> out = new ArrayList<>();
        ArrayList<Sector> tmp = new ArrayList<>();
        for (Sector[] s : grid.getGrille()){
            for (Sector ss : s){
                if (ss.isDiscover()) {
                    ArrayList<Sector> p = grid.getVoisin(ss, false);
                    for (Sector s2 : p) {
                        tmp.add(s2);
                    }
                    out.add(tmp);
                }
            }
        }
        return out;
    }


    //partie Matthis

    public ArrayList<Sector> djikstraUnvisited(Robots r){
        ArrayList<String> liste = new ArrayList<>();
        liste.add("N");
        liste.add("S");
        liste.add("E");
        liste.add("O");
        ArrayList<Sector> liste2 = new ArrayList<>();
        int index = (int) (Math.random() * liste.size());
        System.out.println(liste.get(index));
        liste2.add(getSectorDirection(liste.get(index),r));
        return liste2;
    }

    public Sector getSectorDirection(String n,Robots r){
        Sector s = null;
        if (n == "N"){
            if (r.getPosition().getX() > 0) {
                s = grid.getSector(r.getPosition().getX() - 1, r.getPosition().getY());
            }else {
                djikstraUnvisited(r);
            }
        }else if (n == "S"){
            if (r.getPosition().getX() < 9) {
                s = grid.getSector(r.getPosition().getX() + 1, r.getPosition().getY());
            }else {
                djikstraUnvisited(r);
            }
        }else if (n == "E"){
            if (r.getPosition().getY() < 9) {
                s = grid.getSector(r.getPosition().getX(), r.getPosition().getY() + 1);
            }else{
                djikstraUnvisited(r);
            }
        } else if (n == "O"){
            if (r.getPosition().getY() > 0) {
                s = grid.getSector(r.getPosition().getX(), r.getPosition().getY() - 1);
            }else{
                djikstraUnvisited(r);
            }
        }
        return s;
    }

    public ArrayList<ArrayList<Sector>> actionAllRobot(){
        ArrayList<ArrayList<Sector>> Allez = new ArrayList<>();
          for (Robots r : this.grid.getRobots()) {
                Allez.add(djikstraUnvisited(r));
          }
          return Allez;
    }

    public void definirChemin(ArrayList<ArrayList<Sector>> a){
        for (int i = 0; i < a.size(); i++) {
            grid.getRobot(i+1).setPath(a.get(i));
        }
        for (Robots r : grid.getRobots()) {
            r.exectuteChemin(grid);
        }
    }

    public void executeAll(){
        definirChemin(actionAllRobot());
    }
}
