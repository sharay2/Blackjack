public class Card {
    String suit;
    int value;

    Card(String theSuit, int theValue) {
        suit = theSuit;
        value = theValue;
    }

    public String cardName(Card card){
        return card.value + card.suit;
    }
}
