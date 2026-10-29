# 📚 Library Management System

> A robust, console-based Java application designed to manage a library catalog, user registrations, and item circulation (borrowing/returning).

---

## 🚀 Overview

The **Library Management System** simulates a real-world library catalog management workflow. It provides a menu-driven interface allowing users to interact with various library assets (Books, E-Books, and DVDs), manage user accounts, search the catalog using functional expressions, and handle item availability dynamically.

---

## 🏛️ Object-Oriented Design & Architecture

The project strictly follows Object-Oriented Programming (OOP) principles and clean architecture guidelines:

* **Abstract Classes & Inheritance:** 
  * `Item` serves as the abstract base class implementing `Comparable<Item>`.
  * `Book` and `DVD` extend `Item`.
  * `EBook` extends `Book` to introduce specialized digital properties.
* **Interfaces & Polymorphism:**
  * `LibraryUserActions` defines core user capabilities (borrowing and returning items).
  * Polymorphic references are used extensively across collections holding diverse `Item` sub-types.
* **Encapsulation & Immutability:** Proper access modifiers (`private`, `protected`, `public`) with data validation logic in setters and constructors.
* **Composition:** `User` objects contain composition relationships with `LibraryCard` and collections of borrowed `Item` instances.

---

## 🛠️ Technical Requirements Met (COS 2030)

* **Java Fundamentals:** Primitive data types, control flow statements (`if-else`, `switch`, loops), structured methods, and string operations.
* **Error Handling & Robustness:** 
  * Custom runtime exception `ItemUnavailableException` handles invalid borrowing states.
  * Comprehensive console input validation ensures the application handles unexpected entries or wrong data types gracefully without crashing.
* **Generics & Collections Framework:** 
  * Leverages `TreeSet` (for sorted catalog items), `HashMap` (for fast call-number lookups), `HashSet` (for unique users/formats), and `ArrayList` (for tracking user borrowings).
* **Lambda Expressions & Streams:** Utilizes Java Streams and lambda expressions for filtering, searching, and managing catalog items functionally.

---

## 📂 Project Structure

```text
src/
│
├── Book.java                  # Book entity extending Item
│   ├── DVD.java               # DVD entity extending Item
│   ├── EBook.java             # EBook entity extending Book
│   ├── FileFormat.java        # Enum for E-book formats (EPUB, MOBI, AZW, PDF)
│   ├── Item.java              # Abstract base class for all catalog items
│   ├── ItemUnavailableException.java # Custom exception for unavailable items
│   ├── Library.java           # Core system logic, collections, and menu management
│   ├── LibraryCard.java       # Library card entity tracking membership IDs
│   ├── LibraryUserActions.java# Interface defining user borrowing/returning behavior
│   ├── Main.java              # Entry point executing the application
│   └── User.java              # User entity implementing user actions
