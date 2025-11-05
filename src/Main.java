import java.sql.Connection; //represents a connection to the database
import java.sql.ResultSet; //stores results returned from the database
import java.sql.Statement; //used to send sql queries
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try { //try block is used so if sth goes wrong, execution jumps to the catch
            // Connect to database
            Connection conn = DatabaseConnection.getConnection();
            System.out.println(" Connection successful!\n");

            // --- FETCH AND PRINT BOOKS ---
            Statement stmt = conn.createStatement(); //creates a statement to send sql commands
            ResultSet rsBooks = stmt.executeQuery("SELECT * FROM books"); //sends sql query to database and the result is stored in rsBooks

            ArrayList<Book> books = new ArrayList<>(); //creates list to store objects
            while (rsBooks.next()) { //rsBooks.next() moves to each row
                String bookId = rsBooks.getString("book_id");
                String title = rsBooks.getString("title");
                String author = rsBooks.getString("author");
                String genre = rsBooks.getString("genre");

                Book book = new Book(bookId, title, author, genre); //creates a book object
                books.add(book); //adds it to the books list
            }

            System.out.println(" BOOKS:");
            for (Book b : books) { //Uses Book’s toString() to print information
                System.out.println(b);
            }
            System.out.println();

            // --- FETCH AND PRINT MEMBERS ---
            ResultSet rsMembers = stmt.executeQuery("SELECT * FROM members");

            ArrayList<Member> members = new ArrayList<>();
            while (rsMembers.next()) {
                String memberId = rsMembers.getString("member_id");
                String firstName = rsMembers.getString("first_name");
                String lastName = rsMembers.getString("last_name");
                String email = rsMembers.getString("email");

                Member member = new Member(memberId, firstName, lastName, email);
                members.add(member);
            }

            System.out.println(" MEMBERS:");
            for (Member m : members) {
                System.out.println(m);
            }
            System.out.println();

            // --- FETCH AND PRINT BORROW RECORDS ---
            ResultSet rsBorrow = stmt.executeQuery("SELECT * FROM borrow_records");

            ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();
            while (rsBorrow.next()) {
                String recordId = rsBorrow.getString("record_id");
                String memberId = rsBorrow.getString("member_id");
                String bookId = rsBorrow.getString("book_id");
                LocalDate borrowDate = rsBorrow.getDate("borrow_date").toLocalDate(); //Converts SQL Date to LocalDate using .toLocalDate().
                LocalDate returnDate = rsBorrow.getDate("return_date").toLocalDate();
                int daysBorrowed = rsBorrow.getInt("days_borrowed");

                BorrowRecord br = new BorrowRecord(recordId, memberId, bookId, borrowDate, returnDate, daysBorrowed);
                borrowRecords.add(br);
            }

            System.out.println(" BORROW RECORDS:");
            for (BorrowRecord br : borrowRecords) {
                System.out.println(br);
            }

            // --- CLOSE RESOURCES --- Properly closes ResultSets, Statement, and DB connection to avoid memory leaks
            rsBooks.close();
            rsMembers.close();
            rsBorrow.close();
            stmt.close();
            conn.close();

        } catch (Exception e) { //If any error occurs, prints stack trace for debugging
            e.printStackTrace();
        }
    }
}
