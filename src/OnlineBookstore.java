import java.util.Scanner;
public class OnlineBookstore {
    private BookManager bookManager = new BookManager();
    private OrderQueue orderQueue = new OrderQueue();
    private LinkedStackADT transactionHistory = new LinkedStackADT();

    public void run() {
        bookManager.addBook(new BookItem("The Hobbit", "J.R.R. Tolkien", 10));
        bookManager.addBook(new BookItem("1984", "George Orwell", 8));
        bookManager.addBook(new BookItem("To Kill a Mockingbird", "Harper Lee", 12));
        bookManager.addBook(new BookItem("Pride and Prejudice", "Jane Austen", 5));
        System.out.println("Default books loaded.");

        Scanner scanner = new Scanner(System.in);
        int userChoice;

        do {
            System.out.println("=== Welcome to the Bookstore ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Access");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1 -> adminMenu(scanner);
                case 2 -> customerMenu(scanner);
            }
        } while (userChoice != 0);
    }

    private void adminMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Admin Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books (Sorted)");
            System.out.println("3. Search Book");
            System.out.println("4. View Transaction History");
            System.out.println("5. Cancel Order");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    bookManager.addBook(new BookItem(title, author, qty));
                    System.out.println("Book added.");
                }
                case 2 -> bookManager.displayBooksSorted();
                case 3 -> {
                    System.out.print("Enter title: ");
                    String search = scanner.nextLine();
                    bookManager.searchByTitle(search);
                }
                case 4 -> transactionHistory.display();
                case 5 -> {
                    System.out.print("Enter Order ID to cancel: ");
                    int id = scanner.nextInt();
                    boolean success = orderQueue.cancelOrder(id, bookManager, transactionHistory);
                    if (success) System.out.println("Order cancelled.");
                    else System.out.println("Order not found.");
                }
            }
        } while (choice != 0);
    }
    private void customerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("=== Customer Menu ===");
            System.out.println("1. View Books");
            System.out.println("2. Search Book");
            System.out.println("3. Place Order");
            System.out.println("4. Cancel Order");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bookManager.displayBooksSorted();
                case 2 -> {
                    System.out.print("Enter title: ");
                    String search = scanner.nextLine();
                    bookManager.searchByTitle(search);
                }
                case 3 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    Order order = new Order(name, address);
                    while (true) {
                        bookManager.displayBooksSorted();
                        System.out.print("Enter book index (-1 to finish): ");
                        int idx = scanner.nextInt();
                        if (idx == -1) break;
                        System.out.print("Quantity: ");
                        int qty = scanner.nextInt();
                        BookItem selected = bookManager.getBook(idx);
                        if (selected != null && selected.getQuantity() >= qty) {
                            selected.setQuantity(selected.getQuantity() - qty);  
                            order.addBook(new BookItem(selected.getTitle(), selected.getAuthor(), qty));  
                        } else {
                            System.out.println("Invalid or insufficient stock.");
                        }
                    }
                    if (order.getBooks().size() > 0) {
                        orderQueue.enqueue(order);
                        transactionHistory.push(order);
                        System.out.println("Order placed! ID: " + order.getOrderId());
                        order.displayBooks(); 
                    }
                }
                case 4 -> {
                    System.out.print("Enter Order ID to cancel: ");
                    int id = scanner.nextInt();
                    boolean success = orderQueue.cancelOrder(id, bookManager, transactionHistory);
                    if (success) System.out.println("Order cancelled.");
                    else System.out.println("Order not found.");
                }
            }
        } while (choice != 0);
    }

    }

