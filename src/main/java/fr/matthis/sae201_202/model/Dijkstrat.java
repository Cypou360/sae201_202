package fr.matthis.sae201_202.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Collections.shuffle;

public class Dijkstrat {
    private ArrayList<Sector[]> deterChemin(Sector dep, Sector arv, Grille grille){
        ArrayList<Sector[]> lstchemins = new ArrayList<>();
        ArrayList<Sector> file = new ArrayList<>();
        file.add(dep);
        ArrayList<Sector> chemin = new ArrayList<Sector>();
        ArrayList<Sector> visited = new ArrayList<Sector>();
        visited.add(dep);

        Sector exCurrent = null;

        while (!visited.contains(arv)){
            Sector current = file.getFirst();
            file.remove(0);
            chemin.add(current);
            visited.add(current);
            ArrayList<Sector> vois = grille.getVoisin(current, false);

            // Ajoute des voisins non présent dans visited
            for (Sector s: vois){
                if (!visited.contains(s)){
                    file.add(s);
                }
            }

            Sector[] tmp = new Sector[2];
            tmp[0] = current;
            tmp[1] = exCurrent;
            lstchemins.add(tmp);
            exCurrent = current;
        }
        return lstchemins;
    }

    public ArrayList<Sector> findShortest(Sector dep, Sector arv, Grille grille) {
        ArrayList<Sector[]> lstChemins = this.deterChemin(dep,arv,grille);
        ArrayList<Sector> chemin = new ArrayList<>();
        chemin.add(arv);

        int arvIndex = findIndex(arv,lstChemins);

        if (arvIndex >= 0) {
            Sector prev = lstChemins.get(arvIndex)[1];
            chemin.add(arv);

            while (prev != null) {
                chemin.add(lstChemins.get(arvIndex)[0]);
                arvIndex = findIndex(prev,lstChemins);
                if (arvIndex <= 0){
                    break;
                }
                prev = lstChemins.get(arvIndex)[1];
            }

            chemin.reversed();
            chemin.add(dep);
            return chemin;
        }
        return null;
    }

    private int findIndex(Sector s, ArrayList<Sector[]> lst){
        for (int i = 0; i < lst.size(); i++){
            if (lst.get(i)[0] == s){
                return i;
            }
        }
        return -1;
    }
}
