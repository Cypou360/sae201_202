package fr.matthis.sae201_202.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.shuffle;

public class Dijkstrat {
    public ArrayList<Sector> Calcul(Sector dep, Sector arv, Grille grille){
        ArrayList<ArrayList<QueuElement>> lstchemins = new ArrayList<>();
        PriorityQueue<QueuElement> file = new PriorityQueue<QueuElement>();
        file.add(new QueuElement(0, dep));
        ArrayList<Sector> chemin = new ArrayList<Sector>();
        ArrayList<Sector> visited = new ArrayList<Sector>();
        visited.add(dep);
        int[] tableau = new int[100];
        tableau[0] = file.peek().getPriority();
        while (!visited.contains(arv)){
            QueuElement current = file.poll();
            chemin.add(current.getSecteur());
            ArrayList<Sector> vois = grille.getVoisin(current.getSecteur(), false);
            // Ajoute des voisins non présent dans visited
            for (Sector s: vois){
                if (!visited.contains(s)){
                    file.offer(new QueuElement(current.getPriority()+1, s));
                }
            }
        }
        return null;
    }
}
