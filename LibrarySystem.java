import java.util.ArrayList;
import java.util.Scanner;

// Book class
class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public void displayInfo() {
        System.out.println("Book ID: " + id + " | Title: " + title + " | Author: " + author + " | Issued: " + isIssued);
    }
}

// User class 
class User {
    int userId;
    String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("User ID: " + userId + " | Name: " + name);
    }
}

// Library class
class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully!");
    }

    public void displayBooks() {
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public void displayUsers() {
        for (User user : users) {
            user.displayInfo();
        }
    }

    public void issueBook(int bookId, int userId) {
        Book book = getBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isIssued) {
            System.out.println("Book is already issued.");
            return;
        }
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        book.isIssued = true;
        System.out.println(" Book issued to " + user.name);
    }

    public void returnBook(int bookId) {
        Book book = getBookById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isIssued) {
            System.out.println("Book is not currently issued.");
            return;
        }
        book.isIssued = false;
        System.out.println("Book returned successfully!");
    }

    private Book getBookById(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    private User getUserById(int id) {
        for (User u : users) {
            if (u.userId == id) return u;
        }
        return null;
    }
}

// Main class
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n Library Menu ");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Display Books");
            System.out.println("4. Display Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    lib.addBook(new Book(bookId, title, author));
                }
                case 2 -> {
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    lib.addUser(new User(userId, name));
                }
                case 3 -> lib.displayBooks();
                case 4 -> lib.displayUsers();
                case 5 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID to issue to: ");
                    int userId = sc.nextInt();
                    lib.issueBook(bookId, userId);
                }
                case 6 -> {
                    System.out.print("Enter Book ID to return: ");
                    int bookId = sc.nextInt();
                    lib.returnBook(bookId);
                }
                case 0 -> System.out.println("Exiting Library System. Goodbye!");
                default -> System.out.println(" Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
