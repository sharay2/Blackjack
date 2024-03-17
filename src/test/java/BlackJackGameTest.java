import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackGameTest {

    //------TESTS FOR GAME------
    // Test for evaluating money winnings
    @Test
    void evaluateDraw() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 10);
        Card playercard2 = new Card("clubs", 7);
        Card bankercard1 = new Card("hearts", 10);
        Card bankercard2 = new Card("diamonds", 7);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        game.money = 500;
        game.setBet(200);
        game.evaluateWinnings();
        assertEquals(500, game.money);
    }

    @Test
    void evaluatePlayerWin() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 10);
        Card playercard2 = new Card("clubs", 10);
        Card bankercard1 = new Card("hearts", 10);
        Card bankercard2 = new Card("diamonds", 8);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        game.money = 500; //300
        game.setBet(200);
        game.evaluateWinnings();
        assertEquals(700, game.money);
    }

    @Test
    void evalutePlayerBlackJack() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 1);
        Card playercard2 = new Card("clubs", 10);
        Card bankercard1 = new Card("hearts", 10);
        Card bankercard2 = new Card("diamonds", 8);
        game.money = 500; //300
        game.setBet(200);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        game.evaluateWinnings();
        assertEquals(800, game.money);
    }

    @Test
    void evaluateDealerWin() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 10);
        Card playercard2 = new Card("clubs", 7);
        Card bankercard1 = new Card("hearts", 10);
        Card bankercard2 = new Card("diamonds", 10);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        game.money = 500; //300
        game.setBet(200);
        game.evaluateWinnings();
        assertEquals(300, game.money);
    }

    // Check for hitCard()

    // VVVV check hit card
//    @Test
//    void checkHitCard() {
//        BlackjackGame game = new BlackjackGame();
//        game.hitCard();
//        assertEquals(game.playerHand.size(), 1);
//    }

    // Tests for player or dealer bust
    @Test
    void checkPlayerBust() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 9);
        Card playercard2 = new Card("clubs", 7);
        Card playercard3 = new Card("spades", 6);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        game.playerHand.add(playercard3);
        assertTrue(game.checkPlayerBust(), "PLAYER BUST NOT TRUE");
    }

    @Test
    void checkDealerBust() {
        BlackjackGame game = new BlackjackGame();
        Card bankercard1 = new Card("spades", 9);
        Card bankercard2 = new Card("clubs", 7);
        Card bankercard3 = new Card("spades", 6);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        game.bankerHand.add(bankercard3);
        assertTrue(game.checkDealerBust(), "DEALER BUST NOT TRUE");
    }

    // setBet() test
    @Test
    void testSetBet() {
        BlackjackGame game = new BlackjackGame();
        game.money = 500;
        game.setBet(200);
        assertEquals(game.money, 300, "Money not updated correctly when bet set");
        assertEquals(game.currentBet, 200, "Current bet not set");
    }

    // Test for testing player/dealer when hit blackjack
    @Test
    void testPlayerBlackJack() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 1);
        Card playercard2 = new Card("clubs", 10);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        assertTrue(game.playerHasBlackjack(), "PLAYER BLACKJACK NOT TRUE");
    }

    @Test
    void testDealerBlackJack() {
        BlackjackGame game = new BlackjackGame();
        Card bankercard1 = new Card("spades", 1);
        Card bankercard2 = new Card("hearts", 10);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        assertTrue(game.dealerHasBlackjack(), "DEALER BLACKJACK NOT TRUE");
    }

    // Test new round and see if old hand is different than new hand
    @Test
    void testNewRound() {
        BlackjackGame game = new BlackjackGame();
        Card playercard1 = new Card("spades", 1);
        Card playercard2 = new Card("clubs", 10);
        Card bankercard1 = new Card("spades", 1);
        Card bankercard2 = new Card("hearts", 10);
        game.playerHand.add(playercard1);
        game.playerHand.add(playercard2);
        game.bankerHand.add(bankercard1);
        game.bankerHand.add(bankercard2);
        ArrayList<Card> oldPlayerHand = new ArrayList<>();
        ArrayList<Card> oldDealerhand = new ArrayList<>();
        oldPlayerHand.addAll(game.playerHand);
        oldDealerhand.addAll(game.bankerHand);
        game.newRound();
        assertNotEquals(game.playerHand, oldPlayerHand, "Player hand not updated after new round");
        assertNotEquals(game.bankerHand, oldDealerhand, "Dealer hand not updated after new round");
    }

}