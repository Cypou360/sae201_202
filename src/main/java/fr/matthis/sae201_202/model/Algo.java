package fr.matthis.sae201_202.model;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Algo {

    private Grille grid;
    private ArrayList<Sector> path;
    private ArrayList<Sector> visited;
    private ArrayList<ArrayList<Sector>> lstAjd;

    public Algo(Grille grille){
        this.grid = grille;
        this.path = new ArrayList<>();
        this.visited = new ArrayList<>();
    }

    public void Dijkstra(Sector start, Sector end){
        this.visited.add(start);
        this.path.add(start);
        Queue<Sector> file = new ArrayDeque<>();
        Sector current = start;
        file.add(current);
        while (current.getPosition().equals(end.getPosition())) {
            current = file.poll();
            file.addAll(grid.getVoisin(current));
            for (Sector s : grid.getVoisin(current)){
                if (!visited.contains(s)){
                    visited.add(s);
                    path.add(s);
                }
            }
        }
    }

    public ArrayList<Sector> getPath() {
        return path;
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
                    ArrayList<Sector> p = grid.getVoisin(ss);
                    for (Sector s2 : p) {
                        tmp.add(s2);
                    }
                    out.add(tmp);
                }
            }
        }
        return out;
    }
}
