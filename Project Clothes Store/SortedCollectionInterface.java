// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import java.util.Iterator;

public interface SortedCollectionInterface<T extends Comparable<T>>{

    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public boolean contains(T data);

    public int size();

    public boolean isEmpty();

    // DA SE SMENAT KOMENTARITE

    /**
     * Removes the node with the data for which compareTo() returns 0.
     * @param data the object to be removed
     * @return the object being removed, or null if nothing was removed
     */
    public T remove(T data);

    /**
     * Gives an iterator that is in level order instead of in order
     * @return Iterator that has all the elements in the tree in level order
     */
    public Iterator<T> levelOrderIterator();
}