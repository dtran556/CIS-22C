import java.util.Comparator;
import java.util.NoSuchElementException;

public class BST<T> {
     class Node {
        public T data;
        public Node left;
        public Node right;
        
        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    
    private Node root;
    
    /***CONSTRUCTORS***/
    
    /**
     * Default constructor for BST
     * sets root to null
     */
    public BST() {
    	
      root = null;
      
    }
   
    /**
     * Copy constructor for BST
     * @param bst the BST to make
     * a copy of 
     */
    public BST(BST<T> bst, Comparator<T> comparator) {
        if (bst == null || bst.isEmpty()) {
            return;
        }

        copyHelper(bst.root, comparator);
    }
    
    /**
     * Helper method for copy constructor
     * @param node the node containing
     * data to copy
     */
    private void copyHelper(Node node, Comparator<T> comparator) {
        if (node == null) {
            return;
        }
        insert(node.data, comparator);
        copyHelper(node.left, comparator);
        copyHelper(node.right, comparator);
    }
    
    public BST(T[] array, Comparator<T> comparator) throws IllegalArgumentException {
        if (array == null) {
            return;
        }
        if (!(isSortArray(array, array.length - 1, comparator))) {
            throw new IllegalArgumentException("BST() array is unsorted");
        }

        copyHelperArray(array, 0, array.length - 1, comparator);
    }

    private boolean isSortArray(T[] array, int index, Comparator<T> comparator) {
        if (index == 1 || index == 0) {
            return true;
        }
        if (comparator.compare(array[index - 2],array[index]) > 1) {
            return false;
        }

        return isSortArray(array, index - 1, comparator);
    }

    private void copyHelperArray(T[] array, int start, int end, Comparator<T> comparator) {

        if (start > end) {
            return;
        }
        int middle = (start + end) / 2;
        insert(array[middle], comparator);
        copyHelperArray(array, start, middle - 1, comparator);
        copyHelperArray(array, middle + 1, end, comparator);
    }
    
    /***ACCESSORS***/
    
    /**
     * Returns the data stored in the root
     * @precondition !isEmpty()
     * @return the data stored in the root
     * @throws NoSuchElementException when
     * precondition is violated
     */
    public T getRoot() throws NoSuchElementException {
    	if (isEmpty()) {
			throw new NoSuchElementException("getRoot()");
		}
        return root.data;
    }
    
    /**
     * Determines whether the tree is empty
     * @return whether the tree is empty
     */
    public boolean isEmpty() {
       return root == null;
    }
    
    /**
     * Returns the current size of the 
     * tree (number of nodes)
     * @return the size of the tree
     */
    public int getSize() {
        return getSize(root);
    }
    
    /**
     * Helper method for the getSize method
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
    	if (node == null) {
			return 0;
		}

       return getSize(node.left) +  getSize(node.right) + 1;
    }
    
    /**
     * Returns the height of tree by
     * counting edges.
     * @return the height of the tree
     */
    public int getHeight() {
    	
        return getHeight(root);
    }
    
    /**
     * Helper method for getHeight method
     * @param node the current
     * node whose height to count
     * @return the height of the tree
     */
    private int getHeight(Node node) {
    	if (node == null) {
			return -1;
		}
    	int leftHeight = getHeight(node.left) + 1;
    	int rightHeight = getHeight(node.right) + 1;
    	
    	if (leftHeight < rightHeight) 
			return rightHeight;
    	else 
    		return leftHeight;
		

    }
    
    /**
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the smallest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMin() throws NoSuchElementException{
    	if (root == null) {
			throw new NoSuchElementException("findMin()");
		}
        return findMin(root);
    }
    
    /**
     * Helper method to findMin method
     * @param node the current node to check
     * if it is the smallest
     * @return the smallest value in the tree
     */
    private T findMin(Node node) {
    	if (node.left == null) {
			return node.data;
		}
    	return findMin(node.left);
    }
    
    /**
     * Returns the largest value in the tree
     * @precondition !isEmpty()
     * @return the largest value in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T findMax() throws NoSuchElementException{
    	if (root == null) {
			throw new NoSuchElementException("findMin()");
		}
        return findMax(root);
    }
    
    /**
     * Helper method to findMax method
     * @param node the current node to check
     * if it is the largest
     * @return the largest value in the tree
     */
    private T findMax(Node node) {
    	if (node.right == null) {
			return node.data;
		}
    	return findMax(node.right);
    }
    
    /***MUTATORS***/
    
    /**
     * Inserts a new node in the tree
     * @param data the data to insert
     */
    public void insert(T data, Comparator<T> comparator) {
    	insert(data, root, comparator);
    }
    
    /**
     * Helper method to insert
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insert(T data, Node node, Comparator<T> comparator) {
    	if(isEmpty()) {
    		root = new Node(data);
    	}else {
        	if (comparator.compare(node.data, data) < 0) {
    			if (node.right == null) {
					node.right = new Node(data);
				}
    			else {
    				insert(data,node.right, comparator);
				}
    		}else {
    			if (node.left == null) {
					node.left = new Node(data);
				}
    			else {
    				insert(data,node.left, comparator);
				}
			}
		}

    }
    
    /**
     * Removes a value from the BST
     * @param data the value to remove
     * @precondition !isEmpty()
     * @throws IllegalStateException when BST is empty
     */
    public void remove(T data, Comparator<T> comparator) throws IllegalStateException {
        if (root == null) {
            throw new IllegalStateException("The tree is empty");
        } else {
            root = remove(data, root, comparator);
        }
    }
    
