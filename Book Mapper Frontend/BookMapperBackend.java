
// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class BookMapperBackend implements IBookMapperBackend {

	HashtableMap<Object, IBook> hashtableOfBooks = new HashtableMap<>();

	protected String authorFilter;

	@Override
	public void addBook(IBook book) {
		hashtableOfBooks.put(book.getISBN13(), book);
	}

	@Override
	public int getNumberOfBooks() {
		return hashtableOfBooks.size();
	}

	@Override
	public void setAuthorFilter(String filterBy) {
		this.authorFilter = filterBy;
	}

	@Override
	public String getAuthorFilter() {
		return this.authorFilter;
	}

	@Override
	public void resetAuthorFilter() {
		this.authorFilter = null;
	}

	@Override
	public List<IBook> searchByTitleWord(String word) {

		List<IBook> bookList = new ArrayList<IBook>();
		Iterator iterator = hashtableOfBooks.iterator();

		while (iterator.hasNext()) {

			IBook b = (IBook) iterator.next();
			if (getAuthorFilter() == null) {
				if (b.getTitle().contains(word)) {
					bookList.add(b);
				}
			} else {
				if (b.getTitle().contains(word) && b.getAuthors().contains(authorFilter)) {
					bookList.add(b);
				}
			}

		}
		return bookList;
		
	}

	@Override
	public IBook getByISBN(String ISBN) {
		if (hashtableOfBooks.containsKey(ISBN)) {
			return hashtableOfBooks.get(ISBN);
		}
		return null;

	}

}