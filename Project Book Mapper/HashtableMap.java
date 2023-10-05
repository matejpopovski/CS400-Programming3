import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A Hashtable's functions and role are played by this class. The index into the linked list array
 * is calculated using a hash function. The keys and values are being mapped. When performing a
 * lookup, the key is hashed, and the resulting hash identifies the location of the associated
 * value.
 * 
 * @author Matej
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashtableMap<KeyType, ValueType> implements IterableMapADT<KeyType, ValueType> {
  int capacity;
  int size;
  LinkedList<Node<KeyType, ValueType>>[] list1;

  /**
   * this constructor initializes all the variables
   * 
   * @param capacity
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    size = 0;
    list1 = new LinkedList[this.capacity];
  }

  /**
   * constructor initializes variables and puts the capacity equal to 15
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    this.capacity = 15;
    size = 0;
    list1 = new LinkedList[this.capacity];
  }

  /**
   * adds a new (key, value) pair into the hashmap.
   * 
   * @param key   the key of the (key, value) pair
   * @param value the value that the key will correspond to
   * @return true if the (key, value) pair was added in the map correctly, false if a mapping for
   *         key is already present or the new (key, value) pair could not be added
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // TODO Auto-generated method stub
    if (this.capacity == 0) {
      return false;
    }

    if (key == null) {
      return false;
    }
    // getting the key's hashcode and dividing it by the capacity
    int keyHash = Math.abs(key.hashCode()) % this.capacity;
    // Return false if the key is already stored in the hashtable
    if (list1[keyHash] != null) {
      for (int i = 0; i < list1[keyHash].size(); i++) {
        if ((list1[keyHash].get(i)).getKey().equals(key)) {
          // returning false if key already exists
          return false;
        }
      }
    }
    // Return false if the key passed is null


    if (list1[keyHash] == null) {
      list1[keyHash] = new LinkedList<Node<KeyType, ValueType>>();
    }

    // adding the key to the map and increasing the size of it by 1
    list1[keyHash].add(new Node<KeyType, ValueType>(key, value));
    size++;


    if (size >= 0.7 * (this.capacity)) {

      list1 = this.increaseSize(list1);
    }
    return true;
  }

  /**
   * Private helper method that doubles the hashtable
   */
  private LinkedList<Node<KeyType, ValueType>>[] increaseSize(
      LinkedList<Node<KeyType, ValueType>>[] list1) {
    // Create new hastable with double capacity
    this.capacity = 2 * this.capacity;
    @SuppressWarnings("unchecked")
    // creating a new linkedlist that stores all the prev and new (double capacity) values
    LinkedList<Node<KeyType, ValueType>>[] helper = new LinkedList[this.capacity];
    for (int i = 0; i < list1.length; i++) {
      if (list1[i] != null) {
        for (int j = 0; j < list1[i].size(); j++) {
          int index = Math.abs(list1[i].get(j).getKey().hashCode()) % this.capacity;
          if (helper[index] == null) {
            helper[index] = new LinkedList<Node<KeyType, ValueType>>();
          }
          helper[index].add(list1[i].get(j));
        }
      }
    }
    list1 = helper;
    return list1;
  }

  /**
   * Returns the value associated with a key if the mapping is present at the first place.
   * 
   * @param key the key in the (key,value) pair
   * @return the value corresponding to the key
   * @throws NoSuchElementException if the mapping for the key does not exist.
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // TODO Auto-generated method stub
    if (this.capacity == 0) {
      return null;
    }
    // getting the hashcode of the key
    if (list1[Math.abs(key.hashCode()) % this.capacity] != null) {
      // checking if the given value matches with an already existing value
      // if yes, returning it
      for (int i = 0; i < list1[Math.abs(key.hashCode()) % this.capacity].size(); i++) {
        if (list1[Math.abs(key.hashCode()) % this.capacity].get(i).getKey().equals(key)) {
          ValueType valueToBeReturn =
              list1[Math.abs(key.hashCode()) % this.capacity].get(i).getValue();
          return valueToBeReturn;
        }
      }
    }
    throw new NoSuchElementException("key does not exist");
  }

  /**
   * Removes a key and the corresponding value from the map.
   * 
   * @param key the key for the (key, value) pair
   * @return the value for the (key, value) pair to be removed when given the key.
   */
  @Override
  public ValueType remove(KeyType key) {
    // checking if there is any key in the table using the containsKey function
    if (!(this.containsKey(key))) {
      return null;
    }
    // TODO Auto-generated method stub
    for (int i = 0; i < list1[Math.abs(key.hashCode()) % this.capacity].size(); i++) {
      // dereasing the size 1 if there is any key that exists to be removed
      if (list1[Math.abs(key.hashCode()) % this.capacity].get(i).getKey().equals(key)) {
        size--;
        ValueType valueToBeRemoved =
            list1[Math.abs(key.hashCode()) % this.capacity].get(i).getValue();
        // finding a matching value and returning it
        list1[Math.abs(key.hashCode()) % this.capacity].remove(i);
        return valueToBeRemoved;
      }
    }
    return null;
  }



  /**
   * This class checks if a key is contained in the map.
   * 
   * @param key the key to lookup for
   * @return true if the key is stored in the map and falseif not
   */
  @Override
  public boolean containsKey(KeyType key) {
    // TODO Auto-generated method stub
    if (list1[Math.abs(key.hashCode()) % this.capacity] != null) {
      for (int i = 0; i < list1[Math.abs(key.hashCode()) % this.capacity].size(); i++) {
        // checking if there exists a key matching with the table keys
        if (list1[Math.abs(key.hashCode()) % this.capacity].get(i).getKey().equals(key)) {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * checks the number of pairs present in the map.
   * 
   * @return returns the number of pairs present in the map
   */
  @Override
  public int size() {
    // TODO Auto-generated method stub
    // returning the size of the map
    return this.size;
  }

  /**
   * deletes all (key, value) pairs from the map by making them null.
   */
  @Override
  public void clear() {
    // TODO Auto-generated method stub
    for (int i = 0; i < list1.length; i++) {
      // setting all values to null to clear all key value pairs
      list1[i] = null;
    }
    this.size = 0;
  }

  @Override
  public Iterator<ValueType> iterator() {
    // TODO Auto-generated method stub
    return null;
  }
}


/**
 * This class creates a node containing a key value pair
 * 
 * @author Matej
 *
 * @param <KeyType>
 * @param <ValueType>
 */
class Node<KeyType, ValueType> {

  private KeyType key;
  private ValueType value;

  public Node(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  public KeyType getKey() {
    return this.key;
  }

  public ValueType getValue() {
    return this.value;
  }

}
