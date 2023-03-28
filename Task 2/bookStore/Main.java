import java.sql.*;
import java.util.Scanner;

public class Main {

  //Inilitialize the scanner function
  static Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {
    //Use a try statement to login and work on database
    try {
      // Connect to the ebookstore_db database, via the jdbc:mysql:channel on
      // localhost (this PC)
      // Use username "otheruser", password "swordfish".
      Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/ebookstore_db?useSSL=false",
        "otheruser",
        "swordfish"
      );

      // Create a direct line to the database for running our queries
      Statement statement = connection.createStatement();

      //Declare true for do loop to run
      boolean continueDo = true;

      do {
        System.out.println(
          "\nWhat would you like to do? \r\n" +
          "1. Enter book\r\n" +
          "2. Update book\r\n" +
          "3. Delete book\r\n" +
          "4. Search books\r\n" +
          "0. Exit"
        );

        //Scan the answer given
        String choice = userInput.nextLine();

        //Switch statement to execute method according to answer given
        switch (choice) {
          case "0":
            continueDo = false;
            break;
          case "1":
            enterBook(statement);
            break;
          case "2":
            updateBook(statement);
            break;
          case "3":
            deleteBook(statement);
            break;
          case "4":
            searchBooks(statement);
            break;
          //Default if the user entered a wrong character
          default:
            System.out.println("\nEnter a correct number: ");
            break;
        }
      } //Continue the "do" function N or n is not entered.
      while (continueDo == true);

      //Close up our connections
      statement.close();
      connection.close();
    } catch (SQLException e) {
      // We only want to catch a SQLException
      e.printStackTrace();
    }

    System.out.println("Goodbye!");

