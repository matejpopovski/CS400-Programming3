public class book implements IBook{
    private String title;
    private String Authors;
    private String ISBN13;
    book(String title, String Authors, String ISBN13){
        this.title = title;
        this.Authors = Authors;
        this.ISBN13 = ISBN13;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthors() {
        return Authors;
    }

    @Override
    public String getISBN13() {
        return ISBN13;
    }
}