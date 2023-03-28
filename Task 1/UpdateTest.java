package main;

import java.sql.*;

public class UpdateTest {

	public static void main(String[] args) {
		updateQty();
	}

	
	// Method to update current quantity
	public static void updateQty() {

		try {
			// Connect to the library_db database, via the jdbc:mysql:channel on localhost
			// (this PC)
			// Use username "otheruser", password "swordfish".
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db?useSSL=false",
					"otheruser", "swordfish");

			// Create a direct line to the database for running our queries
			Statement statement = connection.createStatement();

			// UPDATE the QTY of the book:
			statement.executeUpdate("UPDATE books SET qty = 0 WHERE title = 'Introduction to Java'");

			// Close up our connections
			statement.close();
			connection.close();

		} catch (SQLException e) {
			// We only want to catch a SQLException - anything else is off-limits for now.
			e.printStackTrace();
		}

	}

}
