/** Defines a doubly-linked list class
 * @author Daniel Tran
 * @author Alex Benny
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
     * @postcondition new instance of linked list class with default values is created
     */
    public LinkedList() {
    	length = 0;
    	first = null;
    	last = null;
    	iterator = null;
    }

    /**
     * Converts the given array into a LinkedList
     * @param array the array of values to insert into this LinkedList
     * @postcondition Converts given array into linked list
     */
    public LinkedList(T[] array) {
    	if (array == null)
    	{
    		length = 0;
        	first = null;
        	last = null;
        	iterator = null;
    	}
    	else
    	{
	    	for (int i = 0; i < array.length; i++)
	    	{
	        	this.addLast(array[i]);
	    	}
    	}
    }
    
    /**
     * Instantiates a new LinkedList by copying another List
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
    public LinkedList(LinkedList<T> original) {
    	if (original == null) { 
    		length = 0;
            first = null;
            last = null;
            iterator = null;
        }

    	if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } 
        else {
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
     * @precondition length > 0
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException {
    	if (length == 0)
    	{
    		throw new NoSuchElementException("getFirst: "
                    + "List is Empty. No data to access!"); 
    	}
        return first.data;
    }

    /**
     * Returns the value stored in the last node
     * @precondition length > 0
     * @return the value stored in the node last
     * @throws NoSuchElementException When precondition violated
     */
    public T getLast() throws NoSuchElementException {
    	if (this.length == 0)
    	{
    		throw new NoSuchElementException("getLast: "
                    + "List is Empty. No data to access!"); 
    	}
        return last.data;
    }

    /**
     * Returns the data stored in the iterator node
     * @precondition iterator is offEnd
     * @throw NullPointerException when iterator is null
     */
    public T getIterator() throws NullPointerException {
    	if (this.iterator == null)
    	{
    		throw new NullPointerException("No data accessed at getIterator()"); 
    	}
        return this.iterator.data;
    }

    /**
     * Returns the current length of the LinkedList
     * @return the length of the LinkedList from 0 to n
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Returns whether the LinkedList is currently empty
     * @return whether the LinkedList is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

 

   /**
     * Returns whether the iterator is offEnd, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
    	return iterator == null;
    }

 

    /**** MUTATORS ****/

    /**
     * Creates a new first element
     * @param data the data to insert at the front of the LinkedList
     * @postcondition new head added to list, increased length by 1
     */
    public void addFirst(T data) {
        if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.next = first;
            first.prev = N;
            first = N;
        }
        length++;
      }
  

    /**
     * Creates a new last element
     * @param data the data to insert at the end of the LinkedList
     * @postcondition new tail added to list, increased length by 1
     */
    public void addLast(T data) {
        if (first == null) {
            first = last = new Node(data);
        } else {
        	Node N = new Node(data); 
            last.next = N; 
            N.prev = last; 
            last = N; 

        }
        length++;
      }
    
    /**
     * Inserts a new element after the iterator
     * @param data the data to insert
     * @precondition iterator is offEnd
     * @throws NullPointerException when iterator is offEnd
     */
    public void addIterator(T data) throws NullPointerException{
    	if(offEnd()) {
    		throw new NullPointerException("addIterator: Iterator is null. Cannot add an element");
    	}
    	else if(iterator == last) {
    		addLast(data);
    	}
    	else {
    		Node n = new Node(data);
    		n.next = iterator.next;
    		n.prev = iterator;
    		iterator.next.prev = n;
    		iterator.next = n;
    		length++;
    	}
    }

    /**
     * removes the element at the front of the LinkedList
     * @precondition length != 0
     * @postcondition original head replaced with head.next, length decreased 
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException { // how to delete node from memory
    	if (this.length == 0)
    	{
    		throw new NoSuchElementException("No such element found at removeFirst()"); 
    	}
    	else if (this.length == 1)
    	{
    		this.first = null;
    	}
    	else
    	{
    		first.next.prev = null;
	    	this.first = first.next;
    	}
    	length--;
    }

    /**
     * removes the element at the end of the LinkedList
     * @precondition length != 0
     * @postcondition original tail replaced with tail.prev, length decreased
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException {
    	if (this.length == 0)
    	{
    		throw new NoSuchElementException("No such element found at removeLast()"); 
    	}
    	else if (this.length == 1)
    	{
    		this.last = null;
    	}
    	else
    	{
    		last.prev.next = null;
	    	this.last = last.prev;
	    	
    	}
    	length--;
    }
    
    /**
     * removes the element referenced by the iterator
     * @precondition if iterator is offEnd
     * @postcondition removes the node that the iterator is pointing at
     * @throws NullPointerException if precondition is violated
     */
    public void removeIterator() throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("removeIterator: Iterator is null. Cannot remove an element");
    	}
    	else if(iterator == first) {
    		//edge case 1
    		removeFirst(); //should set iterator to null in this case
    	}
    	else if(iterator == last) {
    		//edge case 2
    		removeLast(); //should set iterator to null in this case
    	}
    	else {
    		//general case
    		iterator.prev.next = iterator.next;
 	        iterator.next.prev = iterator.prev;
 	        iterator = null;
 	        length--;
    	}
    }
    
    /**
     * places the iterator at the first node
     * @postcondition iterator is now storing the address for the first node
     */
    public void positionIterator(){
    	iterator = this.first;
        
    }
    
    /**
     * Moves the iterator one node towards the last
     * @precondition iterator is offEnd
     * @postcondition moves the iterator forward by one.
     * @throws NullPointerException when iterator is offEnd
     */
    public void advanceIterator() throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("advanceIterator: Iterator is null. Cannot advance");
    	}
        iterator = iterator.next;
    }
    
    /**
     * Moves the iterator one node towards the first
     * @precondition iterator is offEnd
     * @postcondition moves the iterator one node backwards
     * @throws NullPointerExceptionif the precondition is violated
     */
    public void reverseIterator() throws NullPointerException {
    	if (iterator == null) { 
	         throw new NullPointerException("reverseIterator(): Cannot advance because iterator points to null"); 
	      } 
	      else { 
	         iterator = iterator.prev; 
	      } 
    }

    /**** ADDITIONAL OPERATIONS ****/

  
    /**
     * Converts the LinkedList to a String, 
     * with each value separated by a blank
     * line At the end of the String, place a 
     * new line character
     * @return the LinkedList as a String
     */
    
    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        while(temp != null) {
            result.append(temp.data + " ");
            temp = temp.next;
        }
        return result.toString() + "\n";
    }
   
    
    /**
     * Determines whether the given Object is
     * another LinkedList, containing
     * the same data in the same order
     * @param o another Object
     * @return whether there is equality
     */
    @SuppressWarnings("unchecked") //good practice to remove warning here
    @Override public boolean equals(Object o) {
    	if(o == this) {
    		return true;
    	}
    	else if (!(o instanceof LinkedList)) {
    		return false;
    	}
    	else {
    		LinkedList<T> List = (LinkedList<T>) o;
    		if(this.getLength() != List.getLength()) {
    			return false;
    		}
    		else {
    			Node temp1 = this.first;
    			Node temp2 = List.first;
    			while(temp1 != null) {
    				if(temp1.data != temp2.data) {
    					return false;
    				}
    				temp1 = temp1.next;
    				temp2 = temp2.next;
    			}
    			return true;
    		}
    	}

    }
    
    /**CHALLENGE METHODS*/
    
    
    /**
     * Zippers two LinkedLists to create a third List
     * which contains alternating values from this list
     * and the given parameter
     * For example: [1,2,3] and [4,5,6] -> [1,4,2,5,3,6]
     * For example: [1, 2, 3, 4] and [5, 6] -> [1, 5, 2, 6, 3, 4]
     * For example: [1, 2] and [3, 4, 5, 6] -> [1, 3, 2, 4, 5, 6]
     * @param list the second LinkedList in the zipper
     * @return a new LinkedList, which is the result of
     * zippering this and list
     * @postcondition this and list are unchanged
     */

    public LinkedList<T> zipperLists(LinkedList<T> list) {
    	if (list.isEmpty())
    	{
    		return this;
    	}
    	if (this.isEmpty())
    	{
    		return list;
    	}
    	LinkedList<T> n = new LinkedList<T>();
    	Node headL, headT;
    	headL  = list.first;
    	headT = this.first;
    	
    	int longerLength = 0;
    	if (list.length > this.length) {
    		longerLength = list.length;
    		longerLength++;
    		} 
    	else 
    	{
    		longerLength = this.length;
    	}
    	for (int i = 0; i < longerLength ; i++)
    	{
    		if (headT != null)
    		{
    			n.addLast(headT.data);
        		headT = headT.next;
    		}
    		if (headL != null)
    		{
    			n.addLast(headL.data);
        		headL = headL.next;
    		}
    		//length++;
    	}
    	//System.out.println(n);

        return n;
    }
    
    /**
     * Determines whether a LinkedList is
     * reversible, i.e. the data is the same
     * written both forward and backward
     * e.g. isReversible(1 2 3 2 1) -> true
     * e.g. isReversible(A B B A) -> true
     * @return whether the list is reversible
     */
    public boolean isReversible() {
    	System.out.println(this.toString());
    	Node head, tail;
    	head = first;
    	tail = last;
    	if (length == 0 || length == 1)
    	{
    		//System.out.println("true");
    		return true;
    	}
    	for (int i = 0; i < length/2; i++)
    	{
    		//System.out.println(head.data);
    		//System.out.println(tail.data);

    		/*if (head.data.compareTo(tail.data) != 0)
    		{
    			System.out.println("false");
    			return false;
    		}
    		*/
    		if (head.data.toString().equals(tail.data.toString()) == false)
    		{
    			return false;
    		}
    		
    		//System.out.println("true");

    		head = head.next;
    		tail = tail.prev;
    	}
    	 
        return true;
    }
}