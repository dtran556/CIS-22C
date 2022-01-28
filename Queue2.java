
/**
 * Queue class - Two Stack Version
 * @author Alex Benny
 * @author Daniel Tran
 * CIS 22C, Lab 3
 */

import java.util.NoSuchElementException;

public class Queue<T extends Comparable<T>> implements Q<T> {
	private Stack<T> stack1; // stack 1 = front
	private Stack<T> stack2; // stack 2 = back

	/**** CONSTRUCTORS ****/
	/**
	 * Default constructor for the Queue class
	 */
	public Queue() {
		stack1 = new Stack<T>();
		stack2 = new Stack<T>();

	}

	/**
	 * Converts an array into a Queue
	 * 
	 * @param array the array to copy into the array
	 */
	public Queue(T[] array) {
		stack1 = new Stack<T>();
		stack2 = new Stack<T>();
		if (array == null) {
			return;
		}
		// System.out.println(array.length);
		for (int i = 0; i < array.length; i++) {
			stack2.push(array[i]);
		}
		for (int i = array.length - 1; i >= 0; i--) {
			stack1.push(array[i]);
		}
	}

	/**
	 * Copy constructor for the Queue class
	 * 
	 * @param original the Queue to copy
	 * @postcondition a new Queue object which is an identical, but distinct, copy
	 *                of original
	 */
	public Queue(Queue<T> original) {

		if (original == null) {
			stack1 = new Stack<T>();
			stack2 = new Stack<T>();
			return;
		}
		stack1 = new Stack<T>(original.stack1);
		stack2 = new Stack<T>(original.stack2);
		// while(!original.stack1.isEmpty())
		// {
		// stack2.push(original.stack1.peek());
		// stack1.push(original.stack2.peek());
		// original.stack1.pop();
		// original.stack2.pop();
		// }
	}

	// add methods here
	/**
	 * Returns the value stored at the front of the Queue
	 * 
	 * @return the value at the front of the queue
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public T getFront() throws NoSuchElementException {
		if (stack1.isEmpty()) {
			throw new NoSuchElementException("getFront(): no front because queue is empty");
		} else {
			return stack1.peek();
		}
	}

	/**
	 * Returns the length of the Queue
	 * 
	 * @return the length from 0 to n
	 */
	public int getSize() {
		return stack1.getSize();
	}

	/**
	 * Determines whether a Queue is empty
	 * 
	 * @return whether the Queue is empty
	 */
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	/**** MUTATORS ****/

	// add methods here
	/**
	 * Inserts a new value at the end of the Queue
	 * 
	 * @param data the new data to insert
	 * @postcondition a new node at the end of the Queue
	 */
	@SuppressWarnings("unchecked")
	public void enqueue(T data) {
		stack2.push(data);
		Stack<T> temp = new Stack<T>();
		// for(int i = 0; i < stack1.getSize() - 1; i++)
		while (!stack1.isEmpty()) {
			temp.push(stack1.peek());
			stack1.pop();
		}

		stack1.push(data);

		while (!temp.isEmpty()) {
			stack1.push(temp.peek());
			temp.pop();

		}
		// stack1.toString();

	}

	/**
	 * Removes the front element in the Queue
	 * 
	 * @precondition !isEmpty()
	 * @throws NoSuchElementException when the precondition is violated
	 * @postcondition the front element has been removed
	 */

	// stack 1 is front
	// stack 2 is back
	public void dequeue() throws NoSuchElementException {
		if (stack1.isEmpty()) {
			throw new NoSuchElementException("dequeue(): Queue is empty. " + "No element to remove");
		} else {
			// System.out.println(stack2.toString());
			// stack 1 is empty error pre condition
			// else set the top of stack 1 to a temp and then pop the first element from
			// stack 1
			Stack<T> temp = new Stack<T>();
			stack1.pop();
			while (!stack2.isEmpty()) {
				temp.push(stack2.peek());
				stack2.pop();
			}
			temp.pop();

			while (!temp.isEmpty()) {
				stack2.push(temp.peek());
				temp.pop();

			}
		}
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
		return stack1.toString();
	}

	/**
	 * Determines whether two Queues contain the same values in the same order
	 * 
	 * @param o the Object to compare to this
	 * @return whether o and this are equal
	 */
	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		} else if (!(o instanceof Queue)) {

			return false;
		} else {
			Queue<T> n = (Queue<T>) o;
			if (n.stack1.equals(stack1) && n.stack2.equals(stack2))

			{
				return true;
			}

		}

		return false;
	}

	public String reverseQueue() {
		return stack2.toString();
	}
}
