public class Order {
    private String customerName;
    private String shippingAddress;
    private CustomArrayList<BookItem> books = new CustomArrayList<>();
    private int orderId;
    private static int nextId = 1;
    private String status;

    public Order(String customerName, String shippingAddress) {
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.orderId = nextId++;
        this.status = "Processing";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomArrayList<BookItem> getBooks() {
        return books;
    }

    public void addBook(BookItem book) {
        boolean bookExists = false;

        // Check if the book already exists in the order by comparing title and author
        for (int i = 0; i < books.size(); i++) {
            BookItem existingBook = books.get(i);
            if (existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                    existingBook.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                // If the book exists, update the quantity in the order
                existingBook.setQuantity(existingBook.getQuantity() + book.getQuantity());
                bookExists = true;
                break;
            }
        }

        // If the book doesn't exist in the order, add it to the list
        if (!bookExists) {
            books.add(book);
        }

        // Sort the books in the order after adding
        displayBooksSorted();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    // Sort books in the order alphabetically by title
    public void displayBooksSorted() {
        // Insertion Sort by Title (Case-insensitive)
        for (int i = 1; i < books.size(); i++) {
            BookItem key = books.get(i);
            int j = i - 1;
            while (j >= 0 && books.get(j).getTitle().compareToIgnoreCase(key.getTitle()) > 0) {
                books.set(j + 1, books.get(j));
                j--;
            }
            books.set(j + 1, key);
        }

        // After sorting, display the books in the order
        displayBooks();
    }

    // Display books in the order
    public void displayBooks() {
        System.out.println("=== Books in the Order ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " (Qty: " + books.get(i).getQuantity() + ")");
        }
    }

    // Override toString method to print meaningful Order details
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderId)
                .append(", Customer: ").append(customerName)
                .append(", Status: ").append(status)
                .append(", Shipping Address: ").append(shippingAddress)
                .append("\nBooks: \n");

        for (BookItem book : books) {
            orderDetails.append("    - ").append(book.getTitle())
                    .append(" by ").append(book.getAuthor())
                    .append(" (Qty: ").append(book.getQuantity())
                    .append(")\n");
        }

        return orderDetails.toString();
    }
}
