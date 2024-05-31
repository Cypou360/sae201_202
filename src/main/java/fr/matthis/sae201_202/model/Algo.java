package fr.matthis.sae201_202.model;

import java.util.ArrayList;

public class Algo {
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
}
