
/**
 * Queue class - Array Version
 * @author Daniel Tran
 * @author Alex Benny
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Queue<T> implements Q<T> {
	private T[] queue;
	private int curr_size; // curr_size is end

	/**** CONSTRUCTORS ****/
	/**
	 * Default constructor for the Queue class with an initial length of 10 and no
	 * elements
	 */
	@SuppressWarnings("unchecked")
	public Queue() {
		queue = (T[]) new Object[10];
		curr_size = 0;
	}

	/**
	 * Converts an array into a Queue
	 * 
	 * @param array the array to copy into the array
	 */
	@SuppressWarnings("unchecked")
	public Queue(T[] array) {
		if (array == null) {
			queue = (T[]) new Object[10];
			curr_size = 0;
		} else {
			queue = (T[]) new Object[10];
			curr_size = 0;
			for (int i = 0; i < array.length; i++) {
				this.enqueue(array[i]);
			}
		}
	}

	/**
	 * Copy constructor for the Queue class
	 * 
	 * @param original the Queue to copy
	 * @postcondition a new Queue object which is an identical, but distinct, copy
	 *                of original
	 */
	@SuppressWarnings("unchecked")
	public Queue(Queue<T> original) {
		if (original == null) {
			return;
		}
		if (original.getSize() == 0) {
			queue = (T[]) new Object[10];
			curr_size = 0;
		} else {
			queue = (T[]) new Object[10];
			curr_size = 0;
			for (int i = 0; i < original.getSize(); i++) {
				this.enqueue(original.queue[i]);
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
		if (curr_size == 0) {
			throw new NoSuchElementException("getFront(): no front because queue is empty");
		} else {
			return queue[0];
		}
	}

	/**
	 * Returns the length of the Queue
	 * 
	 * @return the length from 0 to n
	 */
	public int getSize() {
		return curr_size;
	}

	/**
	 * Determines whether a Queue is empty
	 * 
	 * @return whether the Queue is empty
	 */
	public boolean isEmpty() {
		return curr_size == 0;
	}

	/**** MUTATORS ****/

	// add methods here
	/**
	 * Inserts a new value at the end ofthe Queue
	 * 
	 * @param data the new data to insert
	 * @postcondition a new node at the end of the Queue
	 */
	public void enqueue(T data) {

		if (curr_size == queue.length) {

			resize();
			queue[curr_size] = data;
			curr_size++;

		}

		else {
			queue[curr_size] = data;
			curr_size++;
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
		} else {
			for (int i = 0; i < curr_size - 1; i++) {

				queue[i] = queue[i + 1];
			}
		}
		curr_size--;
	}

	/**
	 * Returns the values stored in the Queue as a String, separated by a blank
	 * space with a new line character at the end
	 * 
	 * @return a String of Queue values
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < curr_size; i++) {
			result.append(queue[i] + " ");

		}
		return result.toString() + "\n";
	}

	/**
	 * Determines whether two Queues contain the same values in the same order
	 * 
	 * @param o the Object to compare to this
	 * @return whether o and this are equal
	 */
	@Override
	// having space issue same issue as the reverseQueue Method
	public boolean equals(Object o) {
		if (o == this) {

			return true;
		} else if (!(o instanceof Queue)) {

			return false;
		} else {
			Queue<T> n = (Queue<T>) o;
			if (this.curr_size != n.curr_size) {

				return false;
			} else {
				for (int i = 0; i < curr_size - 1; i++) {
					if (!this.queue[i].equals(n.queue[i])) {

						return false;
					}
				}
			}
			return true;
		}
	}

	/**
	 * Recursively (no loops) creates a String where the array is in reverse order
	 * by calling the private helper method
	 */

	public String reverseQueue() {
		if (curr_size == 0) {
			return "\n";
		} else {
			return reverseQueue(curr_size - 1) + "\n";
		}

	}

	/** PRIVATE HELPER METHODS */

	/**
	 * private helper method for reverseQueue
	 * 
	 * @param index the current index
	 * @return a String of this Queue in reverse order
	 */
	private String reverseQueue(int index) {
		if (index == 0) {
			return queue[0].toString() + " ";
		} else {
			return "" + queue[index].toString() + " " + reverseQueue(index - 1) + "";
		}
	}

	/**
	 * Increases the current array size by 10
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		int size = queue.length + 10;
		T[] arr = (T[]) new Object[size];
		for (int i = 0; i < curr_size; i++) {
			arr[i] = queue[i];
		}
		queue = arr;
	}
}
