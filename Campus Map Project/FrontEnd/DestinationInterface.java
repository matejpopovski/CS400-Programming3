public interface DestinationInterface extends Comparable<DestinationInterface> {
  public String getName();

  public Integer getCost();
  
  public String getDest();

  // from super interface Comparable
  public int compareTo(DestinationInterface otherDestination);


}
