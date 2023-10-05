// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

class Book1 implements IBook {
	@Override
	public String getTitle() {
		return "Title 1";
	}

	@Override
	public String getAuthors() {
		return "Author 1";
	}

	@Override
	public String getISBN13() {
		//return "9780747532743";
		return "1234567890123";
	}
}
