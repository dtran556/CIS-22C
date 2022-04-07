/**
 * HashTable.java
 * @author Soham Manoli
 * @author Anna Dymchenko
 * CIS 22C, Lab 5
 */
import java.util.ArrayList;

public class HashTable<T> {

	private int numElements;
	private ArrayList<LinkedList<T>> Table;

	/**
	 * Constructor for the HashTable
	 * class. Initializes the Table to
	 * be sized according to value passed
	 * in as a parameter
	 * Inserts size empty Lists into the
	 * table. Sets numElements to 0
	 * @param size the table size
	 */
	public HashTable(int size) {
		Table = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Table.add(new LinkedList<>());
		}
		numElements = 0;
	}

	/**
	 * Constructor for HashTable class
	 * Inserts the contents of the given array
	 * into the Table at the appropriate indices
	 * @param array an array of elements to insert
	 * @param size the size of the Table
	 */
	public HashTable(T[] array, int size) {
		Table = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Table.add(new LinkedList<>());
		}
		numElements = 0;
		if(array != null){
			for(T data : array){
				add(data);
			}
		}
	}

	/**Accessors*/

	/**
	 * returns the hash value in the Table
	 * for a given Object
	 * @param t the Object
	 * @return the index in the Table
	 */
	private int hash(T t) {
		int code = t.hashCode();
		return Math.abs(code) % Table.size();
	}

	/**
	 * Counts the number of elements at this index
	 * @param index the index in the Table
	 * @precondition index < Table.size() and index >= 0
	 * @return the count of elements at this index
	 * @throws IndexOutOfBoundsException when
	 * the precondition is violated
	 */
	public int countBucket(int index) throws IndexOutOfBoundsException{
		if(index >= Table.size() || index < 0){
			throw new IndexOutOfBoundsException("countBucket(): index must be less than size and non-negative");
		}
		return Table.get(index).getLength();
	}

	/**
	 * Determines total number of elements in the Table
	 * @return total number of elements
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * Accesses a specified element in the Table
	 * @param t the element to locate
	 * @return the bucket number where the element
	 * is located or -1 if it is not found
	 * @precondition the parameter t cannot be null
	 * @throws NullPointerException when the
	 * precondition is violated
	 */
	public int find(T t) throws NullPointerException{
		if(t == null){
			throw new NullPointerException("find(): parameter t cannot be null");
		}
		int bucket = hash(t);
		if(Table.get(bucket).findIndex(t) != -1){
			return bucket;
		}else{
			return -1;
		}
	}

	/**
	 * Returns a specified element in the Table
	 * @param t the element to locate
	 * @return the object with the same hash as t
	 * is located or null if it is not found
	 * @precondition the parameter t cannot be null
	 * @throws NullPointerException when the
	 * precondition is violated
	 */
	public T get(T t) throws NullPointerException{
		if(t == null){
			throw new NullPointerException("get(): parameter t cannot be null");
		}
		if(find(t) != -1){
			LinkedList<T> list = Table.get(find(t));
			list.positionIterator();
			int index = list.findIndex(t);
			list.advanceIteratorToIndex(index);
			return list.getIterator();
		}else{
			return null;
		}
	}

	/**
	 * Determines whether a specified element is in
	 * the Table
	 * @param t the element to locate
	 * @return  whether the element is in the Table
	 * @precondition the parameter t cannot be null
	 * @throws NullPointerException when the
	 * precondition is violated
	 */
	public boolean contains(T t) throws NullPointerException{
		if(t == null){
			throw new NullPointerException("contains(): parameter t cannot be null");
		}
		return find(t) != -1;
	}


	/**Mutators*/

	/**
	 * Inserts a new element in the Table
	 * at the end of the chain of the
	 * correct bucket
	 * @param t the element to insert
	 * @precondition the parameter t cannot be null
	 * @throws NullPointerException when the
	 * precondition is violated
	 */
	public void add(T t) throws NullPointerException{
		if(t == null){
			throw new NullPointerException("add(): parameter t cannot be null");
		}
		int bucket = hash(t);
		Table.get(bucket).addLast(t);
		numElements++;
	}

	/**
	 * Removes the given element from the Table
	 * @param t the element to remove
	 * @precondition the parameter t cannot be null
	 * @return whether t exists and was removed from the Table
	 * @throws NullPointerException when the precondition
	 * is violated
	 */
	public boolean delete(T t) throws NullPointerException {
		if(t == null){
			throw new NullPointerException("delete(): parameter t cannot be null");
		}
		int bucket = hash(t);
		int index = Table.get(bucket).findIndex(t);
		if(index == -1) {
			return false;
		}
		Table.get(bucket).positionIterator();
		Table.get(bucket).advanceIteratorToIndex(index);
		Table.get(bucket).removeIterator();
		numElements--;
		return true;
	}

	/**
	 * Resets the hash table back to the
	 * empty state, as if the one argument
	 * constructor has just been called.
	 */
	public void clear() {
		for (int i = 0; i < Table.size() ; i++) {
			Table.set(i, new LinkedList<>());
		}
		numElements = 0;
	}

	/**Additional Methods*/

	/**
	 * Computes the load factor
	 * @return the load factor
	 */
	public double getLoadFactor() {
		return ((double) numElements)/Table.size();
	}

	/**
	 * Creates a String of all elements at a given bucket
	 * @param bucket the index in the Table
	 * @return a String of elements, separated by spaces
	 * with a new line character at the end
	 */
	public String bucketToString(int bucket) {
		return Table.get(bucket).toString();
	}

	/**
	 * Creates a String of the bucket number
	 * followed by a colon followed by the first
	 * element at each bucket followed
	 * by a new line
	 * For empty buckets, add the bucket number
	 * followed by a colon followed by empty
	 * @return a String of all first elements
	 * at each bucket
	 */
	public String rowToString(){
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < Table.size() ; i++) {
			out.append("Bucket ");
			out.append(i);
			out.append(": ");
			if(Table.get(i).isEmpty()){
				out.append("empty");
			}else{
				out.append(Table.get(i).getFirst());
			}
			out.append('\n');
		}
		return out.toString();
	}

	/**
	 * Starting at the 0th bucket, and continuing
	 * in order until the last bucket,concatenates all
	 * elements at all buckets into one String, with
	 * a new line between buckets and one more new line at the
	 * end of the entire String
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < Table.size() ; i++) {
			if(Table.get(i).getLength()>0) out.append(Table.get(i).toString());
		}
		out.append('\n');
		return out.toString();
	}
}
