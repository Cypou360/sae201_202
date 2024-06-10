package fr.matthis.sae201_202.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Collections.shuffle;

public class Dijkstrat {
    private ArrayList<ArrayList<Sector>> deterChemin(Sector dep, Sector arv, Grille grille){
        ArrayList<ArrayList<Sector>> lstchemins = new ArrayList<>();
        ArrayList<Sector> file = new ArrayList<>();
        file.add(dep);
        ArrayList<Sector> visited = new ArrayList<>();
        visited.add(dep);

        Sector exCurrent = null;

        while (!visited.contains(arv)){
            Sector current = file.getFirst();
            file.remove(0);
            visited.add(current);
            ArrayList<Sector> vois = grille.getVoisin(current, false);

            // Ajoute des voisins non présent dans visited
            for (Sector s: vois){
                if (!visited.contains(s)){
                    file.add(s);
                }
            }

            ArrayList<Sector> tmp = new ArrayList<>();
            tmp.add(current);
            tmp.add(exCurrent);
            lstchemins.add(tmp);
            exCurrent = current;
        }
        System.out.println(lstchemins);
        return lstchemins;
    }

    public ArrayList<Sector> findShortest(Sector dep, Sector arv, Grille grille) {
        ArrayList<ArrayList<Sector>> lstChemins = this.deterChemin(dep,arv,grille);
        ArrayList<Sector> chemin = new ArrayList<>();
        chemin.add(arv);

        int arvIndex = findIndex(arv,lstChemins);

        if (arvIndex >= 0) {
            Sector prev = lstChemins.get(arvIndex).get(1);
            chemin.add(arv);

            while (prev != null) {
                chemin.add(lstChemins.get(arvIndex).get(0));
                arvIndex = findIndex(prev,lstChemins);
                if (arvIndex <= 0){
                    break;
                }
                prev = lstChemins.get(arvIndex).get(0);
            }

            chemin.reversed();
            chemin.add(dep);
            return chemin;
        }
        return null;
    }

    private int findIndex(Sector s, ArrayList<ArrayList<Sector>> lst){
        for (int i = 0; i < lst.size(); i++){
            if (lst.get(i).get(0) == s){
                return i;
            }
        }
        return -1;
    }
}
