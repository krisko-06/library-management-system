/**
 * interface LibraryUserActions
 * Defines a contract for the operations executed with items – borrowing and returning.
 */

public interface LibraryUserActions {
    void borrowItem(Item item);
    void returnItem(Item item);
}
