
/**
 * Queue class - singly-linked list version
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

//import Stack.Node;

public class Queue<T> /*implements Q<T>*/ {
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
		front = null;
		end = null;
		size = 0;
	}

	/**
	 * Converts an array into a Queue
	 * 
	 * @param array the array to copy into the Queue
	 */
	public Queue(T[] array) {
		if (array == null) {
			size = 0;
			front = null;
			end = null;
		} else {
			for (int i = 0; i < array.length; i++) {
				this.enqueue(array[i]);
				// size++;
			}
		}
	}

	/**
	 * 
	 * Copy constructor for the Queue class Makes a deep copy of the parameter
	 * 
	 * @param aQueue another Queue to copy
	 */
	public Queue(Queue<T> aQueue) {
		if (aQueue == null) {
			return;
		}
		if (aQueue.size == 0) {
			front = null;
			end = null;
			size = 0;
		} else {
			Node temp = aQueue.front;
			while (temp != null) {
				enqueue(temp.data);
				temp = temp.next;
			}
		}
	}

	/**** ACCESSORS ****/

	// add methods here
	/**
	 * Returns the value stored at the front of the Queue
	 * 
	 * @return the value at the front of the queue
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getFront() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException("getFront(): no front because queue is empty");
		} else {
			return front.data;
		}
	}

	/**
	 * Returns the length of the Queue
	 * 
	 * @return the length from 0 to n
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

	// add methods here
	/**
	 * Inserts a new value at the end of the Queue
	 * 
	 * @param data the new data to insert
	 * @postcondition a new node at the end of the Queue
	 */
	public void enqueue(T data) {
		if (size == 0) {
			front = end = new Node(data);
			size++;
		} else {
			Node n = new Node(data);
			end.next = n;
			end = n;
			size++;
		}

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
			throw new NoSuchElementException("dequeue(): Queue is empty. " + "No element to remove");
		} else if (size == 1) {
			front = null;
			end = null;
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
		String result = "";
		Node temp = front;
		while (temp != null) {
			result += temp.data + " ";
			temp = temp.next;
		}
		result += "\n";
		return result;
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
		if (o == this) {
			return true;
		} else if (!(o instanceof Queue)) {
			return false;
		} else {
			Queue<T> n = (Queue<T>) o;
			if (this.size != n.size) {
				return false;
			} else {
				Node temp1 = this.front;
				Node temp2 = n.front;
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

	/** RECURSIVE HELPER METHODS */

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order
	 * 
	 * @param n the current node
	 */
	private String reverseQueue(Node n) {
		if (n == null) {
			return "";
		} else {
			return reverseQueue(n.next) + n.data.toString() + " ";
		}
	}

	/**
	 * Recursively (no loops) creates a String where the data is in reverse order by
	 * calling the private helper method
	 */

	public String reverseQueue() {
		return reverseQueue(front) + "\n";
	}
}