import java.util.List;

public class BackendDeveloperTest {

    static IBookMapperBackend backend = new BookMapperBackend();

    public static void tester(){
        if(test1() && test2() && test3() && test4() &&test5()){
            System.out.println("All tests pass!");

        }
        else{
            System.out.println("Some test fails!");
        }

    }

    public static boolean test1() {

        backend.addBook(new IBook() {
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
                return "1234567890123";
            }
        });

        backend.addBook(new IBook() {
            @Override
            public String getTitle() {
                return "Title 2";
            }

            @Override
            public String getAuthors() {
                return "Author 2";
            }

            @Override
            public String getISBN13() {
                return "1231231231231";
            }});


        if(backend.getAuthorFilter() == null){
            backend.setAuthorFilter("Potter");

            if(backend.getAuthorFilter() == "Potter"){
                backend.resetAuthorFilter();

                if(backend.getAuthorFilter() == null){
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean test2() {
        if (backend.getNumberOfBooks() == 2){
            return true;
        }

        return false;
    }

    public static boolean test3() {
        List<IBook> bookList = backend.searchByTitleWord("Title");
        if(bookList.get(0).getISBN13() =="1234567890123" && bookList.get(1).getISBN13() == "1231231231231"){
            return true;
        }
        return false;
    }


    public static boolean test4() {
        backend.setAuthorFilter("2");
        List<IBook> bookList = backend.searchByTitleWord("Title");

        if(bookList.get(0).getISBN13()=="1231231231231"){
            return true;
        }
        return false;
    }

    public static boolean test5() {
        backend.setAuthorFilter("123");

        List<IBook> bookList = backend.searchByTitleWord("Title");

        if(bookList.size() != 0){
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        tester();
    }
}
