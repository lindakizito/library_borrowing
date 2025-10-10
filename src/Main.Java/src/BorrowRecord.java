import java.time.LocalDate;

public class BorrowRecord {
    private String recordId;
    private String memberId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int daysBorrowed;

    // Constructor
    public BorrowRecord(String recordId, String memberId, String bookId,
                        LocalDate borrowDate, LocalDate returnDate, int daysBorrowed) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.daysBorrowed = daysBorrowed;
    }

    // Getters
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

    // toString
    @Override
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