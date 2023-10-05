// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * This is an interface for the class that represents the Campus Map data read. It will work with a set of 
 * destinations. It will create an object that will be based on each edge. Later it will add the destinations 
 * in an ArrayList.
 */
public interface CampusMapDataReaderInterface {

  public List<Destination> readDataSet()
      throws FileNotFoundException, IOException, DataFormatException;
}

