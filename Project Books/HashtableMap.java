import java.util.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This is a class that has the role of a Hashtable and all its methods. It is
 * using a hash function to compute the index, into linked list array. It is
 * mapping the keys to the values. During lookup the key is hashed and the
 * resulting hash indicates where the corresponding value is stored.
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	int capacity;
	int size;
	LinkedList<KeyValue<KeyType, ValueType>>[] keyValuePairs;

	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		this.capacity = capacity;
		size = 0;
		keyValuePairs = new LinkedList[this.capacity];
	}

	@SuppressWarnings("unchecked")
	public HashtableMap() {
		this.capacity = 15;
		size = 0;
		keyValuePairs = new LinkedList[this.capacity];
	}

	/**
	 * Inserts a new (key, value) pair into the map if the map does not contain a
	 * value mapped to key yet.
	 *
	 * @param key   the key of the (key, value) pair to store
	 * @param value the value that the key will map to
	 * @return true if the (key, value) pair was inserted into the map, false if a
	 *         mapping for key already exists and the new (key, value) pair could
	 *         not be inserted
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

		int index = Math.abs(key.hashCode()) % this.capacity;
		// Return false if the key is already stored in the hashtable
		if (keyValuePairs[index] != null) {
			for (int i = 0; i < keyValuePairs[index].size(); i++) {
				if ((keyValuePairs[index].get(i)).getKey().equals(key)) {
					return false;
				}
			}
		}
		// Return false if the key passed is null

		// Create new linked list to store the key-value pair
		if (keyValuePairs[index] == null) {
			keyValuePairs[index] = new LinkedList<KeyValue<KeyType, ValueType>>();
		}

		// Add the new key-value pair to the hastable and increment the size of
		// hashtable
		keyValuePairs[index].add(new KeyValue<KeyType, ValueType>(key, value));
		size++;

		// Dynamically grow hastable by doubling its capacity and rehashing, when load
		// factor >= 70%
		if (size >= 0.7 * (this.capacity)) {

			keyValuePairs = this.doubleHashtable(keyValuePairs);
		}
		return true;
	}

	// Private helper method that doubles the hashtable
	private LinkedList<KeyValue<KeyType, ValueType>>[] doubleHashtable(
			LinkedList<KeyValue<KeyType, ValueType>>[] keyValuePairs) {
		// Create new hastable with double capacity
		this.capacity = 2 * this.capacity; // doubles the capacity
		@SuppressWarnings("unchecked")
		LinkedList<KeyValue<KeyType, ValueType>>[] temp = new LinkedList[this.capacity];
		for (int i = 0; i < keyValuePairs.length; i++) {
			if (keyValuePairs[i] != null) {
				for (int j = 0; j < keyValuePairs[i].size(); j++) {
					int newIndex = Math.abs(keyValuePairs[i].get(j).getKey().hashCode()) % this.capacity;
					if (temp[newIndex] == null) {
						temp[newIndex] = new LinkedList<KeyValue<KeyType, ValueType>>();
					}
					temp[newIndex].add(keyValuePairs[i].get(j));
				}
			}
		}
		keyValuePairs = temp;
		return keyValuePairs;
	}

	/**
	 * Returns the value mapped to a key if the map contains such a mapping.
	 *
	 * @param key the key for which to look up the value
	 * @return the value mapped to the key
	 * @throws NoSuchElementException if the map does not contain a mapping for the
	 *                                key
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (this.capacity == 0) {
			return null;
		}

		if (keyValuePairs[Math.abs(key.hashCode()) % this.capacity] != null) {

			for (int i = 0; i < keyValuePairs[Math.abs(key.hashCode()) % this.capacity].size(); i++) {
				if (keyValuePairs[Math.abs(key.hashCode()) % this.capacity].get(i).getKey().equals(key)) {
					ValueType valueReturn = keyValuePairs[Math.abs(key.hashCode()) % this.capacity].get(i).getValue();
					return valueReturn;
				}
			}
		}
		throw new NoSuchElementException("This key does not exist!");
	}

	/**
	 * Checks if a key is stored in the map.
	 *
	 * @param key the key to check for
	 * @return true if the key is stored (mapped to a value) by the map and false
	 *         otherwise
	 */
	@Override
	public boolean containsKey(KeyType key) {
		// TODO Auto-generated method stub
		if (keyValuePairs[Math.abs(key.hashCode()) % this.capacity] != null) {
			for (int i = 0; i < keyValuePairs[Math.abs(key.hashCode()) % this.capacity].size(); i++) {
				if (keyValuePairs[Math.abs(key.hashCode()) % this.capacity].get(i).getKey().equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes a key and its value from the map.
	 *
	 * @param key the key for the (key, value) pair to remove
	 * @return the value for the (key, value) pair that was removed, or null if the
	 *         map did not contain a mapping for key
	 */
	@Override
	public ValueType remove(KeyType key) {
		if (!(this.containsKey(key))) {
			return null;
		}
		// TODO Auto-generated method stub
		for (int i = 0; i < keyValuePairs[Math.abs(key.hashCode()) % this.capacity].size(); i++) {
			if (keyValuePairs[Math.abs(key.hashCode()) % this.capacity].get(i).getKey().equals(key)) {
				size--;
				ValueType valueRemoved = keyValuePairs[Math.abs(key.hashCode()) % this.capacity].get(i).getValue();
				keyValuePairs[Math.abs(key.hashCode()) % this.capacity].remove(i);
				return valueRemoved;
			}
		}
		return null;
	}

	/**
	 * Returns the number of (key, value) pairs stored in the map.
	 *
	 * @return the number of (key, value) pairs stored in the map
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	/**
	 * Removes all (key, value) pairs from the map.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < keyValuePairs.length; i++) {
			keyValuePairs[i] = null;
		}
		this.size = 0;
	}


    class Book1 implements IBook {
        @Override
          public String getTitle() {
                return "HarryPotter 1";
            }

            @Override
            public String getAuthors() {
                return "Rowling";
            }

            @Override
            public String getISBN13() {
                return "9780747532743";
            }
    }

    class Book2 implements IBook {
        @Override
            public String getTitle() {
                return "HarryPotter 2";
            }

            @Override
            public String getAuthors() {
                return "Rowling";
            }

            @Override
            public String getISBN13() {
                return "9780439064873";
            }
    }

    public Iterator iterator(){
        return Arrays.asList(new IBook[] {new Book1(), new Book2()}).iterator();

    }
}