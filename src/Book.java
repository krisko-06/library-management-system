import java.util.Objects;

/**
 * class Book
 * Defines the state and behavior of a Book offered by the library.
 * Inherits Item abstract class.
 */

public class Book extends Item {
    private String author;
    private int pages;

    public Book(String callNumber, String title, boolean available, String author, int pages) {
        super(callNumber, title, available);
        setAuthor(author);
        setPages(pages);
    }

    /**
     * Getter method overrides the abstract method declaration from the parent (Item) class.
     */
    @Override
    public String getItemType() {
        return "Book";
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Setter for author's name with input validation.
     * @param author name of author
     */
    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author's name cannot be empty.");
        }
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    /**
     * Setter for number of pages with input validation.
     * @param pages number of pages
     */
    public void setPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Number of pages must be a positive number.");
        }
        this.pages = pages;
    }

    /**
     * Overrides the method definition from the parent (Item) class.
     * @param keyword represents a keyword provided by the user
     * @return true, if keyword is found in item's title OR in author's name
     */
    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        return super.matches(keyword) || author.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + "\nAuthor: " + getAuthor() + "\nNumber of pages: " + getPages();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book book)) return false;
        return this.getCallNumber().equals(book.getCallNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCallNumber());
    }
}