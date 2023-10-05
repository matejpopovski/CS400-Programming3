import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper implements IBookMapperBackend {

    public static void main(String[] args) throws FileNotFoundException {
        // load the books from the data file
        List<IBook> books = (new BookLoader()).loadBooks("books.csv");
        // instantiate the backend
        IBookMapperBackend backend = new BookMapperBackend();
        // add all the books to the backend
        for (IBook book : books) {
            backend.addBook(book);
        }
        // instantiate the isbn validator (to be used by the front end)
        ISBNValidator isbnValidator = new ISBNValidator();
        // instantiate the scanner for user input (to be used by the front end)
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        BookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);
        // start the input loop of the front end
        frontend.runCommandLoop();
    }

    @Override
    public void addBook(IBook book) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public int getNumberOfBooks() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public void setAuthorFilter(String filterBy) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public String getAuthorFilter() {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public void resetAuthorFilter() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public IBook getByISBN(String ISBN) {
      // TODO Auto-generated method stub
      return null;
    }
    
}