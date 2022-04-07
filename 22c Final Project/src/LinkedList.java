
/**
 * LinkedList.java
 * @author Kim Bui
 * @author 
 * @author 
 * CIS 22C, Final Project
 */
import java.util.NoSuchElementException;

public class LinkedList<T> {
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}

	private int length;
	private Node first;
	private Node last;
	private Node iterator;

	/**** CONSTRUCTORS ****/
	/**
	 * Instantiates a new LinkedList with default values
	 *
	 * @postcondition length = 0 first = last = iterator = null
	 */
	public LinkedList() {
		this.length = 0;
		this.first = null;
		this.last = null;
		this.iterator = null;
	}

	/**
	 * Converts the given array into a LinkedList
	 *
	 * @param array the array of values to insert into this LinkedList
	 * @postcondition a new Linked List is created with all array elements
	 */
	public LinkedList(T[] array) {
		if (array.length == 0) {
			first = last = null;
			this.length = 0;
		} else {
			for (int i = 0; i < array.length; i++) {
				Node newNode = new Node(array[i]);
				if (length == 0) {
					first = last = newNode;
					first.prev = last.next = null;
				} else {
					last.next = newNode;
					newNode.prev = last;
					last = newNode;
					last.next = null;
				}
				length++;
			}
		}
	}

	/**
	 * Instantiates a new LinkedList by copying another List
	 *
	 * @param original the LinkedList to copy
	 * @postcondition a new List object, which is an identical, but separate, copy
	 *                of the LinkedList original
	 */
	public LinkedList(LinkedList<T> original) {
		if (original == null) {
			return;
		}
		if (original.length == 0) {
			this.length = 0;
			this.first = null;
			this.last = null;
			this.iterator = null;
		} else {
			Node temp = original.first;
			while (temp != null) {
				addLast(temp.data);
				temp = temp.next;
			}
			iterator = null;
		}
	}

	/**** ACCESSORS ****/
	/**
	 * Returns the value stored in the first node
	 *
	 * @precondition length > 0
	 * @return the value stored at node first
	 * @throws NoSuchElementException when length = 0
	 */
	public T getFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getFirst(): LinkedList is empty. No data to access!");
		}
		return first.data;
	}

	/*
	 * Returns the value stored in the last node
	 *
	 * @precondition length > 0
	 * 
	 * @return the value stored in the node last
	 * 
	 * @throws NoSuchElementException when length = 0
	 */
	public T getLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("getLast(): LinkedList is empty. No data to access!");
		}
		return last.data;
	}

	/**
	 * Returns the data stored in the iterator node
	 *
	 * @precondition the iterator is not null
	 * @throw NullPointerException iterator is null
	 */
	public T getIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("getIterator(): iterator is null. No data to access!");
		}
		return iterator.data;
	}

	/**
	 * Returns the current length of the LinkedList
	 *
	 * @return the length of the list from 0 to n
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns whether the LinkedList is currently empty
	 *
	 * @return whether the LinkedList is empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * Returns whether the iterator is offEnd, i.e. null
	 *
	 * @return whether the iterator is null
	 */
	public boolean offEnd() {
		return iterator == null;
	}
	
	/**
	 * Determines at which index the iterator is located Indexed from 0 to 
	 * length - 1
	 * 
	 * @return the index number of the iterator
	 * @precondition iterator is not null
	 * @throws NullPointerException when precondition is violated
	 */
	public int getIteratorIndex() throws NullPointerException {
		int index = 0;
		
		if (offEnd()) {
			throw new NullPointerException("getIteratorIndex(): Iterator is null. Cannot get index!");
		} else if (iterator == first) {
			return 0;
		} else if (iterator == last) {
			return length - 1;
		} else {
			Node temp = first;
			while (temp != iterator) {
				index++;
				temp = temp.next;
			}
			return index;
		}
	}

	/**
	 * Searches the LinkedList for a given element's index
	 * 
	 * @param data the data whose index to locate
	 * @return the index of the data or -1 if the data is not 
	 *         contained in the LinkedList
	 */
	public int findIndex(T data) {
		int index = 0;
		
		if (isEmpty()) {
			return -1;
		} else {
			Node temp = first;
			while (temp != null) {
				if (temp.data.equals(data)) {
					return index;
				} else {
					index++;
					temp = temp.next;
					if (temp == null) {
						return -1;
					}
				}
			}
			return index;
		}
	}

	/**** MUTATORS ****/
	/**
	 * Creates a new first element
	 *
	 * @param data the data to insert at the front of the LinkedList
	 * @postcondition a new element is added at the beginning of the LinkedList
	 */
	public void addFirst(T data) {
		if (length == 0) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			first.prev = N;
			N.next = first;
			first = N;
		}
		length++;
	}

	/**
	 * Creates a new last element
	 *
	 * @param data the data to insert at the end of the LinkedList
	 * @postcondition a new element is added at the end of the LinkedList
	 */
	public void addLast(T data) {
		if (length == 0) {
			first = last = new Node(data);
		} else {
			Node N = new Node(data);
			last.next = N;
			N.prev = last;
			last = N;
			last.next = null;
		}
		length++;
	}

	/**
	 * Inserts a new element after the iterator
	 *
	 * @param data the data to insert
	 * @precondition iterator is not null
	 * @throws NullPointerException when iterator is null
	 */
	public void addIterator(T data) throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("addIterator(): iterator is null. Cannot add.");
		} else if (iterator == last) {
			addLast(data);
		} else {
			Node newNode = new Node(data);
			newNode.prev = iterator;
			newNode.next = iterator.next;
			iterator.next.prev = newNode;
			iterator.next = newNode;
			length++;
		}
	}

	/**
	 * Removes the element at the front of the LinkedList
	 *
	 * @precondition length > 0
	 * @postcondition the first element is removed from the LinkedList
	 * @throws NoSuchElementException when the precondition is violated
	 */
	public void removeFirst() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeFirst(): LinkedList is empty. No data to remove!");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == first) {
				iterator = null;
			}
			first = first.next;
			first.prev = null;
		}
		length--;
	}

	/**
	 * Removes the element at the end of the LinkedList
	 *
	 * @precondition length > 0
	 * @postcondition the last element is removed from the LinkedList
	 * @throws NoSuchElementException when length = 0
	 */
	public void removeLast() throws NoSuchElementException {
		if (length == 0) {
			throw new NoSuchElementException("removeLast(): LinkedList is empty. No data to remove!");
		} else if (length == 1) {
			first = last = iterator = null;
		} else {
			if (iterator == last) {
				iterator = null;
			}
			last = last.prev;
			last.next = null;
		}
		length--;
	}

	/**
	 * Removes the element referenced by the iterator
	 *
	 * @precondition the iterator is not null
	 * @postcondition the iterator is removed
	 * @throws NullPointerException when the iterator is null
	 */
	public void removeIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("removeIterator(): iterator is null. Cannot remove!");
		} else if (iterator == first) {
			removeFirst();
		} else if (iterator == last) {
			removeLast();
		} else {
			iterator.next.prev = iterator.prev;
			iterator.prev.next = iterator.next;
			iterator = null;
			length--;
		}
	}

	/**
	 * Places the iterator at the first node
	 *
	 * @postcondition iterator is positioned at the first node
	 */
	public void positionIterator() {
		iterator = first;
	}

	/**
	 * Moves the iterator one node towards the last
	 *
	 * @precondition the iterator is not null
	 * @postcondition iterator is moved to the next node
	 * @throws NullPointerException when the iterator is null
	 */
	public void advanceIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("advanceIterator(): iterator is null. Cannot advance!");
		}
		iterator = iterator.next;
	}
	
	/**
	 * Advances the iterator to location within the LinkedList
	 * specified by the given index
	 * 
	 * @param index the index at which to place the iterator.
	 * @precondition iterator is not null
	 * @throws NullPointerException when the precondition is violated
	 */
	public void advanceIteratorToIndex(int index) throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("advanceIteratorToIndex(): Iterator is null. Cannot advance!");
		} 
		if (index == 0) {
			return;
		} else {
			for (int i = 0; i < index; i++) {
				advanceIterator();
			}
		}
	}

	/**
	 * Moves the iterator one node towards the first
	 *
	 * @precondition the iterator is not null
	 * @postcondition iterator is moved to the previous node
	 * @throws NullPointerException when the iterator is null
	 */
	public void reverseIterator() throws NullPointerException {
		if (offEnd()) {
			throw new NullPointerException("reverseIterator(): iterator is null. Cannot reverse!");
		}
		iterator = iterator.prev;
	}

	/**** ADDITIONAL OPERATIONS ****/
	/**
	 * Converts the LinkedList to a String, with each value separated by a blank
	 * line At the end of the String, place a new line character
	 *
	 * @return the LinkedList as a String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node temp = first;
		while (temp != null) {
			result.append(temp.data + " ");
			temp = temp.next;
		}
		return result.toString() + "\n";
	}

	/**
	 * Determines whether the given Object is another LinkedList, containing the
	 * same data in the same order
	 *
	 * @param o another Object
	 * @return whether there is equality
	 */
	@SuppressWarnings("unchecked") // good practice to remove warning here
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof LinkedList)) {
			return false;
		} else {
			LinkedList<T> L = (LinkedList<T>) o;
			if (this.length != L.length) {
				return false;
			} else {
				Node temp1 = this.first;
				Node temp2 = L.first;
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
	
	/**
	 * Determines whether a LinkedList is reversible, i.e. the data is the same
	 * written both forward and backward e.g. isReversible(1 2 3 2 1) -> true e.g.
	 * isReversible(A B B A) -> true
	 *
	 * @return whether the list is reversible
	 */
	public boolean isReversible() {
		if (isEmpty() || length == 1) {
			return true;
		} else {
			Node headTemp = first;
			Node lastTemp = last;
			for (int i = 0; i < length / 2;) {
				if (!headTemp.data.equals(lastTemp.data)) {
					return false;
				}
				headTemp = headTemp.next;
				lastTemp = lastTemp.prev;
				i++;
			}
			return true;
		}
	}

	/** CHALLENGE METHODS */
	/**
	 * Zippers two LinkedLists to create a third List which contains alternating
	 * values from this list and the given parameter For example: [1,2,3] and
	 * [4,5,6] -> [1,4,2,5,3,6] For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6,
	 * 3, 4] For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
	 *
	 * @param list the second LinkedList in the zipper
	 * @return a new LinkedList, which is the result of zippering this and list
	 * @postcondition this and list are unchanged
	 */
	public LinkedList<T> zipperLists(LinkedList<T> list) {
		LinkedList<T> newList = new LinkedList<>();
		;
		if (list.length == 0) {
			return this;
		}
		Node temp1 = this.first;
		Node temp2 = list.first;
		if (this.length == list.length) {
			while (temp2 != null) {
				newList.addLast(temp1.data);
				newList.addLast(temp2.data);
				temp1 = temp1.next;
				temp2 = temp2.next;
			}
		} else if (this.length < list.length) {
			while (temp2 != null) {
				while (temp1 != null) {
					newList.addLast(temp1.data);
					newList.addLast(temp2.data);
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				newList.addLast(temp2.data);
				temp2 = temp2.next;
			}
		} else {
			while (temp1 != null) {
				while (temp2 != null) {
					newList.addLast(temp1.data);
					newList.addLast(temp2.data);
					temp1 = temp1.next;
					temp2 = temp2.next;
				}
				newList.addLast(temp1.data);
				temp1 = temp1.next;
			}
		}
		return newList;
	}
}