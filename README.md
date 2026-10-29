# 📚 Library Management System

> A robust, console-based Java application designed to manage a library catalog, user registrations, and item circulation (borrowing/returning). Developed as the final project for **COS 2030: Basics in Java Programming (Spring 2026)**.

---

## 🚀 Overview

The **Library Management System** simulates a real-world library catalog management workflow. It provides a menu-driven interface allowing users to interact with various library assets (Books, E-Books, and DVDs), manage user accounts, search the catalog using functional expressions, and handle item availability dynamically.

---

## 🏛️ Object-Oriented Design & Architecture

The project strictly follows Object-Oriented Programming (OOP) principles and clean architecture guidelines:

* **Abstract Classes & Inheritance:** 
  * `Item` serves as the abstract base class implementing `Comparable<Item>`[cite: 5].
  * `Book` and `DVD` extend `Item`[cite: 1, 2].
  * `EBook` extends `Book` to introduce specialized digital properties[cite: 3].
* **Interfaces & Polymorphism:**
  * `LibraryUserActions` defines core user capabilities (borrowing and returning items)[cite: 9].
  * Polymorphic references are used extensively across collections holding diverse `Item` sub-types.
* **Encapsulation & Immutability:** Proper access modifiers (`private`, `protected`, `public`) with data validation logic in setters and constructors.
* **Composition:** `User` objects contain composition relationships with `LibraryCard` and collections of borrowed `Item` instances[cite: 8, 11].

---

## 🛠️ Technical Requirements Met (COS 2030)

* **Java Fundamentals:** Primitive data types, control flow statements (`if-else`, `switch`, loops), structured methods, and string operations[cite: 13].
* **Error Handling & Robustness:** 
  * Custom runtime exception `ItemUnavailableException` handles invalid borrowing states[cite: 6].
  * Comprehensive console input validation ensures the application handles unexpected entries or wrong data types gracefully without crashing[cite: 13].
* **Generics & Collections Framework:** 
  * Leverages `TreeSet` (for sorted catalog items), `HashMap` (for fast call-number lookups), `HashSet` (for unique users/formats), and `ArrayList` (for tracking user borrowings)[cite: 7, 11].
* **Lambda Expressions & Streams:** Utilizes Java Streams and lambda expressions for filtering, searching, and managing catalog items functionally.

---

## 📂 Project Structure

```text
src/
│
├── Book.java                  # Book entity extending Item[cite: 1]
│   ├── DVD.java               # DVD entity extending Item[cite: 2]
│   ├── EBook.java             # EBook entity extending Book[cite: 3]
│   ├── FileFormat.java        # Enum for E-book formats (EPUB, MOBI, AZW, PDF)[cite: 4]
│   ├── Item.java              # Abstract base class for all catalog items[cite: 5]
│   ├── ItemUnavailableException.java # Custom exception for unavailable items[cite: 6]
│   ├── Library.java           # Core system logic, collections, and menu management
│   ├── LibraryCard.java       # Library card entity tracking membership IDs[cite: 8]
│   ├── LibraryUserActions.java# Interface defining user borrowing/returning behavior[cite: 9]
│   ├── Main.java              # Entry point executing the application[cite: 10]
│   └── User.java              # User entity implementing user actions[cite: 11]
