
/**
 * Graph.java
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C, Lab 6
 */

import java.util.ArrayList;

public class Graph {
	private int vertices;
	private int edges;
	private ArrayList<LinkedList<Integer>> adj;
	private ArrayList<Character> color;
	private ArrayList<Integer> distance;
	private ArrayList<Integer> parent;
	private ArrayList<Integer> discoverTime;
	private ArrayList<Integer> finishTime;
	private static int time = 0;

	/** Constructors and Destructors */

	/**
	 * initializes an empty graph, with n vertices and 0 edges
	 * 
	 * @param n the number of vertices in the graph
	 */
	public Graph(int n) {
		vertices = n;
		edges = 0;
		adj = new ArrayList<LinkedList<Integer>>(n);
		color = new ArrayList<>(n + 1);
		// System.out.println(color.);
		distance = new ArrayList<Integer>(n);
		parent = new ArrayList<Integer>(n);
		discoverTime = new ArrayList<>(n);
		finishTime = new ArrayList<>(n);
		

		for (int i = 0; i < n + 1; i++) {
			color.add('W');
			distance.add(-1);
			parent.add(0);
			adj.add(new LinkedList<Integer>());
			discoverTime.add(-1);
			finishTime.add(-1);
		}
	}

	/*** Accessors ***/

	/**
	 * Returns the number of edges in the graph
	 * 
	 * @return the number of edges
	 */
	public int getNumEdges() {
		return edges;
	}

	/**
	 * Returns the number of vertices in the graph
	 * 
	 * @return the number of vertices
	 */
	public int getNumVertices() {
		return vertices;
	}

	/**
	 * returns whether the graph is empty (no edges)
	 * 
	 * @return whether the graph is empty
	 */
	public boolean isEmpty() {
		return edges == 0;
	}

	/**
	 * Returns the value of the distance[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the distance of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getDistance(Integer v) throws IndexOutOfBoundsException {
		if (v < 0 && v > vertices) {
			throw new IndexOutOfBoundsException("getDistance(): vertex is not on graph");
		}
		return distance.get(v);
	}

	/**
	 * Returns the value of the parent[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the parent of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getParent(Integer v) throws IndexOutOfBoundsException {
		if (v < 0 && v > vertices) {
			throw new IndexOutOfBoundsException("getParent(): vertex is not on graph");
		}
		return parent.get(v);
	}

	/**
	 * Returns the value of the color[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the color of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Character getColor(Integer v) throws IndexOutOfBoundsException {
		if (v < 0 && v > vertices) {
			throw new IndexOutOfBoundsException("getColor(): vertex is not on graph");
		}
		return color.get(v);
	
	}

	/**
	 * Returns the value of the discoverTime[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the discover time of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getDiscoverTime(Integer v) throws IndexOutOfBoundsException {
		if (v < 0 && v > vertices) {
			throw new IndexOutOfBoundsException("getDistance(): vertex is not on graph");
		}
		return discoverTime.get(v);
	}

	/**
	 * Returns the value of the finishTime[v]
	 * 
	 * @param v a vertex in the graph
	 * @precondition 0 < v <= vertices
	 * @return the finish time of vertex v
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public Integer getFinishTime(Integer v) throws IndexOutOfBoundsException {
		if (v < 0 && v > vertices) {
			throw new IndexOutOfBoundsException("getDistance(): vertex is not on graph");
		}
		return finishTime.get(v);
	}

	/**
	 * Returns the LinkedList stored at index v
	 * 
	 * @param v a vertex in the graph
	 * @return the adjacency LinkedList a v
	 * @precondition 0 < v <= vertices
	 * @throws IndexOutOfBoundsException when v is out of bounds
	 */
	public LinkedList<Integer> getAdjacencyList(Integer v) throws IndexOutOfBoundsException {
		if (v < 0 && v > vertices) {
			throw new IndexOutOfBoundsException("getDistance(): vertex is not on graph");
		}
		return adj.get(v);
	}

	/*** Manipulation Procedures ***/

	/**
	 * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into the
	 * list at index u)
	 *  @precondition, 0 < u, v <= vertices
	 * 
	 * @throws IndexOutOfBounds exception when u or v is out of bounds
	 */
	public void addDirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
		if (!(u > 0 && u <= vertices && v > 0 && v <= vertices)) {
		//if(v.compareTo(v))
			throw new IndexOutOfBoundsException("addDirectedEdge(): vertex is not on graph ");
		}
		
		adj.get(u).addLast(v);
		edges++;
	}

	/**
	 * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into the
	 * list at index u) and inserts u into the adjacent vertex list of v 
	 * @precondition, 0 < u, v <= vertices
	 * 
	 * @throws IndexOutOfBoundsException when u or v is out of bounds
	 */
	public void addUndirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
		if (!(u > 0 && u <= vertices && v > 0 && v <= vertices))  {
			throw new IndexOutOfBoundsException("addUndirectedEdge(): vertex is not on graph ");
		}
		adj.get(u).addLast(v);
		adj.get(v).addLast(u);
		edges++;
	}

	/*** Additional Operations ***/

	/**
	 * Creates a String representation of the Graph Prints the adjacency list of
	 * each vertex in the graph, vertex: <space separated list of adjacent vertices>
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 1; i <= vertices; i++) {
			result += i + ": " + adj.get(i).toString();
		}
		return result;
	}

	/**
	 * Performs breath first search on this Graph give a source vertex
	 * 
	 * @param source the starting vertex
	 * @precondition source is a vertex in the graph
	 * @throws IndexOutOfBoundsException when the source vertex is out of bounds of
	 *                                   the graph
	 */

	public void BFS(Integer source) throws IndexOutOfBoundsException {
		if (source <= 0 || source > vertices) {
			throw new IndexOutOfBoundsException("BFS(): vertex is outside the bounds of the graph");
		}
		for (int i = 1; i <= vertices; i++) {
			color.set(i, 'W');
			distance.set(i, -1);
			parent.set(i, null);
		}
		
		color.set(source, 'G');
		distance.set(source, 0);
		LinkedList<Integer> Q = new LinkedList<Integer>();
		
		Q.addLast(source);

		while (!Q.isEmpty()) {
			int x = Q.getFirst();
			Q.removeFirst();

			adj.get(x).positionIterator();
			for (int i = 1; i < adj.get(x).getLength(); i++) {
				int y = adj.get(x).getIterator();
				if (color.get(y) == 'W') {
					color.set(y, 'G');
					distance.set(y, getDistance(x) + 1);
					parent.set(y, x);
					Q.addLast(y);
				
				}
				adj.get(x).advanceIterator();
			}
			//System.out.println(distance);
			color.set(x, 'B');
		}
	}

	/**
	 * Performs depth first search on this Graph in order of vertex lists
	 */
	public void DFS() {
		/*for (int i = 1; i <= vertices; i++) {
			color.add(i, 'w');
			parent.add(i, null);
			discoverTime.add(i, -1);
			finishTime.add(i, -1);
		}*/
		time = 0;
		for (int i = 1; i <= vertices; i++) {
			if (color.get(i) == 'W') {
				visit(i);
			}
		}
	}

	/**
	 * Private recursive helper method for DFS
	 * 
	 * @param vertex the vertex to visit
	 */
	private void visit(int vertex) {
		color.add(vertex, 'G');
		time++;
		discoverTime.set(vertex,time);
		
		
		for (int i = 1; i < adj.size(); i++) {
			if (color.get(i) == 'W') {
				parent.set(i, vertex);
				visit(i);
			}
		}
		color.set(vertex, 'B');
		time++;
		finishTime.set(vertex,time);
		
		
	}

}