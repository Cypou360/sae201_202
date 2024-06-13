
        package fr.matthis.sae201_202.model;

import java.util.*;

public class Dijkstrat {

    /* Constructeur personnalisé */
    public static int indexMinimum(List<Integer> list, List<Boolean> visited) {
        int mini = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            if (!visited.get(i) && value < mini) {
                mini = value;
                index = i;
            }
        }
        return index;
    }

    /* Algorithme de dijksta principal */
    public static List<Integer> dijkstra(int[][] M, int depart, List<Integer> precedents) {
        int n = M.length;

        // Initialisation des distances et des prÃ©dÃ©cesseurs
        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            distances.add(Integer.MAX_VALUE);
            precedents.add(-1);
        }
        distances.set(depart, 0);

        // Initialisation de la liste des sommets visitÃ©s
        List<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            visited.add(false);
        }

        for (int i = 0; i < n; i++) {
            // Trouver le sommet avec la distance minimale
            int u = indexMinimum(distances, visited);
            if (u == -1) break; // Tous les sommets accessibles ont Ã©tÃ© visitÃ©s

            // Marquer ce sommet comme visitÃ©
            visited.set(u, true);

            // Mettre Ã  jour les distances des sommets adjacents
            for (int v = 0; v < n; v++) {
                if (!visited.get(v) && M[u][v] != 0 && distances.get(u) != Integer.MAX_VALUE
                        && distances.get(u) + M[u][v] < distances.get(v)) {
                    distances.set(v, distances.get(u) + M[u][v]);
                    precedents.set(v, u);
                }
            }
        }

        return distances;
    }

    /* Calcul du chemin le plus court */
    public static List<Integer> cheminPlusCourt(int[][] M, int depart, int arrivee) {
        List<Integer> precedents = new ArrayList<>();
        List<Integer> distances = dijkstra(M, depart, precedents);

        // Remonter les prÃ©dÃ©cesseurs pour trouver le chemin
        List<Integer> chemin = new ArrayList<>();
        int courant = arrivee;
        while (courant != -1) {
            chemin.add(0, courant);
            courant = precedents.get(courant);
        }

        if (chemin.get(0) != depart) {
            // Si le chemin ne commence pas par le point de depart, il n'y a pas de chemin valide
            return new ArrayList<>(); // Retourner une liste vide pour indiquer qu'il n'y a pas de chemin
        }
        return chemin;
    }

    /* Génère un path */
    public static ArrayList<Sector> genPath(Sector start, Sector end, Grille grid) {
        // recupreration de la matrice d'adjacent
        int[][] M = grid.genAdjacent();

        int startId = start.getPosition().getY() * 10 + start.getPosition().getX();
        int endId = end.getPosition().getY() * 10 + end.getPosition().getX();

        ArrayList<Integer> pathMatrix = (ArrayList<Integer>) cheminPlusCourt(M, startId, endId);

        ArrayList<Sector> path = new ArrayList<>();
        for (int i = 0; i < pathMatrix.size(); i++) {
            int y = pathMatrix.get(i) / 10;
            int x = pathMatrix.get(i) % 10;
            path.add(grid.getSector(x, y));
        }

        System.out.println(path);
        System.out.println(pathMatrix);
        System.out.println();

        return path;
    }
}