public class BookManager {
    private CustomArrayList<BookItem> books = new CustomArrayList<>();

    public void addBook(BookItem book) {
    
        for (int i = 0; i < books.size(); i++) {
            BookItem existingBook = books.get(i);
            if (existingBook.getTitle().equalsIgnoreCase(book.getTitle()) &&
                    existingBook.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                
                existingBook.setQuantity(existingBook.getQuantity() + book.getQuantity());
                return;
            }
        }
       
        books.add(book);
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
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i + ". " + books.get(i));
        }
    }

    public BookItem getBook(int index) {
        if (index >= 0 && index < books.size()) return books.get(index);
        return null;
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found: " + books.get(i));
                found = true;
            }
        }
        if (!found) System.out.println("Book not found.");
    }


    public void returnBook(String title, int quantity) {
        for (int i = 0; i < books.size(); i++) {
            BookItem book = books.get(i);
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setQuantity(book.getQuantity() + quantity); 
                System.out.println("Returned " + quantity + " of " + title + " to the inventory.");
                return;
            }
        }
        System.out.println("Book not found in inventory.");
    }
}
