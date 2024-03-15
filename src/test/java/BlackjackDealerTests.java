import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/*
class BlackjackDealer
[x] public void generateDeck() : this should generate 52 cards, one for each of 13 faces and 4 suits.
[] public ArrayList<Card> dealHand(): this will return an Arraylist of two cards and leave the remainder of the deck able to be drawn later.
[] public Card drawOne() : this will return the next card on top of the deck
[] public void shuffleDeck(); : this will return all 52 cards to the deck and shuffle their order
[] public int deckSize(); : this will return the number of cards left in the deck. After a call to shuffleDeck() this should be 52.
*/
class BlackjackDealerTests {
	//Testing Suite for the BlackjackDealer class
	//------TESTS FOR generateDeck()------
	@Test
	void initializeDealerTest() {
		BlackjackDealer dealer = new BlackjackDealer();
		assertEquals(dealer.deckSize(), 52, "BlackjackDealer: Incorrect behavior when creating BlackjackDealer Object");
	}
	//------END OF TESTS FOR generateDeck()------

	//------TESTS FOR dealHand() AND drawOne()------
	@Test
	void dealHandOnlyTest() {
		BlackjackDealer dealer = new BlackjackDealer();
		ArrayList<Card> myCards = dealer.dealHand();

		assertNotNull(myCards,"dealHand: Incorrect behavior when dealing hands, no cards being returned");
		assertEquals(myCards.size(), 2, "dealHand: Incorrect behavior when dealing hands, player cards not grabbing cards correctly");
		assertEquals(dealer.deckSize(), 50, "dealHand: Incorrect behavior when dealing hands, card is not correctly removed from deck");
	}

	@Test
	void drawOneTest(){
		BlackjackDealer dealer = new BlackjackDealer();
		ArrayList<Card> myCards = new ArrayList<>();

		myCards.add(dealer.drawOne());

		assertNotNull(myCards,"drawOne: Incorrect behavior when drawing one, no cards being returned");
		assertEquals(myCards.size(), 1, "drawOne: Incorrect behavior when drawing one, player cards not grabbing cards correctly");
		assertEquals(dealer.deckSize(), 51, "drawOne: Incorrect behavior when dealing hands, card is not correctly removed from deck");
	}

	@Test
	void dealHandDrawOneTest(){
		BlackjackDealer dealer = new BlackjackDealer();
		ArrayList<Card> myCards = dealer.dealHand();
		myCards.add(dealer.drawOne());

		assertNotNull(myCards,"dealHand/drawOne: Incorrect behavior when dealing hands, no cards being returned");
		assertEquals(myCards.size(), 3, "dealHand/drawOne: Incorrect behavior when dealing hands and drawing one, player cards not grabbing cards correctly");
		assertEquals(dealer.deckSize(), 49, "dealHand/drawOne: Incorrect behavior when dealing hands, cards are not correctly removed from deck");
	}
	@Test
	void dealHandTwice(){
		BlackjackDealer dealer = new BlackjackDealer();
		ArrayList<Card> myCards = dealer.dealHand();
		ArrayList<Card> theirCards = dealer.dealHand();

		assertNotNull(myCards,"dealHandTwice: Incorrect behavior when dealing hands once, no cards being returned");
		assertNotNull(theirCards,"dealHandTwice: Incorrect behavior when dealing hands twice, no cards being returned");
		assertEquals(dealer.deckSize(), 48, "dealHandTwice: Incorrect behavior when dealing hands twice, cards are not correctly removed from deck");
	}
	//------END OF TESTS FOR dealHand() AND drawOne()------
}
