package fr.univ_orleans.iut45.r207.tp2;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.IllegalArgumentException;

public class BiblioGraphes {
    public static List<String> getNeighborsListOf(Graph<String, DefaultEdge> g, String u){
        List<DefaultEdge> edgesList = new ArrayList<>(g.edgesOf(u));
        List<String> neighbors = new ArrayList<>();
        for (DefaultEdge edge : edgesList){
            String vertex1 = g.getEdgeSource(edge);
            String vertex2 = g.getEdgeTarget(edge);
            if (!neighbors.contains(vertex1) && !(vertex1.equals(u))){
                neighbors.add(vertex1);
            } else if (!neighbors.contains(vertex2) && !(vertex2.equals(u))){
                neighbors.add(vertex2);
            }
        }
        return neighbors;
    }

    public static Set<String> getUnion(Graph<String, DefaultEdge> graph, String u, String v){
        Set<String> voisinsCommuns = new HashSet<>();
        if (graph.containsVertex(v) && graph.containsVertex(u)){
            List<String> voisinsU = Graphs.neighborListOf(graph, u);
            List<String> voisinsV = Graphs.neighborListOf(graph, v);
            for (String voisin : voisinsU){
                if (voisin != u && voisin != v){
                    voisinsCommuns.add(voisin);
                }
            }
            for (String voisin : voisinsV){
                if (voisin != v && voisin != u){
                    voisinsCommuns.add(voisin);
                }
            }
        }
        return voisinsCommuns;
    }

    public static Set<String> voisinsNonCommuns(Graph<String, DefaultEdge> graph, String u, String v){
        Set<String> nonCommuns = new HashSet<>();
        try{
            Set<String> voisinsU = Graphs.neighborSetOf(graph, u);
            Set<String> voisinsV = Graphs.neighborSetOf(graph, v);
            for (String voisin : voisinsU){
                if (!voisinsV.contains(voisin) && voisin != v){
                    nonCommuns.add(voisin);
                }
            }
        } catch (IllegalArgumentException e){
            return new HashSet<>();
        }
        return nonCommuns;
    }

    public static boolean areTwinning(Graph<String, DefaultEdge> graph, String u, String v){
        Set<String> voisinsU = voisinsNonCommuns(graph, u, v);
        Set<String> voisinsV = voisinsNonCommuns(graph, v, u);
        return voisinsU.size() == 0 && voisinsV.size() == 0;
    }

    public static Graph<String, DefaultEdge> sousGrapheInduit(Graph<String, DefaultEdge> graph, Set<String> setSommets) throws PasDeTelSommetException{
        Graph<String, DefaultEdge> sousGraphe = new SimpleGraph<>(DefaultEdge.class);
        for (String sommet : setSommets){
            sousGraphe.addVertex(sommet);
            try{
                Set<DefaultEdge> aretes = graph.edgesOf(sommet);
                for (DefaultEdge arete : aretes){
                    String src = graph.getEdgeSource(arete);
                    String target = graph.getEdgeTarget(arete);
                    if (sousGraphe.containsVertex(src) && sousGraphe.containsVertex(target)){
                        sousGraphe.addEdge(src, target);
                    }
                }
            } catch (IllegalArgumentException e){
                throw new PasDeTelSommetException("Le sommet suivant n'est pas dans le graphe source : " + sommet);
            }
        }
        return sousGraphe;
    }

    public static Graph<String, DefaultEdge> grapheMoinsS(Graph<String, DefaultEdge> graph, Set<DefaultEdge> setAretes){
        Graph<String, DefaultEdge> sousGraphe = new SimpleGraph<>(DefaultEdge.class);
        Graphs.addGraph(sousGraphe, graph);
        sousGraphe.removeAllEdges(setAretes);
        return sousGraphe;
    }

    public static boolean chaineElementaire(List<String> chaine){
        Set<String> sommets_vus = new HashSet<>();
        for (String sommet : chaine){
            if (sommets_vus.contains(sommet)){
                return false;
            }
            sommets_vus.add(sommet);
        }
        return true;
    }
}
