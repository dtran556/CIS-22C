
/**
 * Queue class - singly-linked list version
 * @author  Kim Bui
 * CIS 22C, Final Project
 */

import java.util.NoSuchElementException;

public class Queue<T> {
	private class Node {
		private T data;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node front;
	private Node end;
	private int size;

	/**
	 * Default constructor for the Queue class
	 */
	public Queue() {
		this.front = null;
		this.end = null;
		this.size = 0;
	}

	/**
	 * Converts an array into a Queue
	 *
	 * @param array the array to copy into the Queue
	 */
	public Queue(T[] array) {
		if (array == null) {
			return;
		} else if (array.length == 0) {
			this.front = null;
			this.end = null;
			this.size = 0;
		} else {
			for (int i = 0; i < array.length; i++) {
				this.enqueue(array[i]);
			}
		}
	}

	/**
	 * Copy constructor for the Queue class 
	 * Makes a deep copy of the parameter
	 *
	 * @param aQueue another Queue to copy
	 */
	public Queue(Queue<T> original) {
		if (original == null) {
			return;
		} else if (original.size == 0) {
			this.front = null;
			this.end = null;
			this.size = 0;
		} else {
			Node temp = original.front;
			while (temp != null) {
				this.enqueue(temp.data);
				temp = temp.next;
			}
		}
	}

	/**** ACCESSORS ****/

	/**
	 * Returns the value stored at the front of the Queue
	 *
	 * @return the value at the front of the queue
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getFront() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException("getFront(): Queue is empty. Cannot get data!");
		}
		return front.data;
	}

	/**
	 * Returns the size of the Queue
	 *
	 * @return the size from 0 to n
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Determines whether a Queue is empty
	 *
	 * @return whether the Queue is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**** MUTATORS ****/

	/**
	 * Inserts a new value at the end of the Queue
	 *
	 * @param data the new data to insert
	 * @postcondition a new node at the end of the Queue
	 */
	public void enqueue(T data) {
		Node newNode = new Node(data);
		if (isEmpty()) {
			front = end = newNode;
		} else {
			end.next = newNode;
			end = newNode;
		}
		size++;
	}

	/**
	 * Removes the front element in the Queue
	 *
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 * @postcondition the front element has been removed
	 */
	public void dequeue() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("dequeue(): Queue is empty. Cannot remove!");
		} else if (size == 1) {
			front = end = null;
		} else {
			front = front.next;
		}
		size--;
	}

	/**** ADDITONAL OPERATIONS ****/

	/**
	 * Returns the values stored in the Queue as a String, separated by a blank
	 * space with a new line character at the end
	 *
	 * @return a String of Queue values
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		if (isEmpty()) {
			 return "\n".toString();
		}
		Node temp = front;
		while (temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
			if (temp == null) {
				result.append("\n");
			}
		}
		return result.toString();
	}

	/**
	 * Determines whether two Queues contain the same values in the same order
	 *
	 * @param o the Object to compare to this
	 * @return whether o and this are equal
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (!(o instanceof Queue)) {
			return false;
		} else {
			Queue<T> Q = (Queue<T>) o;
			if (this.size != Q.size) {
				return false;
			} else {
				Node temp1 = this.front;
				Node temp2 = Q.front;
				while (temp1 != null) {
					if (!temp1.data.equals(temp2.data)) {
						return false;
					}
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				return true;
			}
		}
	}

	/** RECURSIVE HELPER METHODS */

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order
	 *
	 * @param n the current node
	 */
	private String reverseQueue(Node n) {
		StringBuilder result = new StringBuilder("");
		if (isEmpty() || n == null) {
			return result.toString();
		}
		 return result.append(reverseQueue(n.next)).append(n.data + " ").toString();
	}

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order
	 */
	public String reverseQueue() {
		return reverseQueue(front) + "\n";
	}
}
