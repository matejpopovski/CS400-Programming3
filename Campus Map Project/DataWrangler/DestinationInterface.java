// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

/**
 * This is an interface for the destination class. It will represent an Object that contains a set of attributes 
 * such as name, cost and time. This class also contains method that will compare two costs of a destiantion.  
 */
public interface DestinationInterface extends Comparable<DestinationInterface> {

	

	public int compareTo(DestinationInterface otherDestination);

	public String getDest();

	public String getName();

	public Integer getCost();
  
}
