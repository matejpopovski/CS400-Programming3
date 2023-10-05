import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class BookLoader implements IBookLoader{

  @Override
  public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
    // TODO Auto-generated method stub
    return Arrays.asList(new IBook[] {new Book1(), new Book2()});


  }

}
