import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;

/**
 * Class with main method to run the book mapper app.
 */
public class BookMapper {

    public static void main(String[] args) throws FileNotFoundException {
        IBookLoader bookLoader = new BookLoader();

        // load the books from the data file
        List<IBook> books = bookLoader.loadBooks("books.csv");

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
        IBookMapperFrontend frontend = new BookMapperFrontend(userInputScanner, backend, isbnValidator);

        // start the input loop of the front end
        frontend.runCommandLoop();
    }

}

class BookMapperFrontend implements IBookMapperFrontend{

    public BookMapperFrontend(){

    }
    public BookMapperFrontend(Scanner s, IBookMapperBackend bb, IISBNValidator is){

    }
    @Override
    public void runCommandLoop() {

    }

    @Override
    public void displayMainMenu() {

    }

    @Override
    public void displayBooks(List<IBook> books) {

    }

    @Override
    public void isbnLookup() {

    }

    @Override
    public void titleSearch() {

    }
}

class BookLoader implements IBookLoader{

    @Override
    public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
        return null;
    }
}

class ISBNValidator implements IISBNValidator{
    @Override
    public boolean validate(String isbn13) {
        return false;
    }
}

