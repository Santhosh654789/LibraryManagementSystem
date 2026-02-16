package LibraryManagementSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true; 
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Book is currently not available.");
        }
    }

    public void returnBook() {
        if (!available) {
            available = true;
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book is already available in the library.");
        }
    }

    public void getDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Availability: " + (available ? "Available" : "Borrowed"));
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public Book searchBook(String keyword) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) || book.getAuthor().equalsIgnoreCase(keyword)) {
                return book;
            }
        }
        System.out.println("Book not found.");
        return null;
    }

    public void borrowBook(String keyword) {
        Book book = searchBook(keyword);
        if (book != null) {
            book.borrowBook();
        }
    }

    public void returnBook(String keyword) {
        Book book = searchBook(keyword);
        if (book != null) {
            book.returnBook();
        }
    }

    public void listAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                book.getDetails();
                System.out.println("--------------------");
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Available Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;

                case 2:
                    System.out.print("Enter book title or author: ");
                    String keywordSearch = scanner.nextLine();
                    Book foundBook = library.searchBook(keywordSearch);
                    if (foundBook != null) {
                        foundBook.getDetails();
                    }
                    break;

                case 3:
                    System.out.print("Enter book title or author to borrow: ");
                    String borrowKeyword = scanner.nextLine();
                    library.borrowBook(borrowKeyword);
                    break;

                case 4:
                    System.out.print("Enter book title or author to return: ");
                    String returnKeyword = scanner.nextLine();
                    library.returnBook(returnKeyword);
                    break;

                case 5:
                    library.listAvailableBooks();
                    break;

                case 6:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
