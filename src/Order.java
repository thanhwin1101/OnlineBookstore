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

     
        for (int i = 0; i < books.size(); i++) {
            BookItem existingBook = books.get(i);
            if (existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                    existingBook.getAuthor().equalsIgnoreCase(book.getAuthor())) {
              
                existingBook.setQuantity(existingBook.getQuantity() + book.getQuantity());
                bookExists = true;
                break;
            }
        }

        if (!bookExists) {
            books.add(book);
        }

 
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

 
    public void displayBooksSorted() {
 
        for (int i = 1; i < books.size(); i++) {
            BookItem key = books.get(i);
            int j = i - 1;
            while (j >= 0 && books.get(j).getTitle().compareToIgnoreCase(key.getTitle()) > 0) {
                books.set(j + 1, books.get(j));
                j--;
            }
            books.set(j + 1, key);
        }


        displayBooks();
    }


    public void displayBooks() {
        System.out.println("=== Books in the Order ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " (Qty: " + books.get(i).getQuantity() + ")");
        }
    }


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
