import com.sun.source.tree.LambdaExpressionTree;

public class TestBook {
    public static void main(String[] args) {
        Library library = new Library();
        Book firstBook = new Book("It","Stephen King",
                "Viking Press",1986,9789851);
        Book secondBook = new Book("Tom Sawyer","Mark Twain",
                "Belford Brothers",1876,43255);
        Book thirdBook = new Book("Doctor Sleep","Stephen King",
                "Charles Scribner's Sons",2013,123454);

        library.AddBook(firstBook);
        library.AddBook(secondBook);
        library.AddBook(thirdBook);

        library.PrintAllBooks();
        while(library.SearchBook("Stephen King") != null) {
            library.DeleteBook("Stephen King");
        }
        library.PrintAllBooks();

    }
}
