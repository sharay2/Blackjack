import java.util.ArrayList;
public class BlackjackGame{
    public ArrayList<Card> playerHand;
    public ArrayList<Card> bankerHand;
    public final BlackjackDealer theDealer;
    public final BlackjackGameLogic gameLogic;
    private double currentBet;
    private double totalWinnings;
    BlackjackGame(){
        currentBet = 0.0;
        totalWinnings = 0.0;
        gameLogic = new BlackjackGameLogic();
        theDealer = new BlackjackDealer();
        bankerHand = theDealer.dealHand();
        playerHand = theDealer.dealHand();
    }
    public double evaluateWinnings(){
        totalWinnings = 1.5*currentBet;
        return totalWinnings;
    }

    public void hitCard(){
        playerHand.add(theDealer.drawOne());
    }

    public boolean checkPlayerBust(){
        return gameLogic.handTotal(playerHand) > 21;
    }

    public boolean checkDealerBust(){
        return gameLogic.handTotal(bankerHand) > 21;
    }

    public void setBet(double bet){
        currentBet = bet;
    }
}
