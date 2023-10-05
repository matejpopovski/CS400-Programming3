import java.util.NoSuchElementException;

/**
 * This class is used to test the methods implemented in Hashtable.java
 */
public class HashtableMapTests {
  /**
   * Tests the put() method defined in Hashtable
   *
   * @return true if tests pass
   */
  public static boolean test1() {
    // initializes hashtable with capacity 10
    HashtableMap<Integer, Integer> tester = new HashtableMap<Integer, Integer>(7);
    // puts various keys and values in hashtable
    try {
      if (!tester.put(10, 20)) {
        return false;
      }
      if (!tester.put(100, 200)) {
        return false;
      }
      if (!tester.put(20, 40)) {
        return false;
      }
      if (!tester.put(200, 400)) {
        return false;
      }
      // puts a value using null key in the hashtable
      if (tester.put(null, 10)) {
        return false;
      }

      if (tester.put(10, 20)) {
        return false;
      }
    } catch (NoSuchElementException e) {

    }

    // checks that all the values were added
    if (tester.size() != 4) {
      return false;
    }
    return true;
  }

  /**
   * Tests the get() method defined in Hashtable class
   *
   * @return true if tests pass
   */
  public static boolean test2() {
    // creates a hashtable
    HashtableMap<Integer, String> tester = new HashtableMap<Integer, String>(6);
    boolean tempo = true;

    try {
      if (tester.get(6) != null) {
        tempo = false;
      }
    } catch (NoSuchElementException e) {
      tempo = true;
    }
    // putting values and keys in hashtable
    if (!tester.put(7, "Matej")) {
      tempo = false;
    }

    if (!tester.put(2, "Popovski")) {
      tempo = false;
    }
    // checks for the already existing keys that have a mapping in the hashtable
    if (!(tester.get(7).equals("Matej"))) {
      tempo = false;
    }
    if (!(tester.get(2).equals("Popovski"))) {
      tempo = false;
    }
    // checks for a key that does not exist in the hashtable
    try {
      if (tester.get(4) != null) {
        tempo = false;
      }
    } catch (NoSuchElementException e) {
      tempo = true;
    }
    return tempo;
  }

  /**
   * Tests the remove() method defined in Hashtable class
   *
   * @return true if tests pass
   */
  public static boolean test3() {
    // creates a hashtable
    HashtableMap<Integer, Integer> tester = new HashtableMap<Integer, Integer>(6);
    boolean tempo = true;

    if (tester.remove(2) != null) {
      tempo = false;
    }
    // putting values and keys in hashtable
    tester.put(1, 2);
    tester.put(2, 3);

    // removes a value key from hashtable
    tester.remove(2);

    // checks the get() method for the key and value that were removed
    try {
      if (tester.get(2) != null) {
        tempo = false;
      }
    } catch (NoSuchElementException e) {
      tempo = true;
    }
    return tempo;
  }

  /**
   * Tests the containsKey() method defined in Hashtable
   *
   * @return true
   */
  public static boolean test4() {
    // creates a hashtable with capacity 6
    HashtableMap<Integer, Integer> tester = new HashtableMap<Integer, Integer>(7);
    // puts keys and values in the hashtable
    tester.put(1, 2);
    tester.put(2, 3);
    tester.put(3, 4);
    tester.put(4, 5);
    // checks key and value already exits
    if (!tester.containsKey(4)) {
      return false;
    }

    if (tester.containsKey(5)) {
      return false;
    }
    return true;
  }

  /**
   * Tests the size() and clear() methods defined in Hashtable class with a Hashtable of capacity 6
   *
   * @return true if tests pass
   */
  public static boolean test5() {
    // creates a hashtable
    HashtableMap<Integer, Integer> tester = new HashtableMap<Integer, Integer>(7);
    // putting keys and values in the hashtable
    tester.put(1, 2);
    tester.put(2, 3);
    tester.put(3, 4);
    tester.put(4, 5);


    tester.clear();
    // checks if the hashtable is cleared
    if (tester.size() != 0) {
      return false;
    }
    return true;
  }

  /**
   * Main method
   *
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.println("test1(): " + test1());
    System.out.println("test2(): " + test2());
    System.out.println("test3(): " + test3());
    System.out.println("test4(): " + test4());
    System.out.println("test5(): " + test5());
  }
}
