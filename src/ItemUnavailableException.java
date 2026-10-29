/**
 * class ItemUnavailableException
 * Represents a custom runtime exception when e.g. the user tries to borrow an unavailable item.
 */

public class ItemUnavailableException extends RuntimeException {
    public ItemUnavailableException(String message) {
        super(message);
    }
}