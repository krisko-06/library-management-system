/**
 * final (immutable) class LibraryCard
 * Represents a card that the user should have for library access.
 */

public final class LibraryCard {
    private final int cardId;
    private static int numberOfCardsIssued = 0; // keeps track of the number of cards issued

    public LibraryCard() {
        this.cardId = numberOfCardsIssued++;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "Library card: ID = " + getCardId();
    }
}