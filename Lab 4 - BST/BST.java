
/**
 * BST.java
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C Lab 4
 */

//import java.util.Arrays;
import java.util.NoSuchElementException;

public class BST<T extends Comparable<T>> {
	private class Node {
		private T data;
		private Node left;
		private Node right;

		public Node(T data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	private Node root;

	/*** CONSTRUCTORS ***/
	/**
	 * Default constructor for BST sets root to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Copy constructor for BST
	 * 
	 * @param bst the BST to make a copy of
	 */
	public BST(BST<T> bst) {
		if(bst != null)
		{
			copyHelper(bst.root);
		}
	}

	/**
	 * Helper method for copy constructor
	 * 
	 * @param node the node containing data to copy
	 */
	private void copyHelper(Node node) {
		if (node == null) {
			return;
		} else {
			insert(node.data);
			copyHelper(node.left);
			copyHelper(node.right);
		}
	}

	/**
	 * Creates a BST of minimal height given an array of values
	 * 
	 * 
	 * @param array the list of values to insert
	 * @precondition array must be sorted in ascending order
	 * @throws IllegalArgumentException when the array is unsorted
	 */
	public BST(T[] array) throws IllegalArgumentException {
		if(array == null)
		{
			return;
		}
		if (!isSorted(array)) {
			throw new IllegalArgumentException("BST array is not sorted");
		}
		else 
		{
			arrayInsert(array, 0, array.length);
		}
	}
	/**
	 * Helper method for BST array. 
	 * Checks if the given array is sorted or not.
	 * 
	 * @param array values
	 * @return true or false depending on if sorted or not
	 */
	private boolean isSorted(T[] array) 
	{
		if(array.length == 1)
		{
			return true;
		}
		for (int i = 0; i < array.length - 1; i++) 
		{
			if (array[i].compareTo(array[i + 1]) > 0) 
			{
				return false;	
			}
		}
		return true;
	}
	/**
	 * Helper method for BST array constructor.
	 * Takes the given array and sorts it into BST using binary search
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * 
	 * 
	 */
	private void arrayInsert(T[] array, int low, int high) {
		if (low - high == 0) {
		}
		else
		{
			int index;
			if (low == 0)
			{
				index = high/2;
			}
			else
			{
				index = (high-low)/2 + low;
			}
			insert(array[index]);
			arrayInsert(array, low, index);// left side
			arrayInsert(array, index+1, high); // right side
		}
	}



	/*** ACCESSORS ***/

	/**
	 * Returns the data stored in the root
	 * 
	 * @precondition !isEmpty()
	 * @return the data stored in the root
	 * @throws NoSuchElementException when precondition is violated
	 */
	public T getRoot() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("getRoot() Tree is empty");
		} else {
			return root.data;
		}
	}

	/**
	 * Determines whether the tree is empty
	 * 
	 * @return whether the tree is empty
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the current size of the tree (number of nodes)
	 * 
	 * @return the size of the tree
	 */
	public int getSize() {
		return getSize(root);
	}

	/**
	 * Helper method for the getSize method
	 * 
	 * @param node the current node to count
	 * @return the size of the tree
	 */
	private int getSize(Node node) {
		if (node == null) {
			return 0;
		}
		// else return the sizez of the left and right node
		else {
			return 1 + getSize(node.left) + getSize(node.right);
		}
	}

