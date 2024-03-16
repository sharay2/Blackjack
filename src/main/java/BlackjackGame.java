import java.util.ArrayList;
import java.util.Objects;

public class BlackjackGame{
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    public BlackjackDealer theDealer;
    public BlackjackGameLogic gameLogic;
    double currentBet;
    double money;
    double totalWinnings;
    BlackjackGame(){
        currentBet = 0.0;
        totalWinnings = 0.0;
        gameLogic = new BlackjackGameLogic();
        theDealer = new BlackjackDealer();
        bankerHand = theDealer.dealHand();
        playerHand = theDealer.dealHand();
    }
    public double evaluateWinnings(){
        if("player".equals(gameLogic.whoWon(playerHand, bankerHand))){
            totalWinnings = 2*currentBet;
            money += totalWinnings;
        }
        else if("dealer".equals(gameLogic.whoWon(playerHand, bankerHand))) {
            totalWinnings = -currentBet;
        }

        return totalWinnings;
    }

    public Card hitCard(){
        Card newCard = theDealer.drawOne();
        playerHand.add(newCard);
        return newCard;
    }

    public Card dealerHitCard(){
        Card newCard = theDealer.drawOne();
        bankerHand.add(newCard);
        return newCard;
    }

    public boolean checkPlayerBust(){
        return gameLogic.handTotal(playerHand) > 21;
    }

    public boolean checkDealerBust(){
        return gameLogic.handTotal(bankerHand) > 21;
    }


    public void setBet(double bet){
        currentBet = bet;
        totalWinnings = 0;
        money -= bet;
    }

    public boolean hasBlackjack(){
        if(gameLogic.handTotal(playerHand) == 21){
            money += 2.5*currentBet;
            return true;
        }
        return false;
    }
}
