package fr.univ_orleans.iut45.r207.tp2;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Set;
import java.util.HashSet;

/**
 * Hello JGraphT!
 */
public class App {
	public static Graph<String, DefaultEdge> graph1(){
		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

		graph.addVertex("a");
		graph.addVertex("b");
		graph.addVertex("c");
		graph.addVertex("d");
		graph.addVertex("e");

		graph.addEdge("a", "b");
		graph.addEdge("a", "c");
		graph.addEdge("b", "c");
		graph.addEdge("c", "d");
		graph.addEdge("d", "e");

		return graph;
	}
	public static void main(String[] args) {
		Graph<String, DefaultEdge> graph1 = graph1();
		System.out.println(graph1());

		System.out.println(BiblioGraphes.getNeighborsListOf(graph1, "a"));

		System.out.println(BiblioGraphes.getUnion(graph1, "a", "b"));
		
		System.out.println(BiblioGraphes.voisinsNonCommuns(graph1, "a", "e"));
		System.out.println(BiblioGraphes.voisinsNonCommuns(graph1, "a", "b"));
		System.out.println(BiblioGraphes.voisinsNonCommuns(graph1, "a", "d"));
		System.out.println(BiblioGraphes.voisinsNonCommuns(graph1, "a", "j"));

		Set<String> sommetsSousGraphe1 = new HashSet<>();
		sommetsSousGraphe1.add("a");
		sommetsSousGraphe1.add("b");
		sommetsSousGraphe1.add("l");

		Set<String> sommetsSousGraphe2 = new HashSet<>();
		sommetsSousGraphe2.add("a");
		sommetsSousGraphe2.add("b");
		sommetsSousGraphe2.add("c");

		try{
			System.out.println(BiblioGraphes.sousGrapheInduit(graph1, sommetsSousGraphe2));
			System.out.println(BiblioGraphes.sousGrapheInduit(graph1, sommetsSousGraphe1));
		} catch (PasDeTelSommetException e){
			System.out.println(e.getMessage());
		}
		
	}
	
}
