import java.time.LocalDate;

public class BorrowRecord {
    private int recordId;
    private int memberId;
    private int bookId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int daysBorrowed;

    // Constructor
    public BorrowRecord(int recordId, int memberId, int bookId,
                        LocalDate borrowDate, LocalDate returnDate, int daysBorrowed) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.daysBorrowed = daysBorrowed;
    }

    // Getters
    public int getRecordId() {
        return recordId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getBookId() {
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