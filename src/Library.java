import java.util.*;
import java.util.stream.Collectors;

/**
 * class Library
 * Represents a library system that manages items (adding, removing, listing, borrowing, returning).
 * Contains main logic of the application.
 */

public class Library  {
    private TreeSet<Item> items = new TreeSet<>();              // all items are unique and automatically sorted
    private HashMap<String, Item> itemsMap =  new HashMap<>();  // easy access to items by call number
    private HashSet<User> users = new HashSet<>();              // each user is unique

    public Library() { } // 0-argument constructor

    /**
     * Adds an item to the library (both in TreeSet and HashMap).
     * @param item item to be added
     */
    public void addItem(Item item) {
        items.add(item);
        itemsMap.put(item.getCallNumber(), item);
    }

    /**
     * Removes an item from library by a given call number.
     * @param callNumber call number of item to be removed
     */
    public void removeItem(String callNumber) {
        Item item = itemsMap.remove(callNumber);
        if (item != null) {
            items.remove(item);
        }
    }

    /**
     * removeItem(Item item) – method overloading
     */
    public void removeItem(Item item) {
        if (item != null) {
            items.remove(item);
            itemsMap.remove(item.getCallNumber());
        }
    }

    /**
     * Searches for an item by a given keyword.
     * @param keyword keyword provided by user
     * @return items that contain the keyword
     */
    public List<Item> search(String keyword) {
        return items.stream()
                .filter(i -> i.matches(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Lists only items that are available for borrowing.
     * @return available items only
     */
    public List<Item> listAvailableItems() {
        return items.stream()
                .filter(Item::isAvailable)
                .collect(Collectors.toList());
    }

    /**
     * Finds and returns the item with the corresponding call number by using the HashMap collection.
     * @param callNumber call number provided by user
     * @return item with the corresponding call number
     */
    public Item getByCallNumber(String callNumber) {
        return itemsMap.get(callNumber);
    }

    /**
     * Adds some initial objects to the library for exemplary purposes.
     */
    public void populateLibrary() {
        Item firstBook = new Book("PR6029.R8 A5 1982", "Animal Farm", true,
                             "George Orwell", 155);

        Item secondBook = new Book("PS3537.T3234 E3 2012", "East of Eden", false,
                "John Steinbeck", 728);

        HashSet<FileFormat> formats = new HashSet<>();
        formats.add(FileFormat.EPUB);
        formats.add(FileFormat.MOBI);

        Item ebook = new EBook("PS3619.H328 H66 2014", "Honor", true,
                               "Elif Shafak", 324, formats);

        Item dvd = new DVD("M1520 .N80 1994", "The Nutcracker", true, 104);

        this.addItem(firstBook);
        this.addItem(secondBook);
        this.addItem(ebook);
        this.addItem(dvd);
    }

    /**
     * Prints a collection using generic type and enhanced for loop.
     * @param collection any collection of the given bounded type
     * @param <T> any child class of Item, including Item
     */
    private <T extends Item> void printItems(Collection<T> collection) {
        for (T item : collection) {
            System.out.println(item);
        }
    }

    /**
     * Driver method that controls the application flow, processes user input and handles errors.
     * Uses switch-case, while loops, for loops, methods of the Library class.
     */
    public void run() {
        this.populateLibrary(); // loading exemplary objects to current library

        // array containing menu options – according to project requirements
        String[] menuOptions = {
                "List all items in the library catalog (books, e-books, DVDs)",
                "Add an item to the library catalog",
                "Remove an item from the library catalog",
                "Get an item by call number",
                "List available items only",
                "Search an item by keyword",
                "Create a new library user",
                "Borrow an item",
                "Return an item",
                "List items borrowed by user",
        };

        System.out.println("\n~~~~~~~~~~~~~~~ Welcome to the Library Catalog! ~~~~~~~~~~~~~~~");
        System.out.println("##### Please enter the respective number of the activity: #####\n");

        // printing all menu options
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }

        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);
        User user = null;
        String callNumber = "";
        Item item = null;

        // loop asking for input until user asks to exit the application
        while(true) {
            System.out.print("\nNumber of activity: ");

            String input = scanner.nextLine();
            int selectedActivity;

            // exception handling if input is not in the correct format
            try {
                selectedActivity = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (selectedActivity) {
                case 1: // list all items in the library catalog
                    printItems(items);
                    break;
                case 2: // add an item to the library catalog
                    System.out.print("Enter item type (book/DVD): ");
                    String itemType = scanner.nextLine().trim();

                    // input validation
                    while (itemType.isEmpty()) {
                        System.out.print("Item type cannot be empty. Please try again: ");
                        itemType = scanner.nextLine().trim();
                    }

                    System.out.print("Call number: ");
                    callNumber = scanner.nextLine();

                    while (callNumber.isEmpty()) {
                        System.out.print("Call number cannot be empty. Please try again: ");
                        callNumber = scanner.nextLine().trim();
                    }

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    while (title.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        title = scanner.nextLine().trim();
                    }

                    if (itemType.equalsIgnoreCase("book")) {
                        System.out.print("Author: ");
                        String author = scanner.nextLine().trim();

                        System.out.print("Pages: ");
                        int pages;
                        try {
                            pages = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number of pages. Please try again.");
                            break;
                        }

                        item = new Book(callNumber, title, true, author, pages);

                    } else if (itemType.equalsIgnoreCase("dvd")) {
                        System.out.print("Duration: ");
                        double duration = scanner.nextDouble();
                        scanner.nextLine();

                        item = new DVD(callNumber, title, true, duration);
                    }

                    if (item == null) {
                        System.out.println("Invalid item type.");
                        break;
                    } else if (item instanceof Book) {
                        Book book = (Book) item; // down-casting
                        System.out.println("Book created for author: " + book.getAuthor());
                    } else if (item instanceof DVD) {
                        DVD dvd = (DVD) item; // down-casting
                        System.out.println("DVD added has a duration of: " + dvd.getDuration() + " minutes.");
                    }

                    this.addItem(item);
                    System.out.println("Item successfully added to the library catalog.");
                    break;
                case 3: // remove an item from the library catalog
                    System.out.print("Enter call number of the item you would like to remove: ");
                    callNumber = scanner.nextLine().trim();

                    while (callNumber.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        callNumber = scanner.nextLine().trim();
                    }

                    // checks if call number is present in the catalog
                    if (itemsMap.containsKey(callNumber)) {
                        this.removeItem(callNumber);
                        System.out.println("Item successfully removed from the library catalog.");
                    } else {
                        System.out.println("Item was not found.");
                    }
                    break;
                case 4: // get an item by call number
                    System.out.print("Enter call number of the item you would like to get: ");
                    callNumber = scanner.nextLine().trim();

                    while (callNumber.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        callNumber = scanner.nextLine().trim();
                    }

                    item = this.getByCallNumber(callNumber);
                    if (item == null) {
                        System.out.println("No item found with this call number.");
                    } else {
                        System.out.println(item);
                    }
                    break;
                case 5: // list available items only
                        printItems(listAvailableItems());
                    break;
                case 6: // search an item by keyword
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine().trim();

                    while (keyword.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        keyword = scanner.nextLine().trim();
                    }

                    List<Item> results = this.search(keyword);

                    if (results.isEmpty()) {
                        System.out.println("No items found with the given keyword.");
                    } else {
                        System.out.println("\nItems found with the given keyword:");
                        printItems(results);
                    }
                    break;
                case 7: // create a new library user
                    System.out.print("Enter name of user: ");
                    String name = scanner.nextLine().trim();

                    while (name.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        name = scanner.nextLine().trim();
                    }

                    LibraryCard card = new LibraryCard();
                    user = new User(name, card);
                    users.add(user);
                    System.out.println("User successfully created. You are now registered as: " + user.getName());
                    break;
                case 8: // borrow an item
                    // The system will not allow item borrowing if there is no registered user.
                    if (user == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }
                    System.out.print("Enter call number: ");
                    callNumber = scanner.nextLine().trim();

                    while (callNumber.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        callNumber = scanner.nextLine().trim();
                    }

                    item = this.getByCallNumber(callNumber);

                    if (item == null) {
                        System.out.println("Item not found.");
                        break;
                    }

                    try {
                        user.borrowItem(item);
                        System.out.println("Item successfully borrowed by " + user.getName());
                    } catch (ItemUnavailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9: // return an item
                    if (user == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }
                    System.out.print("Enter call number: ");
                    callNumber = scanner.nextLine().trim();

                    while (callNumber.isEmpty()) {
                        System.out.print("Title cannot be empty. Please try again: ");
                        callNumber = scanner.nextLine().trim();
                    }

                    item = this.getByCallNumber(callNumber);

                    if (item == null) {
                        System.out.println("Item not found.");
                        break;
                    }

                    user.returnItem(item);
                    break;
                case 10: // list items borrowed by user
                    if (user == null) {
                        System.out.println("Please create a user first.");
                        break;
                    }
                    System.out.println("List of items borrowed by " + user.getName() + ": ");
                    printItems(user.getBorrowedItems());
                    break;
                case 0: // exit
                    System.out.println("Goodbye!");
                    return; // terminate the program
                default:
                    System.out.println("Invalid activity selected. Please try again.\n");
            }
        }
    }
}