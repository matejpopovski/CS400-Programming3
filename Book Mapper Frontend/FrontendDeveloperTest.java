import java.util.Scanner;

public class FrontendDeveloperTest {

    //Test runCommandLoop with option 4.
    public static boolean test1() {
        TextUITester tester = new TextUITester("4\n");
        Scanner scan = new Scanner(System.in);
        IBookMapperBackend myback = new IBookMapperBackendplaceHolder();
        IISBNValidator myval = new IISBNValidatorplaceHolder();
        IBookMapperFrontend myfront = new IBookMapperFront(scan, myback, myval);
        myfront.runCommandLoop();
        scan.close();
        String output = tester.checkOutput();
        if(output.equals("Welcome to the Book Mapper Application!\n" +
                "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "Goodbye!\n"))
            return true;
        return false;
    }

    //Test helper functions.
    public static boolean test2() {

        TextUITester tester = new TextUITester("\n1234567890123\n\nabc\n");
        Scanner scan = new Scanner(System.in);
        IBookMapperBackend myback = new IBookMapperBackendplaceHolder();
        IISBNValidator myval = new IISBNValidatorplaceHolder();
        IBookMapperFrontend myfront = new IBookMapperFront(scan, myback, myval);
        myfront.displayMainMenu();
        // The isbn returns null.
        myfront.isbnLookup();
        // The isbn returned a book.
        myfront.isbnLookup();
        // The title search returns 0 book.
        myfront.titleSearch();
        // The title search returns 4 books.
        myfront.titleSearch();
        scan.close();
        String output = tester.checkOutput();

        if(output.equals("You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Lookup ISBN Menu:\n" +
                "          Enter ISBN to look up:\n" +
                "You are in the Lookup ISBN Menu:\n" +
                "          Enter ISBN to look up:\n" +
                "1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198\n" +
                "\n" +
                "You are in the Search for Title Word Menu: \n" +
                "         Enter a word to search for in book titles (empty for all books): \n" +
                "Matches (any author) 0 of 4\n" +
                "Matches (any author) 0 of 4\n" +
                "You are in the Search for Title Word Menu: \n" +
                "         Enter a word to search for in book titles (empty for all books): \n" +
                "Matches (any author) 4 of 4\n" +
                "0. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198\n" +
                "\n" +
                "1. \"A Portrait of the Artist as a Young Man\" by James Joyce/Jim Norton, ISBN: 9789626343661\n" +
                "\n" +
                "2. \"Dubliners\" by James Joyce/Frank McCourt/Donal Donnelly/Ciaran Hinds/Colm Meaney/Malachy McCourt, ISBN: 9780060789565\n" +
                "\n" +
                "3. \"The End of Nana Sahib: The Steam House (Extraordinary Voyages  #20)\" by Jules Verne/Agnes D. Kingston, ISBN: 9781410103277\n" +
                "\n" +
                "Matches (any author) 4 of 4\n"))
            return true;
        return false;
    }

    //Test runCommandLoop with option 3.
    public static boolean test3() {
        TextUITester tester = new TextUITester("3\nJames Joyce\n3\n\n4\n");
        Scanner scan = new Scanner(System.in);
        IBookMapperBackend myback = new IBookMapperBackendplaceHolder();
        IISBNValidator myval = new IISBNValidatorplaceHolder();
        IBookMapperFrontend myfront = new IBookMapperFront(scan, myback, myval);
        myfront.runCommandLoop();
        scan.close();
        String output = tester.checkOutput();
        if(output.equals("Welcome to the Book Mapper Application!\n" +
                "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Set Author Filter Menu:\n" +
                "          Author name must currently contain: none\n" +
                "Enter a new string for author names to contain (empty for any): You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Set Author Filter Menu:\n" +
                "          Author name must currently contain: James Joyce\n" +
                "Enter a new string for author names to contain (empty for any): You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "Goodbye!\n"))
            return true;
        return false;
    }

    // Test runCommandLoop with option 1.
    public static boolean test4() {
        TextUITester tester = new TextUITester("1\n\n1\n1234567890123\n4\n");
        Scanner scan = new Scanner(System.in);
        IBookMapperBackend myback = new IBookMapperBackendplaceHolder();
        IISBNValidator myval = new IISBNValidatorplaceHolder();
        IBookMapperFrontend myfront = new IBookMapperFront(scan, myback, myval);
        myfront.runCommandLoop();
        scan.close();
        String output = tester.checkOutput();
        if(output.equals("Welcome to the Book Mapper Application!\n" +
                "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Lookup ISBN Menu:\n" +
                "          Enter ISBN to look up:\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Lookup ISBN Menu:\n" +
                "          Enter ISBN to look up:\n" +
                "1. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "Goodbye!\n"))
            return true;
        return false;
    }

    // Test runCommandLoop with option 2.
    public static boolean test5() {
        TextUITester tester = new TextUITester("2\n\n2\nabc\n4\n");
        Scanner scan = new Scanner(System.in);
        IBookMapperBackend myback = new IBookMapperBackendplaceHolder();
        IISBNValidator myval = new IISBNValidatorplaceHolder();
        IBookMapperFrontend myfront = new IBookMapperFront(scan, myback, myval);
        myfront.runCommandLoop();
        scan.close();
        String output = tester.checkOutput();
        if(output.equals("Welcome to the Book Mapper Application!\n" +
                "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Search for Title Word Menu: \n" +
                "         Enter a word to search for in book titles (empty for all books): \n" +
                "Matches (any author) 0 of 4\n" +
                "Matches (any author) 0 of 4\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "You are in the Search for Title Word Menu: \n" +
                "         Enter a word to search for in book titles (empty for all books): \n" +
                "Matches (any author) 4 of 4\n" +
                "0. \"The Hitchhiker's Guide to the Galaxy (Hitchhiker's Guide to the Galaxy  #1)\" by Douglas Adams, ISBN: 9780330491198\n" +
                "\n" +
                "1. \"A Portrait of the Artist as a Young Man\" by James Joyce/Jim Norton, ISBN: 9789626343661\n" +
                "\n" +
                "2. \"Dubliners\" by James Joyce/Frank McCourt/Donal Donnelly/Ciaran Hinds/Colm Meaney/Malachy McCourt, ISBN: 9780060789565\n" +
                "\n" +
                "3. \"The End of Nana Sahib: The Steam House (Extraordinary Voyages  #20)\" by Jules Verne/Agnes D. Kingston, ISBN: 9781410103277\n" +
                "\n" +
                "Matches (any author) 4 of 4\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "Goodbye!\n"))
            return true;
        return false;
    }
    public static void main(String[] args){
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
    }
}