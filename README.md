 Library Borrowing System
# Project Overview
This project is a Java application that manages and analyzes library borrowing records.  
It uses a relational database (created from CSV files) to store books, members, and borrow records.  
The application connects to the database via JDBC, runs SQL queries, and produces statistical analysis and visualizations.

# Dataset
The project uses three CSV files:
- *Borrow Record* (record_id, member_id, book_id, borrow_date, return_date, days_borrowed)
- *Books* (book_id, title, author, genre)
- *Members* (member_id, first_name, last_name, email)

# Analysis Tasks
1. Most borrowed books and most active members.  
2. Average borrowing duration and overdue counts (if return_date > expected).  
3. Genre popularity distribution.  
4. Visualizations:  
   - Bar chart (top books)  
   - Pie chart (genre share)  
   - Histogram (days borrowed)  

# How to Run
1. Import the CSV files into a SQL database.  
2. Connect using JDBC in Java.  
3. Run the Java application (Main.java).  
4. View results and generated charts.  

# Documentation & Presentation
- ER diagram and relational schema.  
- Screenshots of queries and charts.  
- Written summary of results.  
- Challenges faced.  

# Team Members
Leikan Kiok
Benson Mwangi
Ayden Arodi
Collins Kalachu
Linda Kizito
