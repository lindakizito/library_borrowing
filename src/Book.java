public class Book { /*Defines a public class named Book
    this class models a BOOK object from our database*/
    private String bookId;
    private String title;
    private String author;
    private String genre;
//private so only this class can directly access them
    public Book(String bookId, String title, String author, String genre) { //this constructor is called when creating a new Book
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
// -----Getter Methods --------They allow read-only access from outside the class.
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}