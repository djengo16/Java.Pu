import java.awt.dnd.InvalidDnDOperationException;
import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;

    public Library(){
        books = new ArrayList<>();
    }

    public void AddBook(Book book){
        this.books.add(book);
    }

    public void SearchBook(String author){
        Book searchedBook = null;

        for (Book book:
             books) {
            if (book.getAuthor() == author){
                searchedBook = book;
            }
        }
        if (searchedBook == null){
            throw new InvalidDnDOperationException("Invalid author!");
        }


        PrintBookInformation(searchedBook);
    }

    public int GetCount() {
        return this.books.size();
    }

    public void DeleteBook(String authorName){
        for (Book book:
             books) {
            if (book.getAuthor() == authorName){
                books.remove(book);
            }
        }
    }

    private void PrintBookInformation(Book book){
        StringBuilder information = new StringBuilder();

        information.append("Book title: " + book.getTitle() + '.');
        information.append(System.getProperty("line.separator"));
        information.append("Book author: " + book.getAuthor()+'.');
        information.append(System.getProperty("line.separator"));
        information.append("Book publisher: " + book.getPublisher()+'.');
        information.append(System.getProperty("line.separator"));
        information.append("Year of publishing: " + book.getYearOfPublishing()+'.');
        information.append(System.getProperty("line.separator"));
        information.append("Book ISBN number: " + book.getISBN() +'.');

    }

    public void PrintAllBooks(){
        for (Book book:
             books) {
            PrintBookInformation(book);
        }
    }


}
