import java.util.HashSet;
import java.util.Objects;

/**
 * class EBook
 * Represents and electronic book offered by the library.
 * Inherits Book class.
 */

public class EBook extends Book {
    private final HashSet<FileFormat> supportedFileFormats;

    public EBook(String callNumber, String title, boolean available, String author, int pages,
                 HashSet<FileFormat> supportedFileFormats) {
        super(callNumber, title, available, author, pages);
        this.supportedFileFormats = new HashSet<>(supportedFileFormats);
    }

    @Override
    public String getItemType() {
        return "E-book";
    }

    /**
     * Overrides the method definition from the parent (Item) class.
     * @param keyword represents a keyword provided by the user
     * @return true if keyword is found in item's title, author's name OR matches a file format.
     */
    @Override
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        return super.matches(keyword) ||
                supportedFileFormats.toString().toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + "\nFormats: " + supportedFileFormats;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EBook ebook)) return false;
        return this.getCallNumber().equals(ebook.getCallNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCallNumber());
    }
}