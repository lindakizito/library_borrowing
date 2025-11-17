package model;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private int borrowCount; // Chart use

    public Book(String bookId, String title, String author, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public void setBorrowCount(int count) { this.borrowCount = count; }
    public int getBorrowCount() { return borrowCount; }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
}