    //close the scanner
    scan.close();
  }

  //Method to insert a book
  public static void enterBook(Statement statement) throws SQLException {
    //Get variables to add books
    System.out.println("Please enter book id");
    int bookId = userInput.nextInt();
    userInput.nextLine();

    System.out.println("Please enter book title");
    String bookTitle = userInput.nextLine();

    System.out.println("Please enter book author");
    String bookAuthor = userInput.nextLine();

    System.out.println("Please enter book Qty");
    int bookQty = userInput.nextInt();
    userInput.nextLine();

    //Insert into books
    statement.executeUpdate(
      "INSERT INTO books VALUES (" +
      bookId +
      " , '" +
      bookTitle +
      "' , '" +
      bookAuthor +
      "' , " +
      bookQty +
      ")"
    );
  }

  //Method to update information
  public static void updateBook(Statement statement) throws SQLException {
    //Ask the user which book they want to ammend
    System.out.println(
      "Do you know which book you want to update by ID? \n1. Yes n\2. NO"
    );
    int userChoice = userInput.nextInt();
    userInput.nextLine();

    //If not Yes , launch the search method
    if (userChoice != 1) {
      searchBook(statement);
    }

    //Get variables to add to books
    System.out.println("\nPlease enter book id:");
    int bookId = userInput.nextInt();
    userInput.nextLine();

    ResultSet results = statement.executeQuery(
      "SELECT Id, Title, Author, Qty FROM books WHERE id = " + bookId
    );
    displayResults(results);

    System.out.println(
      "\nWhich item do you want to update? \n1. Title \n2. Author \n3. Qty"
    );
    String itemUpdate = userInput.nextLine();

    //Switch statement according to answer given

    switch (itemUpdate) {
      case "1":
        //Update the title of the book
        System.out.println("Please enter new book Title");
        String bookTitle = userInput.nextLine();
        statement.executeUpdate(
          "UPDATE books SET Title = '" + bookTitle + "' WHERE id = " + bookId
        );
        break;
      case "2":
        //Update the author of the book:
        System.out.println("Please enter new book Author: ");
        String bookAuthor = userInput.nextLine();
        statement.executeUpdate(
          "UPDATE books SET Author = '" + bookAuthor + "'  WHERE id = " + bookId
        );
        break;
      case "3":
        //UPDATE the Qty of the book:
        System.out.println("Please enter book qty:");
        int bookQty = userInput.nextInt();
        userInput.nextLine();
        statement.executeUpdate(
          "OPDATE boos SET qty = " + bookQty + " WHERE ID = " + bookId
        );
        break;
      // Default if user entered a wrong character
      default:
        System.out.println("\nBook was not ammended\n");
        break;
    }
  }

  //Method on how to delete a book
  public static void deleteBook(Statement statement) throws SQLException {
    //Asks the user which book they want to delete
    System.out.println(
      "Do you know which book you want to delete by ID? \n1. Yes \n2. NO"
    );
    int userKnowsId = userInput.nextInt();
    userInput.nextLine();

    // If not Yes, launch search method
    if (userKnowsId != 1) {
      searchBook(statement);
    }

    // Get variables to add to books
    System.out.print(
      "\nPlease enter the id of the book that you want to delete \nid:"
    );
    int bookId = scan.nextInt();
    scan.nextLine();

    ResultSet results = statement.executeQuery(
      "SELECT id, Title, Author, Qty FROM books WHERE id =" + bookId
    );

    // Display book that is being deleted
    // If user made a mistake information is temporarily still available in console
    System.out.println("\nThe below book is deleted:");
    displayResults(results);

    // DELETE Books based on id :
    statement.executeUpdate("DELETE FROM books WHERE id =" + bookId);
  }

  // Method to search for a book
  public static void searchBook(Statement statement) throws SQLException {
    // Ask user by which criteria they want to search
    System.out.println(
      "Search book by: \n1. id \n2. Title \n3. Author \n4. Above Certain available \n5. Below Certain Qty \n6. Display All Books in Library"
    );
    String bookSearchOption = scan.nextLine();

    // Declare result set
    ResultSet results;

    // Switch statement according to answer given
    switch (bookSearchOption) {
      case "1":
        // Search by id
        System.out.print("\nPlease enter book id:");
        int bookId = scan.nextInt();
        scan.nextLine();
        results =
          statement.executeQuery(
            "SELECT id, Title, Author, Qty FROM books WHERE id =" + bookId
          );
        displayResults(results);
        break;
      case "2":
        // Search by Title
        System.out.println("\nPlease enter book Title:");
        String bookTitle = scan.nextLine();
        results =
          statement.executeQuery(
            "SELECT id, Title, Author, Qty FROM books WHERE Title ='" +
            bookTitle +
            "'"
          );
        displayResults(results);
        break;
      case "3":
        // Search by Author
        System.out.println("\nPlease enter book Author:");
        String bookAuthor = scan.nextLine();
        results =
          statement.executeQuery(
            "SELECT id, Title, Author, Qty FROM books WHERE Author ='" +
            bookAuthor +
            "'"
          );
        displayResults(results);
        break;
      case "4":
        // Search by minimum Qty
        System.out.print("Search for books with a Qty above:");
        int bookQty = scan.nextInt();
        scan.nextLine();
        results =
          statement.executeQuery(
            "SELECT id, Title, Author, Qty FROM books WHERE Qty >" + bookQty
          );
        displayResults(results);
        break;
      case "5":
        // Search by books with a Qty below
        System.out.print("Search for book with a Qty Below:");
        int bookQtyBelow = scan.nextInt();
        scan.nextLine();
        results =
          statement.executeQuery(
            "SELECT id, Title, Author, Qty FROM books WHERE Qty <" +
            bookQtyBelow
          );
        displayResults(results);
        break;
      case "6":
        // Display all the books in library
        System.out.print(
          "Are you sure you want to display all the books in the library?: \n1. Yes \n2. No"
        );
        int bookDisplayAll = scan.nextInt();
        scan.nextLine();

        if (bookDisplayAll == 1) {
          results =
            statement.executeQuery("SELECT id, Title, Author, Qty FROM books");
          displayResults(results);
        }
        break;
      // Default if user entered a wrong character
      default:
        System.out.println("Invalid number selected");
        break;
    }
  }

  // Method to display the results of selected items in database
  public static void displayResults(ResultSet results) throws SQLException {
    // Display Fields
    System.out.println("id, Title, Author, Qty");
    // Display selected data rows
    while (results.next()) {
      System.out.println(
        results.getInt("id") +
        ", " +
        results.getString("Title") +
        ", " +
        results.getString("Author") +
        ", " +
        results.getInt("Qty")
      );
    }
  }
}
