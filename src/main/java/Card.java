public class Card {
    String suit;
    int value;

    Card(String theSuit, int theValue) {
        suit = theSuit;
        value = theValue;
    }

    public String cardName(){
        return this.value + this.suit + ".jpg";
    }
}
