import java.util.List;

public class BookMapperBackend implements IBookMapperBackend {

  
  String authorFilter = null;
  //List<IBook> listOfBooks = null;
  protected HashtableMap<String, IBook> map;

  public BookMapperBackend() {
    map = new HashtableMap<>();
  }
  
  public BookMapperBackend(HashtableMap<String, IBook> map) {
    this.map = map;
  }
  
  @Override
  public void addBook(IBook book) {
    // TODO Auto-generated method stub
    map.put(book.getISBN13(), book); 
  }

  @Override
  public int getNumberOfBooks() {
    // TODO Auto-generated method stub
    return map.size();
  }

  @Override
  public void setAuthorFilter(String filterBy) {
    // TODO Auto-generated method stub
    authorFilter = filterBy;
    
  }

  @Override
  public String getAuthorFilter() {
    // TODO Auto-generated method stub
    return authorFilter;
  }

  @Override
  public void resetAuthorFilter() {
    // TODO Auto-generated method stub
    setAuthorFilter(null);
    
  }

  @Override
  public List<IBook> searchByTitleWord(String word) {
    // TODO Auto-generated method stub
    List<IBook> listOfSearchedBooks = null;
    for (int book = 0; book < map.size(); book++) {
      String titleOfBook = map.get(String.valueOf(book)).getTitle();    
      if (titleOfBook.toLowerCase().contains(word.toLowerCase()) 
          && map.get(String.valueOf(book)).getAuthors() == null) {
        listOfSearchedBooks.add(map.get(String.valueOf(book)));
      }     
      else if (titleOfBook.toLowerCase().contains(word.toLowerCase()) 
          && map.get(String.valueOf(book)).getAuthors() != null 
          && map.get(String.valueOf(book)).getAuthors().equals(authorFilter)) {
        listOfSearchedBooks.add(map.get(String.valueOf(book)));
      }    
    }
    return null;
  }

  @Override
  public IBook getByISBN(String ISBN) {
    if(!(map.containsKey(ISBN))) {
      return null;
    }
    
    // TODO Auto-generated method stub
    return map.get(ISBN);
  }

}
