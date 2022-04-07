/**
 * @author Soham Manoli
 * @author Anna Dymchenko
 * Final Project
 */

import java.util.Comparator;
import java.util.ArrayList;
import java.lang.Math;

public class Heap<T> {

    private int heapSize;
    private ArrayList<T> heap;


    /*
    * Note: The following heap data structure is implemented with an empty index of 0
    * This means that we measure index from 1 (is more efficient arithmetically at runtime)
    * */

    /**Constructors/

     /**
     * Constructor for the Heap class
     * @param data an unordered ArrayList
     * Calls buildHeap
     */
    public Heap(ArrayList<T> data, Comparator<T> comparator){
        heapSize = data.size();
        heap = new ArrayList<>(heapSize+1);
        for(int i = 0; i < data.size(); i++){
            if(data.get(0)==null){
                heap.set(i, data.get(i));
            }else{
                heap.set(i+1, data.get(i));
            }
        }
        buildHeap(comparator);
    }

    /**Mutators*/

    /**
     * Converts an ArrayList into a valid
     * max heap. Called by constructor
     * Calls helper method heapify
     */
    public void buildHeap(Comparator<T> comparator){
        for(int i = (int) Math.floor(heapSize/2); i >= 1; i--){
            heapify(i, comparator);
        }
    }

    /**
     * helper method to buildHeap, remove, and sort
     * bubbles an element down to its proper location within the heap
     * @param index an index in the heap
     */
    private void heapify(int index, Comparator<T> comparator) throws IndexOutOfBoundsException{
        int left = getLeft(index);
        int right = getRight(index);
        int maxIndex = index;
        if(left <= heapSize && comparator.compare(heap.get(left), heap.get(maxIndex)) > 0){
            maxIndex = left;
        }
        if(right <= heapSize && comparator.compare(heap.get(right), heap.get(maxIndex)) > 0){
            maxIndex = right;
        }
        if(index != maxIndex){
            swap(heap, index, maxIndex);
            heapify(maxIndex, comparator);
        }
    }


    /**
     * Helper method to swap two nodes
     * @param indexOne first node's index to swap
     * @param indexTwo second node's index to swap
     */

    private void swap(ArrayList<T> arr, int indexOne, int indexTwo){
        T n = arr.get(indexOne);
        arr.set(indexOne, arr.get(indexTwo));
        arr.set(indexTwo, n);
    }

    /**
     * Inserts the given data into heap
     * Calls helper method heapIncreaseKey
     * @param key the data to insert
     */
    public void insert(T key, Comparator<T> comparator){
        heapSize++;
        heapIncreaseKey(heap.size()-1, key, comparator);
    }

    /**
     * Helper method for insert. Bubbles an element up to its proper location
     * @param index the current index of the key
     * @param key the data
     * @precondition index must be in bounds of ArrayList heap, index < lastIndex and index >= 1
     */
    private void heapIncreaseKey(int index, T key, Comparator<T> comparator) throws IndexOutOfBoundsException{
        if(index > getHeapSize()-1 || index < 1){
            throw new IndexOutOfBoundsException("heapIncreaseKey(): index must be in bounds of ArrayList");
        }else if(comparator.compare(key, heap.get(index)) > 0){
            heap.set(index, key);
            while(index > 1 && comparator.compare(heap.get(getParent(index)), heap.get(index)) < 0){
                swap(heap, index, getParent(index));
                index = getParent(index);
            }
        }
    }


    /**
     * removes the element at the specified index
     * Calls helper method heapify
     * @param index the index of the element to remove
     * @precondition index must be in bounds of ArrayList heap, index < lastIndex and index >= 1
     */
    public void remove(int index, Comparator<T> comparator) throws IndexOutOfBoundsException{
        if(index > getHeapSize()-1 || index < 1){
            throw new IndexOutOfBoundsException("remove(): index must be in bounds of ArrayList");
        }else{
            heap.remove(index);
            heapSize--;
            heapify(1, comparator);
        }
    }

    /**Accessors*/

    /**
     * returns the maximum element (highest priority)
     * @return the max value
     */
    public T getMax(){
        return heap.get(1);
    }


    /**
     * returns the location (index) of the 
     * parent of the element stored at index
     * @param index the current index
     * @return the index of the parent
     * @precondition index must be in bounds of ArrayList heap, index < lastIndex and index >= 1
     * @throws IndexOutOfBoundsException
     */
    public int getParent(int index) throws IndexOutOfBoundsException {
        if(index > getHeapSize()-1 || index < 1){
            throw new IndexOutOfBoundsException("getParent(): index must be in bounds of ArrayList");
        }else{
            int parentIndex = (int) Math.floor(index/2);
            return parentIndex <= 0 ? null : parentIndex;
        }
    }

    /**
     * returns the location (index) of the 
     * left child of the element stored at index
     * @param index the current index
     * @return the index of the left child
     * @precondition index must be in bounds of ArrayList heap, index < lastIndex and index >= 1
     * @throws IndexOutOfBoundsException
     */
    public int getLeft(int index) throws IndexOutOfBoundsException {
        if(index > getHeapSize()-1 || index < 1){
            throw new IndexOutOfBoundsException("getLeft(): index must be in bounds of ArrayList");
        }else{
            int leftIndex = (int) Math.floor(index*2);
            return leftIndex >= getHeapSize() ? null : leftIndex;
        }
    }

    /**
     * returns the location (index) of the 
     * right child of the element stored at index
     * @param index the current index
     * @return the index of the right child
     * @precondition index must be in bounds of ArrayList heap, index < lastIndex and index >= 1
     * @throws IndexOutOfBoundsException
     */
    public int getRight(int index) throws IndexOutOfBoundsException {
        if(index > getHeapSize()-1 || index < 1){
            throw new IndexOutOfBoundsException("getRight(): index must be in bounds of ArrayList");
        }else {
            int rightIndex = (int) Math.floor((index*2)+1);
            return rightIndex >= getHeapSize() ? null : rightIndex;
        }
    }

    /**
     * returns the heap size (current number of elements)
     * @return the size of the heap
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * Gets the element at the specified index
     * @param index at which to access
     * @return the element at index
     * @precondition index must be in bounds of ArrayList heap, index < lastIndex and index >= 1
     * @throws IndexOutOfBoundsException
     */
    public T getElement(int index) throws IndexOutOfBoundsException {
        if(index > getHeapSize()-1 || index < 1){
            throw new IndexOutOfBoundsException("getElement(): index must be in bounds of ArrayList");
        }else{
            return heap.get(index);
        }
    }

    /**Additional Operations*/

    /**
     * Creates a String of all elements in the heap
     */
    @Override public String toString(){
        StringBuilder out = new StringBuilder();
        for(int i = 1; i < heap.size()-1; i++){
            out.append(heap.get(i));
            out.append(' ');
        }
        out.append('\n');
        return out.toString();
    }

    /**
     * Uses the heap sort algorithm to
     * sort the heap into ascending order
     * Calls helper method heapify
     * @return an ArrayList of sorted elements
     * @postcondition heap remains a valid heap
     */
    public ArrayList<T> sort(Comparator<T> comparator) {
        int size = getHeapSize();
        for(int i = getHeapSize(); i >= 2; i++){
            swap(heap, 1, i);
            heapSize--;
            heapify(1, comparator);
        }
        heapSize = size;
        return heap;
    }

}