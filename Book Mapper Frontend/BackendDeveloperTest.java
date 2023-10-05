
// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BackendDeveloperTest {

	static IBookMapperBackend backend = new BookMapperBackend();

	public static void tester() {
		if (test1()){System.out.println("Backend Individual Test 1: passed");}
		else{System.out.println("Backend Individual Test 1: failed");}
		if(test2()){System.out.println("Backend Individual Test 2: passed");}
		else{System.out.println("Backend Individual Test 2: failed");}
		if(test3()){System.out.println("Backend Individual Test 3: passed");}
		else{System.out.println("Backend Individual Test 3: failed");}
		if(test4()){System.out.println("Backend Individual Test 4: passed");}
		else{System.out.println("Backend Individual Test 4: failed");}
		if(test5()){System.out.println("Backend Individual Test 5: passed");}
		else{System.out.println("Backend Individual Test 5: failed");}

		if(test6()){System.out.println("Backend Partner (Frontend) Test 1: passed");}
		else{System.out.println("Backend Partner (Frontend) Test 1: failed");}
		if(test7()){System.out.println("Backend Partner (Frontend) Test 2: passed");}
		else{System.out.println("Backend Partner (Frontend) Test 2: failed");}

		if(test8()){System.out.println("Backend Integration Test 1: passed");}
		else{System.out.println("Backend Integration Test 1: failed");}
		if(test9()){System.out.println("Backend Integration Test 2: passed");}
		else{System.out.println("Backend Integration Test 2: failed");}

	}

	public static boolean test1() {

		if (backend.getAuthorFilter() == null) {
			backend.setAuthorFilter("Title");

			if (backend.getAuthorFilter() == "Title") {
				backend.resetAuthorFilter();

				if (backend.getAuthorFilter() == null) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean test2() {

		List<IBook> books = new ArrayList<IBook>();
		books.add(new Book1());
		books.add(new Book2());

		for (IBook book : books) {
			backend.addBook(book);
		}

		if (backend.getNumberOfBooks() == 2) {
			return true;
		}

		return false;
	}

	public static boolean test3() {
		try {
			List<IBook> books = new ArrayList<IBook>();
			books.add(new Book1());
			books.add(new Book2());

			for (IBook book : books) {
				backend.addBook(book);
			}
			List<IBook> lb = backend.searchByTitleWord("Title");
			if (lb.get(0).getISBN13() == "1234567890123" && lb.get(1).getISBN13() == "1231231231231") {
				return true;
			}
		} catch (NoSuchElementException e) {

		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
		return false;
	}

	public static boolean test4() {
		try {

			backend.setAuthorFilter("2");
			List<IBook> bookList = backend.searchByTitleWord("Title");

			if (bookList.get(0).getISBN13() == "1231231231231") {
				return true;
			}
		} catch (NoSuchElementException e) {

		} catch (Exception e) {

			 return false;
		}

		return false;
	}

	public static boolean test5() {
		backend.setAuthorFilter("123");

		List<IBook> bookList = backend.searchByTitleWord("Title");

		if (bookList.size() != 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		tester();
	}


  public static boolean test6() {
    try {
	    TextUITester testUI = new TextUITester("4\n");
	    Scanner sc = new Scanner(System.in);
	    IBookMapperBackend backend = new IBookMapperBackendplaceHolder();
	    IISBNValidator validator = new IISBNValidatorplaceHolder();
	    IBookMapperFrontend testFrontend = new IBookMapperFront(sc, backend, validator);
	    testFrontend.runCommandLoop();
	    String output = testUI.checkOutput();
    if(!output.equals("Welcome to the Book Mapper Application!\n" +
                "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
                "Goodbye!\n")) {
      return false;


    }else {
      return true;
    }
    }catch(Exception e) {
      System.out.println("Exception in test6");
      return false;
    }
  }


  public static boolean test7() {
    try {
    	TextUITester testUI = new TextUITester("\nabc\n");
	    Scanner sc = new Scanner(System.in);
	    IBookMapperBackend backend = new IBookMapperBackendplaceHolder();
	    IISBNValidator validator = new IISBNValidatorplaceHolder();
	    IBookMapperFrontend testFrontend = new IBookMapperFront(sc, backend, validator);
	    testFrontend.displayMainMenu();
        testFrontend.titleSearch();
        testFrontend.titleSearch();
        sc.close();
	    String output = testUI.checkOutput();

    if(!output.equals("You are in the Main Menu:\n" +
                "          1) Lookup ISBN\n" +
                "          2) Search by Title Word\n" +
                "          3) Set Author Name Filter\n" +
                "          4) Exit Application\n" +
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
                "Matches (any author) 4 of 4\n")) {
      return false;
    }
    return true;
    }catch(Exception e) {
      System.out.println("Exception in test7");
      return false;
    }
  }

  private static boolean test8() {

    IBookLoader bookLoader = new BookLoader();
    try {

      List<IBook> bookList = bookLoader.loadBooks("books.csv");

      IBookMapperBackend backend = new BookMapperBackend();
      for (IBook book : bookList) {
        backend.addBook(book);
      }
      if (bookList.size() != backend.getNumberOfBooks()) {
        return false;

      }
      if (backend.getByISBN("1111111111111") != null) {
        return false;
      }
      if (backend.getByISBN("1234567890123") != bookList.get(0)) {
        return false;
      }

      if (backend.searchByTitleWord("XXXXXXX").size() != 0) {
        return false;
      }
      List<IBook> lb = backend.searchByTitleWord("Title");
	  if (lb.get(0).getISBN13() == "1234567890123" && lb.get(1).getISBN13() == "1231231231231") {
		return true;
	   }

      backend.setAuthorFilter("2");
	  lb = backend.searchByTitleWord("Title");
      if (lb.get(0).getISBN13() == "1231231231231") {
			return true;
	   }

	   for (IBook book : lb) {
        if (!book.getAuthors().toLowerCase().contains("2")) {
          return false;
        }
      }

	  backend.setAuthorFilter("123");

	   lb = backend.searchByTitleWord("Title");
       if (lb.size() != 0) {
			return false;
		}

    }
    catch (FileNotFoundException e) {

	 }
    catch (NoSuchElementException e) {
      return false;
    }
    return true;
  }



  private static boolean test9() {

    IBookLoader bookLoader = new BookLoader();
    try {

      List<IBook> bookList = bookLoader.loadBooks("books.csv");
      IBookMapperBackend backend = new BookMapperBackend();
      for (IBook book : bookList) {
        backend.addBook(book);
      }
      if (backend.getAuthorFilter() != null) {
        return false;
      }
      backend.setAuthorFilter("Title");
      List<IBook> lb = backend.searchByTitleWord("1");
      for (IBook book : lb) {
        if (!book.getAuthors().toLowerCase().contains("1")) {
          return false;
        }
      }
    }
    catch (FileNotFoundException e) {
      return false;
    }
    return true;
  }



}
