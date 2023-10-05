import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.DataFormatException;

public interface CampusMapDataReaderInterface {

  public void readDataSet()
      throws FileNotFoundException, IOException, DataFormatException;
}

