import java.util.LinkedList;
import java.util.Queue;

/**
 * BinaryTree - An example of a Binary Tree built using Node objects. My main
 * task was to implement the method bodies for:
 * 
 * protected void walkTree(Node n, StringBuffer sb) protected Person find(String
 * name, Node n)
 * 
 */
public class BinaryTree {

	private Node root; // The root node of the tree
	private int size; // A count of the nodes in the tree

	public BinaryTree(Student[] sdtList) {
		root = null;
		size = 0;

		for (Student s : sdtList) {
			addNode(s);
		}
	}

	public BinaryTree() {
		root = null;
		size = 0;
	}

	/**
	 * Returns the number of nodes in the tree.
	 * 
	 * @return Number of nodes in the tree.
	 */
	public int size() {
		return size;
	}

	/**
	 * Empties the tree
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Determines if the tree is empty or not.
	 * 
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() {
		return (root == null);
	}

	/**
	 * Adds a Node containing a reference to the object to the tree.
	 * 
	 * @param a The content of the node that will be added
	 */
	public void addNode(Student a) {
		Node node = new Node(a);

		// If tree is empty, make our new node the root and leave
		if (root == null) {
			root = node;
			size = 1;
		}
		// If it is not, put our new node beneath the root
		else {
			// Start the recursive calls to add the descendants of node 'p'
			addNode(node, root);
			size = size + 1;
		}
		// In both cases, adjust size accordingly

	}

	/**
	 * Internal recursive method to add a node to the tree.
	 * 
	 * @param c The content to place in the tree
	 * @param n The current node to consider, will not be null
	 */
	private void addNode(Node c, Node n) {
		// Is our new node content less than our current node content?
		// If it is, we add it to the left side of the tree so that when
		// we walk it with an in-order traversal, the tree content will
		// come out in alphabetic order.
		if (c.compareTo(n) < 0) {
			if (n.hasLeft())
				addNode(c, n.left());
			else {
				// The current left node is null so we can
				// attach our new node to the left of the current node
				n.setLeft(c);
				return;
			}

		}
		// Conversely add it to the right if it is greater.
		else {
			if (n.hasRight())
				addNode(c, n.right());
			else {
				// The current right node is null so we can
				// attach our new node to the right of the current node
				n.setRight(c);
				return;
			}
		}
	}

	/**
	 * Public call to start the tree walk using the root of the tree.
	 * 
	 * @return A reference to a comma separated String containing the trees
	 *         contents.
	 */
	public String walkTree() {
		StringBuffer buff = new StringBuffer();
		Queue<Node> nextUp = new LinkedList<>();
		if (root == null)
			return "Empty Tree!";
		else {
			nextUp.add(root);
			walkTree(root, buff, nextUp);
			buff.setLength(buff.length() - 1);
			return buff.toString();
		}
	}

	/**
	 * Protected method used to walk over the tree.
	 * 
	 * @param n  The current node
	 * @param sb The StringBuffer
	 * @param nu The Queue
	 */
	protected void walkTree(Node n, StringBuffer sb, Queue<Node> nu) {
		// Add to StringBuffer
		sb.append(n.getNodeObjectName() + ",");
		// Add child nodes of current node to the queue
		if (n.hasLeft()) {
			nu.add(n.left());
		}
		if (n.hasRight()) {
			nu.add(n.right());
		}
		// Dequeue the node we worked on
		nu.remove();
		// If the queue contains non-walked nodes, walk to the next node
		if (!nu.isEmpty()) {
			walkTree(nu.element(), sb, nu);
		}
	}

	/**
	 * Find the Node by 'name'
	 * 
	 * @param name The name to search for
	 * @return A reference to the Node that was found or null if no Node found
	 */
	public Student find(String name) {
		return find(name, root);
	}

	/**
	 * Internal protected method used to search the tree from node 'n' looking for a
	 * Node with the given 'name'. You should provide the correct recursive
	 * implementation for this method. Some helpful lines of code have been
	 * included...
	 * 
	 * @param name The name to search for
	 * @param n    The current node in the tree to search from
	 * @return A reference to the node that was found or null if not found
	 */
	protected Student find(String name, Node n) {
		int order = name.compareTo(n.getNodeObjectName());
		// if found students, return him
		if (order == 0) {
			return n.getNodeObject();
		}
		// if student is higher, look to the left
		else if (order < 0 && n.hasLeft()) {
			return find(name, n.left());
		}
		// if student is lower, look to the right
		else if (order > 0 && n.hasRight()) {
			return find(name, n.right());
		}
		return null;
	}

	public void printTree() {
		printTree(root, 0);
	}

	/**
	 * Internal method used to make a recursive reverse order walk from node 'n'.
	 * This method is useful to see the current structure of the tree (turned on its
	 * side)
	 * 
	 * @param n     The node to start the reverse order walk from
	 * @param depth The current depth of the node in the tree
	 */
	private void printTree(Node n, int depth) {
		if (n.hasRight())
			printTree(n.right(), depth + 1);

		// Show depth of current code by indenting to the right
		for (int d = 0; d < depth; d++)
			System.out.print("  ");

		System.out.println(n.getNodeObjectName());

		if (n.hasLeft())
			printTree(n.left(), depth + 1);
	}

}
