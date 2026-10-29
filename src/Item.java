/**
 * abstract class Item
 * Defines the common state and behavior shared among all items in the library.
 * Implements Comparable<Item>.
 */

public abstract class Item implements Comparable<Item> {
    private String callNumber; // A unique code that each item in the library has
    private String title;
    private boolean available;

    public Item(String callNumber, String title, boolean available) {
        setCallNumber(callNumber);
        setTitle(title);
        setAvailable(available);
    }

    /**
     * abstract getter declaration
     * Inherited classes must provide their own definitions – to return item type.
     */
    public abstract String getItemType();

    public String getCallNumber() {
        return callNumber;
    }

    public void  setCallNumber(String callNumber) {
        if (callNumber == null || callNumber.isEmpty()) {
            throw new IllegalArgumentException("Call number cannot be empty.");
        }
        this.callNumber = callNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
    }

    /**
     * Checks whether item is currently available for borrowing.
     */
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Checks whether a given keyword is contained in the title of the item.
     * @param keyword represents a keyword provided by the user
     * @return true, if the title of the item contains the keyword
     */
    public boolean matches(String keyword) {
        if (keyword == null) return false;
        return title.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return "\n[" + getItemType() + "] " + getTitle() + "\nCall number: " + getCallNumber()
                + "\nAvailability: " + (isAvailable() ? "available" : "not available");
    }

    /**
     * Overriding method compareTo() in order to use TreeSet<Item>
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Item other) {
        return this.getCallNumber().compareTo(other.getCallNumber());
    }
}