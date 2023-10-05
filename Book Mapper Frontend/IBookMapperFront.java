import java.util.List;
import java.util.Scanner;
public class IBookMapperFront implements IBookMapperFrontend {

    private Scanner userInputScanner;
    private IBookMapperBackend backend;
    private IISBNValidator validator;
    IBookMapperFront(Scanner userInputScanner, IBookMapperBackend backend, IISBNValidator validator){
        this.userInputScanner = userInputScanner;
        this.backend = backend;
        this.validator = validator;
    }
    @Override
    public void runCommandLoop() {
        System.out.println("Welcome to the Book Mapper Application!");
        System.out.println("x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x");
        System.out.println();
        while (true) {
            displayMainMenu();
            String choice = userInputScanner.nextLine();
            if (choice.equals("1")) {
                isbnLookup();
            } else if (choice.equals("3")) {
                System.out.println("You are in the Set Author Filter Menu:");
                System.out.print("          Author name must currently contain: ");
                String filter = backend.getAuthorFilter();
                if (filter == null) {
                    System.out.println("none");
                } else {
                    System.out.println(filter);
                }
                System.out.print("Enter a new string for author names to contain (empty for any): ");
                String name = userInputScanner.nextLine();
                if (name.isEmpty()) {
                    backend.resetAuthorFilter();
                } else {
                    backend.setAuthorFilter(name);
                }
            } else if (choice.equals("2")) {
                titleSearch();
            } else if (choice.equals("4")) {
                System.out.println("Goodbye!");
                break;
            }
        }

    }

    @Override
    public void displayMainMenu() {
        System.out.println("You are in the Main Menu:");
        System.out.println("          1) Lookup ISBN");
        System.out.println("          2) Search by Title Word");
        System.out.println("          3) Set Author Name Filter");
        System.out.println("          4) Exit Application");
    }

    @Override
    public void displayBooks(List<IBook> books) {
        for (int i = 0; i < books.size(); i++) {
            System.out.print(i);
            System.out.print(". \"");
            System.out.print(books.get(i).getTitle());
            System.out.print("\" by ");
            System.out.print(books.get(i).getAuthors());
            System.out.print(", ISBN: ");
            System.out.println(books.get(i).getISBN13());
            System.out.println();
        }
    }

    @Override
    public void isbnLookup() {
        System.out.println("You are in the Lookup ISBN Menu:");
        System.out.println("          Enter ISBN to look up:");
        String isbn = userInputScanner.nextLine();
        if(validator.validate(isbn)){
            IBook mybook = backend.getByISBN(isbn);
            System.out.print("1. \"");
            System.out.print(mybook.getTitle());
            System.out.print("\" by ");
            System.out.print(mybook.getAuthors());
            System.out.print(", ISBN: ");
            System.out.println(mybook.getISBN13());
            System.out.println();
        }
    }

    @Override
    public void titleSearch() {
        System.out.println("You are in the Search for Title Word Menu: ");
        String name = backend.getAuthorFilter();
        System.out.println("         Enter a word to search for in book titles (empty for all books): ");
        String bookName = userInputScanner.nextLine();
        List<IBook>res = backend.searchByTitleWord(bookName);
        if(name == null){
            System.out.println("Matches (any author) " + res.size() +" of "+backend.getNumberOfBooks());
        }else{
            System.out.println("Matches (author filter: " + name+ ") " + res.size()+" of "+backend.getNumberOfBooks());
        }
        displayBooks(res);
        if(name == null){
            System.out.println("Matches (any author) " + res.size() +" of "+backend.getNumberOfBooks());
        }else{
            System.out.println("Matches (author filter: " + name+ ") " + res.size()+" of "+backend.getNumberOfBooks());
        }
    }
}