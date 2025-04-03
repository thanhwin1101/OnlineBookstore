public class LinkedStackADT<T> {
    private Node<T> top;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    public void push(T item) {
        Node<T> node = new Node<>(item);
        node.next = top;
        top = node;
    }

    public T pop() {
        if (top == null) return null;
        T item = top.data;
        top = top.next;
        return item;
    }

    public void display() {
        Node<T> curr = top;
        System.out.println("=== Transaction History ===");
        while (curr != null) {
            // Display the Order object as a string representation
            System.out.println(curr.data.toString()); // This will use the toString() method of Order
            curr = curr.next;
        }
    }

    // Check if the order with the given ID already exists in the stack
    public boolean containsOrder(int orderId) {
        Node<T> curr = top;
        while (curr != null) {
            Order order = (Order) curr.data;
            if (order.getOrderId() == orderId) {
                return true; // Order with the given ID found
            }
            curr = curr.next;
        }
        return false; // Order not found
    }
}