	/**
	 * Returns the height of tree by counting edges.
	 * 
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root);
	}

	/**
	 * Helper method for getHeight method
	 * 
	 * @param node the current node whose height to count
	 * @return the height of the tree
	 */
	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}
		int left = getHeight(node.left);
		int right = getHeight(node.right);

		if (left > right) {
			return left + 1;
		} else {
			return right + 1;
		}
	}

	/**
	 * Returns the smallest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the smallest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMin() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMin(): Tree is empty");
		} else {
			return findMin(root);
		}
	}

	/**
	 * Helper method to findMin method
	 * 
	 * @param node the current node to check if it is the smallest
	 * @return the smallest value in the tree
	 */
	private T findMin(Node node) {
		if (node.left != null) {
			return findMin(node.left);
		} else {
			return node.data;
		}
	}

	/**
	 * Returns the largest value in the tree
	 * 
	 * @precondition !isEmpty()
	 * @return the largest value in the tree
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T findMax() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("findMax(): Tree is empty");
		} else {
			return findMax(root);
		}
	}

	/**
	 * Helper method to findMax method
	 * 
	 * @param node the current node to check if it is the largest
	 * @return the largest value in the tree
	 */
	private T findMax(Node node) {
		if (node.right != null) {
			// we know that bst goes as LEFT = small and right = biggest
			// keep going through the right side until we find max
			return findMax(node.right);
		} else {
			return node.data;
		}
	}

	/*** MUTATORS ***/

	/**
	 * Inserts a new node in the tree
	 * 
	 * @param data the data to insert
	 */
	public void insert(T data) {
		if (root == null) {
			root = new Node(data);
		} 
		else {
			insert(data, root);
		}
	}

	/**
	 * Helper method to insert Inserts a new value in the tree
	 * 
	 * @param data the data to insert
	 * @param node the current node in the search for the correct location in which
	 *             to insert
	 */
	private void insert(T data, Node node) {
		if (node == null)
		{
			return;
		}
		if (data.compareTo(node.data) <=0 )
		{
			if (node.left == null)
			{
				node.left = new Node(data);
			}
			else
			{
				insert(data, node.left);
			}
		}
		else
		{
			if (node.right == null)
			{
				node.right = new Node(data);
			}
			else
			{
				insert(data, node.right);
			}
		}
	}

	/**
	 * Removes a value from the BST
	 * 
	 * @param data the value to remove
	 * @precondition !isEmpty()
	 * @throws IllegalStateException when BST is empty
	 */
	public void remove(T data) throws IllegalStateException {
		if (isEmpty()) {
			throw new IllegalStateException("remove(): Tree is empty");
		} 
		else if (search(data) == false) {
			return;
		}
		else {
			root = remove(data, root);
		}
	}

	/**
	 * Helper method to the remove method
	 * 
	 * @param data the data to remove
	 * @param node the current node
	 * @return an updated reference variable
	 */
	private Node remove(T data, Node node) {
		if (node == null) { // 1. If node is null
			return node;
		} 
		else if (data.compareTo(node.data) < 0) // 2.
		{
			node.left = remove(data, node.left);
			
		} 
		else if (data.compareTo(node.data) > 0) // 3.
		{
			node.right = remove(data, node.right);
		} 
		else // 4.
		{
			if (node.left == null && node.right == null) 
			{
				node = null;
			} 
			else if (node.left != null && node.right == null) 
			{
				node = node.left;
			} 
			else if (node.left == null && node.right != null) 
			{
				node = node.right;
			} 
			else {
				T minVal = findMin(node.right);
				node.data = minVal;
				node.right = remove(minVal, node.right);
			}			
		}
		return node;

	}

	/*** ADDITIONAL OPERATIONS ***/

	/**
	 * Searches for a specified value in the tree
	 * 
	 * @param data the value to search for
	 * @return whether the value is stored in the tree
	 */
	public boolean search(T data) {
		if (root == null) {
			return false;
		} else {
			return search(data, root);
		}
	}

	/**
	 * Helper method for the search method
	 * 
	 * @param data the data to search for
	 * @param node the current node to check
	 * @return whether the data is stored in the tree
	 */
	private boolean search(T data, Node node) {
		if (data.equals(node.data)) 
		{
			return true;
		}
		else if (data.compareTo(node.data) < 0)
		{
			if (node.left == null) 
			{
				return false;
			} else {
				return search(data, node.left);
			}
		} else 
		{
			if (node.right == null) 
			{
				return false;
			} else { 
				return search(data, node.right);
			}
		}
	}

	/**
	 * Returns a String containing the data in pre order
	 * 
	 * @return a String of data in pre order
	 */
	public String preOrderString() {
		StringBuilder preOrder = new StringBuilder();
		preOrderString(root, preOrder);
		return preOrder.append("\n").toString();
	}

	/**
	 * Helper method to preOrderString Inserts the data in pre order into a String
	 * 
	 * @param node     the current Node
	 * @param preOrder a String containing the data
	 */
	private void preOrderString(Node node, StringBuilder preOrder) {
		if (node == null) {
			return;
		} else {
			preOrder.append(node.data + " ");
			preOrderString(node.left, preOrder);
			preOrderString(node.right, preOrder);
		}

	}

	/**
	 * Returns a String containing the data in order
	 * 
	 * @return a String of data in order
	 */
	public String inOrderString() {
		StringBuilder inOrder = new StringBuilder();
		inOrderString(root, inOrder);
		return inOrder.append("\n").toString();
	}

	/**
	 * Helper method to inOrderString Inserts the data in order into a String
	 * 
	 * @param node    the current Node
	 * @param inOrder a String containing the data
	 */
	private void inOrderString(Node node, StringBuilder inOrder) {
		if (node == null) {
			return;
		} else {
			inOrderString(node.left, inOrder);
			inOrder.append(node.data + " ");
			inOrderString(node.right, inOrder);
		}
	}

	/**
	 * Returns a String containing the data in post order
	 * 
	 * @return a String of data in post order
	 */
	public String postOrderString() {
		StringBuilder postOrder = new StringBuilder();
		postOrderString(root, postOrder);
		postOrder.append("\n");
		return postOrder.toString();
		
	}

	/**
	 * Helper method to postOrderString Inserts the data in post order into a String
	 * 
	 * @param node      the current Node
	 * @param postOrder a String containing the data
	 */
	private void postOrderString(Node node, StringBuilder postOrder) {
		if (node == null) {
			return;
		} else {
			postOrderString(node.left, postOrder);
			postOrderString(node.right, postOrder);
			postOrder.append(node.data + " ");

		}

	}

	/**
	 * Creates a String that is a depth order traversal of the data in the tree
	 * 
	 * @return the level order traversal as a String
	 */
	public String depthOrderString() {
		StringBuilder depthOrder = new StringBuilder();
		Queue<BST<T>.Node> q = new Queue<BST<T>.Node>();
        q.enqueue(root);
        depthOrderString(q, depthOrder);
        depthOrder.append("\n");
        return depthOrder.toString();
	}

	/**
	 * Helper method to depthOrderString Inserts the data in depth order into a
	 * String starting from 0
	 * 
	 * @param q          the Queue in which to store the data
	 * @param depthOrder a StringBuilder containing the data
	 */
	private void depthOrderString(Queue<Node> q, StringBuilder depthOrder) {
        if (q.getFront() == null)
        {
            return;
        }
        depthOrder.append(q.getFront().data + " ");
        q.enqueue(q.getFront().left);
        q.enqueue(q.getFront().right);
        q.dequeue();
        depthOrderString(q, depthOrder);
        q.dequeue();
        depthOrderString(q, depthOrder);


        return;
    }

}