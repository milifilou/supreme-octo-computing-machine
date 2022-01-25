import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * University maintains an ArrayList called cohorts that stores the list of
 * classes of the university. My task was to correctly implement the method
 * bodies for:
 * 
 * protected ArrayList<Cohort> quickSort(ArrayList<Cohort> list, boolean
 * ascending, String attr) protected boolean checkForCycles(Graph rel, Module
 * node, Map<Module, Boolean> visited, Map<Module, Boolean> beingVisited)
 * 
 * The above methods are called via public methods of the same name which supply
 * the local modules object as a parameter. You can observe calls to these
 * public methods in the go methods of UniversityTest.java.
 */
public class University {
	private ArrayList<Cohort> cohorts;
	private Graph graph;

	public University() {
		cohorts = new ArrayList<Cohort>();
		graph = new Graph();
	}

	public void clear() {
		for (Cohort v : cohorts) {
			v.getBtree().clear();
		}
		cohorts.clear();
		graph = new Graph();
	}

	public void addClass(Cohort v) {
		cohorts.add(v);
		if (!v.getModule().getPrerequisites().isEmpty()) {
			for (Module m : v.getModule().getPrerequisites()) {
				graph.insertEdge(v.getModule(), m);
			}
		} else {
			graph.insertNode(v.getModule());
		}
	}

	public ArrayList<Cohort> getCohort() {
		return cohorts;
	}

	public Graph getGraph() {
		return graph;
	}

	public void describeModuleList() {
		for (Cohort v : cohorts) {
			System.out.println(v.toString());
			v.describeStudentTree();
		}
	}

	/**
	 * This method calls the tree walk method for a specific cohort
	 * 
	 * @param val The index of the cohort that you want to call the method for
	 * @return A String with the names of all Students in the tree
	 */
	public String walkTree(int val) {
		return cohorts.get(val).walkTree();
	}

	/**
	 * This method calls the find method for a specific cohort
	 * 
	 * @param val The index of the cohort that you want to call the method for
	 * @return A reference to the Student that was found or null if no Student found
	 */
	public Student find(int val, String name) {
		return cohorts.get(val).find(name);
	}

	/**
	 * This method calls the protected checkForCycle to find for cycles in the
	 * graph.
	 * 
	 * You should not modify this code.
	 */
	public boolean checkForCycles() {
		Map<Module, Boolean> visited = new HashMap<Module, Boolean>();
		Map<Module, Boolean> beingVisited = new HashMap<Module, Boolean>();
		for (Module m : graph.getVertices()) {
			if (checkForCycles(this.graph, m, visited, beingVisited)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method should find cycles in directed graphs.
	 *
	 * @param graph        The graph
	 * @param node         the current node
	 * @param visited      List of visited nodes
	 * @param beingVisited List of nodes being visited
	 * 
	 * @return true, if there is a cycle, false otherwise
	 */
	protected boolean checkForCycles(Graph graph, Module node, Map<Module, Boolean> visited,
			Map<Module, Boolean> beingVisited) {
		/*
		 * You may notice that I did not use the "Visited" Map at all. 
		 * I have tried and failed to find use for it. 
		 * For each starting node, we visit the nodes its adjacency list depth first 
		 * A node is 'beingVisited' if it is on our current path down 
		 * If a node we just reached has already been noted already on our path down, 
		 * it means we have taken a back edge 
		 * If there are back edges, this is not an acyclic graph
		 */
		if (beingVisited.containsKey(node) && beingVisited.get(node)) {
			return true;
		}
		beingVisited.put(node, true);
		for (Module preReq : graph.getAdj(node)) {
			if (checkForCycles(graph, preReq, visited, beingVisited)) {
				return true;
			}
		}
		beingVisited.put(node, false);
		return false;
	}

	/**
	 * This method should use quick sort approach to rearrange the references in the
	 * ArrayList 'modules' such that they are in order according to the attr
	 * (attribute) parameter. If asc is true, this should be ascending order, if asc
	 * is false, this should be descending order.
	 * 
	 * You should not modify this code.
	 * 
	 * @param asc  True if the list should be ascending order, false for descending
	 * @param attr Attribute (name or code) that will be use during the sorting
	 * @return The ArrayList 'modules' that has been sorted using insertion sort
	 */
	public ArrayList<Cohort> quickSort(boolean asc, String attr) {
		ArrayList<Cohort> sorted = new ArrayList<Cohort>(cohorts); // copy of the list
		return quickSort(sorted, 0, cohorts.size() - 1, asc, attr);
	}

	/**
	 * This method should use quick sort approach to rearrange the references in the
	 * ArrayList 'modules' such that they are in order according to the attr
	 * (attribute) parameter. If asc is true, this should be ascending order, if asc
	 * is false, this should be descending order.
	 * 
	 * @param list The arraylist to be sorted
	 * @param low  First index
	 * @param high Last index
	 * @param asc  True if the list should be ascending order, false for descending
	 * @param attr Attribute (name or code) that will be use during the sorting
	 * 
	 * @return The ArrayList 'modules' that has been sorted using insertion sort
	 */
	protected ArrayList<Cohort> quickSort(ArrayList<Cohort> list, int low, int high, boolean ascending, String attr) {
		// If the range has less than two elements,
		// return immediately as there is nothing to do.
		if (high - low > 0) {
			// pick a random pivot in the range (random since it performs well on sorted
			// lists)
			int pivotIndex = ThreadLocalRandom.current().nextInt(low, high + 1);
			swapCohort(list, pivotIndex, high);
			Cohort pivot = list.get(high);
			int i = low;
			// split based on sorting criterion
			if (attr.equals("name")) {
				String comparison = pivot.getModule().getName();
				// partition loop
				for (int j = low; j <= high; j++) {
					String jName = list.get(j).getModule().getName();
					// split based on sort direction
					if ((jName.compareTo(comparison) < 0) && ascending) {
						swapCohort(list, i, j);
						i++;
					} else if ((jName.compareTo(comparison) > 0) && !ascending) {
						swapCohort(list, i, j);
						i++;
					}
				}
			}
			// split based on sorting criterion
			else if (attr.equals("code")) {
				int comparison = pivot.getModule().getCode();
				// partition loop
				for (int j = low; j <= high; j++) {
					int jCode = list.get(j).getModule().getCode();
					// split based on sort direction
					if (ascending && (jCode < comparison)) {
						swapCohort(list, i, j);
						i++;
					} else if (!ascending && (jCode > comparison)) {
						swapCohort(list, i, j);
						i++;
					}
				}
			}
			// pivot placement and recursive step
			swapCohort(list, i, high);
			quickSort(list, low, i - 1, ascending, attr);
			quickSort(list, i + 1, high, ascending, attr);
		}
		return list;
	}

	/**
	 * This method swaps the location of two cohorts in an arrayList
	 * 
	 * @param list The list we are swapping in
	 * @param i    The index of a cohort to be swapped with j
	 * @param j    The index of a cohort to be swapped with i
	 */
	protected void swapCohort(ArrayList<Cohort> list, int i, int j) {
		Cohort temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

}
