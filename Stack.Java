
/**
 * Stack.java
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> {
	private class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node top;
	private int size;
	private Object data;

	/**** CONSTRUCTORS ****/

	/**
	 * Default constructor for the Stack class
	 * 
	 * @postcondition a new Stack object with all fields assigned default values
	 */
	public Stack() {
		this.top = null;
		this.size = 0;
	}

	/**
	 * Constructor for the Stack class Converts an array into a Stack
	 * 
	 * @param an array of elements to copy into the Stack
	 */
	public Stack(T[] array) {
		if (array == null) {
			size = 0;
			top = null;
		} else {
			for (int i = 0; i < array.length; i++) {
				this.push(array[i]);
			}
		}
	}

	/**
	 * Copy constructor for the Stack class
	 * 
	 * @param original the Stack to copy
	 * @postcondition a new Stack object which is an identical, but distinct, copy
	 *                of original REQUIRED: THIS METHOD MUST BE IMPLEMENTED IN O(N)
	 *                TIME
	 */
	public Stack(Stack<T> original) {
		if (original.equals(null)) {
			return;
		}
		if (original.size == 0) {
			top = null;
			size = 0;
		} else {
			Stack<T> stack1 = new Stack<T>();
			Stack<T> stack2 = new Stack<T>();
			int x = original.getSize();
			// copy part
			Node temp = original.top;
			for (int i = 0; i < x; i++) {
				stack1.push(temp.data);
				temp = temp.next;
			}
			for (int i = 0; i < x; i++) {
				stack2.push(stack1.peek());
				stack1.pop();
			}
			this.top = stack2.top;
			size = x;
		}

	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored at the top of the Stack
	 * 
	 * @return the value at the top of the Stack
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the the Stack is empty
	 */
	public T peek() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("peek(): Stack is empty, cannot peek");
		} else {
			return top.data;
		}
	}

	/**
	 * Returns the length of the Stack
	 * 
	 * @return the length from 0 to n
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Determines whether a Stack is empty
	 * 
	 * @return whether the Stack is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**** MUTATORS ****/

	/**
	 * Inserts a new value at the top of the Stack
	 * 
	 * @param data the new data to insert
	 */
	public void push(T data) {
		Node n = new Node(data);
		n.next = top;
		top = n;
		size++;
	}

	/**
	 * Removes the top element of the Stack
	 * 
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the Stack is empty
	 */
	public void pop() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("pop(): Stack is empty cannot remove.");
		} else {
			top = top.next;
		}
		size--;
	}

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Stack as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Stack values
	 */
	public String toString() {
		String result = "";
		Node temp = top;
		while (temp != null) {
			result = result + temp.data + " ";
			temp = temp.next;
		}
		result += "\n";
		return result;
	}

	/**
	 * Determines whether two Stacks contain the same data in the same order
	 * 
	 * @param o the Object to compare to this Stack
	 * @return whether o and this are equal Stacks
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Stack)) {
			return false;
		} else {
			Stack<T> n = (Stack<T>) o;
			if (this.size != n.size) {
				
				return false;
			} else {
				Node temp1 = this.top;
				Node temp2 = n.top;
				while (temp1 != null) {
					if (!temp1.data.equals(temp2.data)) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
			}
			return true;
		}
	}

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order by
	 * calling the private helper method
	 */
	public String reverseStack() {
		return reverseStack(top) + "\n";
	}

	/**
	 * Determines whether the Stack is sorted from smallest to largest by calling
	 * its recursive helper
	 * 
	 * @return whether the Stack is sorted
	 */
	public boolean isSorted() {
		if (size == 0) {
			return true;
		}
		return isSorted(top);
	}

	/**
	 * Uses the iterative linear search algorithm to locate an element
	 * 
	 * @param element the value to search for
	 * @return whether the element is present in the Queue Note that in the case
	 *         length==0 the element is considered not found
	 */
	public boolean linearSearch(T element) {
		Node n = top;
		if (isEmpty()) {
			return false;
		} else {
			while (n != null) {
				if (n.data.equals(element)) {
					return true;
				}
				n = n.next;
			}
			return false;
		}
	}

	/**
	 * Uses the recursive binarySearch algorithm to determine if a specific element
	 * is in the Stack by calling the private helper method binarySearch
	 * 
	 * @param value the value to search for
	 * @return whether the element is present in the Queue
	 * @precondition isSorted()
	 * @throws IllegalStateException when the precondition is violated.
	 */
	public boolean binarySearch(T value) throws IllegalStateException {
		if (!isSorted()) {
			throw new IllegalStateException("binarySearch(): stack is not sorted");
		}

		if (binarySearch(0, size - 1, value) == -1) {
			return false;
		} else {
			return true;
		}
	}

	/** RECURSIVE HELPER METHODS */

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order
	 * 
	 * @param n the current node
	 */
	private String reverseStack(Node n) {
		// base case
		if (n == null) {
			return "";
		}
		// recursive step
		else {
			return reverseStack(n.next) + "" + n.data.toString() + " ";
		}

	}

	/**
	 * Helper method to isSorted Recursively determines whether data is sorted from
	 * smallest to largest
	 * 
	 * @param node the current node
	 * @return whether the data is sorted in ascending order
	 */
	private boolean isSorted(Node node) {
		if (node.next == null) {
			return true;
		}

		else if (node.data.compareTo(node.next.data) > 0) {
			return isSorted(node.next);
		} else {
			return false;
		}
	}

	/**
	 * Searches for the specified value in by implementing the recursive
	 * binarySearch algorithm
	 * 
	 * 
	 * @param low   the lowest bounds of the search
	 * @param high  the highest bounds of the search
	 * @param value the value to search for
	 * @return the location at which value is located from 1 to length or -1 to
	 *         indicate not found
	 */
	private int binarySearch(int low, int high, T value) {
		Node temp = top;
		if (high < low) {
			return -1;
		}
		for (int i = 0; i < high; i++) {
			temp = temp.next;
		}
		if (temp.data.equals(value)) {
			return high;
		}
		temp = top;
		for (int i = 0; i < low; i++) {
			temp = temp.next;
		}

		if (temp.data.equals(value)) {
			return low;
		}
		temp = top;
		int mid = (high + low) / 2;
		for (int i = 0; i < mid; i++) {
			temp = temp.next;
		}
		if (temp.data.equals(value)) {
			return mid;
		} else if (value.compareTo(temp.data) > 0) {
			// right side of mid
			return binarySearch(mid + 1, high, value);
		} else // if(value.compareTo(temp.data) < 0)
		{
			// left side of mid
			return binarySearch(low, mid - 1, value);
		}

	}

}
