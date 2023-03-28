import java.sql.*;


public class Main {

    public static void main(String[] args) {

        	// Delete books method
		deleteBooks();

		// Insert books method
		insert();
    }
	//Method to delete a book
	public static void deleteBooks() {
        
    try {

// Connect to the library_db database, via the jdbc:mysql: channel
//on localhost (this PC)
// Use username "otheruser", password "swordfish".

Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db?useSSL=false ", "otheruser", "swordfish");

// Create a direct line to the database for running our queries

Statement statement = connection.createStatement();

	// DELETE Books with id larger than 8000:
    statement.executeUpdate("DELETE FROM books WHERE id>8000");


// Close up our connections

statement.close();
connection.close();

} catch (SQLException e) {
    // We only want to catch a SQLException - anything else is off-limits for now.
    e.printStackTrace();

}
    
    }
	// Method to insert a book
	public static void insert() {

        try {
			// Connect to the library_db database, via the jdbc:mysql:channel on localhost
			// (this PC)
			// Use username "otheruser", password "swordfish".
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db?useSSL=false",
					"otheruser", "swordfish");

			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();

			// INSERT INTO Books
			statement.executeUpdate("INSERT INTO books VALUES(8001,'Java ABC', 'Kevin Jones', 3)");

			// INSERT INTO Books
			statement.executeUpdate("INSERT INTO books VALUES(8002, 'Java XYZ', 'Kevin Jones', 5)");

		} catch (SQLException e) {
			// We only want to catch a SQLException - anything else is off-limits for now.
			e.printStackTrace();
		}

	}

}

 
