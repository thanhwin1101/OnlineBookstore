public class OrderQueue {
    private Node front, rear;

    private class Node {
        Order data;
        Node next;

        Node(Order data) {
            this.data = data;
        }
    }

    public void enqueue(Order order) {
        Node node = new Node(order);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
    }

    public Order dequeue() {
        if (front == null) return null;
        Order order = front.data;
        front = front.next;
        if (front == null) rear = null;
        return order;
    }

    public boolean cancelOrder(int orderId, BookManager bookManager, LinkedStackADT transactionHistory) {
        Node prev = null, curr = front;

        while (curr != null) {
            if (curr.data.getOrderId() == orderId) {
                CustomArrayList<BookItem> items = curr.data.getBooks();
                for (int i = 0; i < items.size(); i++) {
                    BookItem book = items.get(i);
                    bookManager.returnBook(book.getTitle(), book.getQuantity()); 
                }
                curr.data.setStatus("Cancelled");

    
                if (!transactionHistory.containsOrder(orderId)) {
                    transactionHistory.push(curr.data);
                    System.out.println("Order " + orderId + " transaction added to history.");
                } else {
                    System.out.println("Order " + orderId + " has already been recorded in the transaction history.");
                }

                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }
}
