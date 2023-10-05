
// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

class BookLoader implements IBookLoader {

	@Override
	public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {
		
		List<IBook> books = new ArrayList<IBook>();
        books.add(new Book1());
        books.add(new Book2());
        return books;
		
		//return null;
	}
	
}