    /**
     * Helper method to the remove method
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable
     */
    private Node remove(T data, Node node, Comparator<T> comparator) {
    	if (node == null) {
			return node;
		}else {
			
	    	if(comparator.compare(node.data, data) < 0) {
	    		node.right = remove(data, node.right, comparator);
	    	}else if(comparator.compare(node.data, data) > 0) {
	    		node.left = remove(data, node.left, comparator);
	    	}else {
	    		
				if (node.left == null) {
					return node.right;
				}else if (node.right == null){
					return node.left;
				}
				else {
					node.data = findMin(node.right);
					node.right = remove(node.data, node.right, comparator);
				}
				
			}
			
		}

    	
        return node;
    }
    
        
    /***ADDITIONAL OPERATIONS***/
    
    /**
     * Searches for a specified value
     * in the tree
     * @param data the value to search for
     * @return whether the value is stored
     * in the tree
     */
    public T search(T data, Comparator<T> comparator) {
        if(root == null) {
            return null;
        } else {
            return search(data, root, comparator);
        }
        
    }
    
    /**
     * Helper method for the search method
     * @param data the data to search for
     * @param node the current node to check
     * @return whether the data is stored
     * in the tree
     */
    private T search(T data, Node node, Comparator<T> comparator) {
    	if(comparator.compare(node.data, data) == 0) {
    		return node.data; // return data of that node instead of true
    	}
    	else if (comparator.compare(node.data, data) < 0) {
    		if (node.right == null) {
				return null; // return null instead of false
			}
    		else {
    			return search(data,node.right, comparator);
    		}
    	}
    	else {
    		if (node.left == null) {
    			return null;// return null instead of false
			}
    		else {
    			return search(data,node.left, comparator);
			}
		}
	}
    
    
    /**
     * Determines whether a BST is balanced
     * using the definition given in the course
     * lesson notes
     * Note that we will consider an empty tree
     * to be trivially balanced
     * @return whether the BST is balanced
     */
    public boolean isBalanced() {
        if(root == null) {
            return true;
        }
        return isBalanced(root);
    }
    
    /**
     * Helper method for isBalanced
     * to determine if a BST is balanced
     * @param n a Node in the tree
     * @return whether the BST is balanced
     * at the level of the given Node
     */
    private boolean isBalanced(Node n) {
        if(n != null) {
            if(Math.abs(getHeight(n.left) - getHeight(n.right)) > 1) {
                return false;
            }
            return isBalanced(n.left) && isBalanced(n.right);
        }
        return true;
    }
    
    /**
     * Returns a String containing the data
     * in post order
     * @return a String of data in post order
     */
    public String preOrderString() {
    	
    	StringBuilder str = new StringBuilder();
    	preOrderString(root, str);
    	
        return str + "\n";
    }
    
    /**
     * Helper method to preOrderString
     * Inserts the data in pre order into a String
     * @param node the current Node
     * @param preOrder a String containing the data
     */
    private void preOrderString(Node node, StringBuilder preOrder) {
    	if (node == null) {
			return;
		}
    	preOrder.append(node.data + " ");
    	preOrderString(node.left, preOrder);
    	preOrderString(node.right, preOrder);
    }
    
    /**
     * Returns a String containing the data
     * in order
     * @return a String of data in order
     */
    public String inOrderString() {
        StringBuilder str = new StringBuilder();
        inOrderString(root, str);

        return str.append("\n").toString() ;
    }
    
    /**
     * Helper method to inOrderString
     * Inserts the data in order into a String
     * @param node the current Node
     * @param inOrder a String containing the data
     */
    private void inOrderString(Node node, StringBuilder inOrder) {
    	if (node == null) {
			return;
		}
    	
    	inOrderString(node.left, inOrder);
    	inOrder.append(node.data + " ");
    	inOrderString(node.right,inOrder);
    	
    }
    
    /**
     * Returns a String containing the data
     * in post order
     * @return a String of data in post order
     */
    public String postOrderString() {
        StringBuilder str = new StringBuilder();
        postOrderString(root, str);

        return str + "\n";
    }
    
    /**
     * Helper method to postOrderString
     * Inserts the data in post order into a String
     * @param node the current Node
     * @param postOrder a String containing the data
     */
    private void postOrderString(Node node, StringBuilder postOrder) {
    	if (node == null) {
			return;
		}
    	postOrderString(node.left, postOrder); 
    	postOrderString(node.right, postOrder);
    	postOrder.append(node.data + " ");
    }
    /**
     * Creates a String that is a depth order
     * traversal of the data in the tree
     * 
     * @return the level order traversal as a String
     */
    public String depthOrderString() {
        StringBuilder depthOrder = new StringBuilder();
        Queue<Node> q = new Queue<>();
        depthOrderString(q, depthOrder);
        return depthOrder + "\n";
    }

    /**
     * Helper method to depthOrderString
     * Inserts the data in depth order into a String starting from 0
     * 
     * @param q          the Queue in which to store the data
     * @param depthOrder a StringBuilder containing the data
     */
    private void depthOrderString(Queue<Node> q, StringBuilder depthOrder) {
    	
        q.enqueue(root);

        while (!(q.isEmpty()) && root != null) {

            depthOrder.append(q.getFront().data + " ");

            if (q.getFront().left != null) {
                q.enqueue(q.getFront().left);
            }
            if (q.getFront().right != null) {
                q.enqueue(q.getFront().right);
            }
            q.dequeue();
        }

    }

}