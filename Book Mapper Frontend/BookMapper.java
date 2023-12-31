
// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

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
		//if (!(books == null)) {
			for (IBook book : books) {

				backend.addBook(book);

			}
		//}

		// instantiate the isbn validator (to be used by the front end)
		IISBNValidator isbnValidator = new IISBNValidatorplaceHolder();

		// instantiate the scanner for user input (to be used by the front end)
		Scanner userInputScanner = new Scanner(System.in);

		// instantiate the front end and pass references to the scanner, backend, and
		// isbn validator to it
		IBookMapperFrontend frontend = new IBookMapperFront(userInputScanner, backend, isbnValidator);

		// start the input loop of the front end
		frontend.runCommandLoop();
	}

}
