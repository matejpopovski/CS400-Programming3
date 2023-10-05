// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

class Book2 implements IBook {
	@Override
	public String getTitle() {
		//return "HarryPotter 2";
		return "Title 2";
	}

	@Override
	public String getAuthors() {
		//return "Rowling";
		return "Author 2";
	}

	@Override
	public String getISBN13() {
		return "1231231231231";
	}
}