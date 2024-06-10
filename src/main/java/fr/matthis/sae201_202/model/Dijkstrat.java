package fr.matthis.sae201_202.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.util.Collections.shuffle;

public class Dijkstrat {
    private ArrayList<Distance> deterChemin(Sector dep, Sector arv, Grille grille){
        ArrayList<Distance> lstchemins = new ArrayList<>();
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
                if (s != arv) {
                    file.add(s);
                }else{
                    visited.add(s);
                    lstchemins.add(new Distance(1,current,s));
                    current = s;
                    break;
                }
            }

            Distance tmp = new Distance(1,current,exCurrent);
            lstchemins.add(tmp);
            exCurrent = current;
        }
        System.out.println(lstchemins);
        return lstchemins;
    }

    public ArrayList<Sector> findShortest(Sector dep, Sector arv, Grille grille) {
        ArrayList<Distance> lstChemins = this.deterChemin(dep,arv,grille);
        ArrayList<Sector> chemin = new ArrayList<>();
        chemin.add(arv);

        int arvIndex = findIndex(arv,lstChemins);

        if (arvIndex >= 0) {
            Sector prev = lstChemins.get(arvIndex).getPrevious();

            while (prev != null) {
                chemin.add(lstChemins.get(arvIndex).getCurrent());
                arvIndex = findIndex(prev,lstChemins);
                if (arvIndex <= 0){
                    break;
                }
                prev = lstChemins.get(arvIndex).getCurrent();
            }

            chemin.add(dep);
            ArrayList<Sector> cheminInvert = new ArrayList<>();
            for (int i = chemin.size() ; i > 0 ; i--){
                cheminInvert.add(chemin.get(i-1));
            }
            System.out.println(cheminInvert);
            return cheminInvert;
        }
        return null;
    }

    private int findIndex(Sector s, ArrayList<Distance> lst){
        for (int i = 0; i < lst.size(); i++){
            if (lst.get(i).getCurrent() == s){
                return i;
            }
        }
        return -1;
    }
}
