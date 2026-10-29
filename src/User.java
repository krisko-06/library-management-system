/**
 * class User
 * implements LibraryUserActions interface
 * Provides the state and behavior of a library user.
 */

import java.util.ArrayList;
import java.util.Objects;

public class User implements LibraryUserActions {
    private String name;
    private final LibraryCard card; // Composition: a User HAS-A LibraryCard
    private ArrayList<Item> borrowedItems;

    public User(String name, LibraryCard card) {
        setName(name);
        this.card = card;
        this.borrowedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Setter with validation.
     * @param name (user)name of the user
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public ArrayList<Item> getBorrowedItems() {
        return borrowedItems;
    }

    /**
     * Defines a method for borrowing an item from library by user.
     * Throws custom exception if item is not currently available for borrowing.
     * @param item an item that the user would like to borrow
     * Sets availability to false after the user borrows the item.
     */
    @Override
    public void borrowItem(Item item) {
        if (!item.isAvailable()) {
            throw new ItemUnavailableException(item.getItemType() +
                    " cannot be borrowed because it is currently unavailable.");
        }
        borrowedItems.add(item);
        item.setAvailable(false);
    }

    /**
     * Defines a method for returning an item in library by user.
     * @param item an item that the user would like to return
     * Sets availability to true if the user returns the item.
     */
    @Override
    public void returnItem(Item item) {
        if (borrowedItems.remove(item)) {
            item.setAvailable(true);
            System.out.println(item.getItemType() + " returned successfully.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User user)) return false;
        return this.getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName());
    }

}