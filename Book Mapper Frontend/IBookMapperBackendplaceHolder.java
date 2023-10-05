import java.util.List;
import java.util.ArrayList;

public class IBookMapperBackendplaceHolder implements IBookMapperBackend{
    String AuthorName;
    List<IBook>lists;

    IBookMapperBackendplaceHolder() {
        lists = new ArrayList<IBook>();
        lists.add(new book("The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)", "Douglas Adams", "9780330491198"));
        lists.add(new book("A Portrait of the Artist as a Young Man", "James Joyce/Jim Norton", "9789626343661"));
        lists.add(new book("Dubliners", "James Joyce/Frank McCourt/Donal Donnelly/Ciaran Hinds/Colm Meaney/Malachy McCourt", "9780060789565"));
        lists.add(new book("The End of Nana Sahib: The Steam House (Extraordinary Voyages  #20)", "Jules Verne/Agnes D. Kingston","9781410103277"));
    }

    @Override
    public void addBook(IBook book) {
        lists.add(book);
    }

    @Override
    public int getNumberOfBooks() {
        return 4;
    }

    @Override
    public void setAuthorFilter(String filterBy) {
        AuthorName = filterBy;
    }

    @Override
    public String getAuthorFilter() {

        return AuthorName;
    }

    @Override
    public void resetAuthorFilter() {
        AuthorName = null;
    }

    @Override
    public List<IBook> searchByTitleWord(String word) {
        List<IBook>res = new ArrayList<>();
        if (word.equals("abc")) {
            return lists;
        } else {
            return res;
        }
    }

    @Override
    public IBook getByISBN(String ISBN) {
        if (ISBN.equals("1234567890123")) {
            return lists.get(0);
        } else {
            return null;
        }
    }
}