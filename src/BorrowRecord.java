import java.time.LocalDate;

public class BorrowRecord {
    private String recordId;
    private String memberId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int daysBorrowed;

    // Constructor Used to create a BorrowRecord object
    public BorrowRecord(String recordId, String memberId, String bookId,
                        LocalDate borrowDate, LocalDate returnDate, int daysBorrowed) {
        //this.fieldName = parameter assigns values from arguments into the object’s fields
        this.recordId = recordId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.daysBorrowed = daysBorrowed;
    }

    // Getters each method returns its respective field.
    public String getRecordId() {
        return recordId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public int getDaysBorrowed() {
        return daysBorrowed;
    }

    // toString method
    @Override //replaces the default Object.toString().
    public String toString() {
        return "BorrowRecord{" +
                "recordId=" + recordId +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", daysBorrowed=" + daysBorrowed +
                '}';
    }
}