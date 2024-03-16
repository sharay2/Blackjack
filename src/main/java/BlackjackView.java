import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BlackjackView extends Application {
	String css = getClass().getResource("styles.css").toExternalForm();

	double money;//since multiple scenes will be referencing from the .css file, this is left here
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("BLACKJACK");
		//initialize features of the window
		ImageView icon = new ImageView(new Image("titleicon.png"));
		Button start = new Button("START");
		Button quit = new Button("QUIT");

		start.getStyleClass().add("titleButtons"); //begins the game when clicked
		quit.getStyleClass().add("titleButtons"); //quits the game when clicked
		icon.setFitWidth(150);
		icon.setFitHeight(150);

		TextField title = new TextField("BLACKJACK");
		title.setEditable(false);
		title.getStyleClass().add("title");
		title.setFocusTraversable(false);

		StackPane iconPane = new StackPane(icon);
		StackPane.setAlignment(icon, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(icon, new Insets(30));

		VBox v1 = new VBox(30, title, start, quit);
		HBox h1 = new HBox(20, v1);

		v1.setAlignment(Pos.CENTER);
		h1.setAlignment(Pos.CENTER);
		//setting font
		h1.setStyle("-fx-font-family: 'sans-serif'");

		BorderPane pane = new BorderPane();
		pane.setCenter(h1);
		pane.setBottom(iconPane);

		Scene titlescene = new Scene(pane, 700,500);
		titlescene.getRoot().getStyleClass().add("background");
		titlescene.getStylesheets().add(css);

		primaryStage.setScene(titlescene);
		primaryStage.show();

		start.setOnAction(e -> moneyScene(primaryStage));
		quit.setOnAction(e->{
			primaryStage.close();
		});
	}

	public void moneyScene(Stage primaryStage) {

		Button entermoney = new Button("ENTER MONEY");
		TextField mymoney = new TextField(); // current money input

		VBox game = new VBox(mymoney, entermoney);
		mymoney.setPromptText("Enter starting money");

		mymoney.setAlignment(Pos.CENTER);
		entermoney.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(game);

		entermoney.setOnAction(e -> {
			try {
				money = Double.parseDouble(mymoney.getText());
				betScene(primaryStage, money);
			} catch (NumberFormatException ex) {
				mymoney.clear();
				mymoney.setPromptText("Error: enter valid starting money");
			}
		});

		Scene newScene = new Scene(pane, 700, 500);
		newScene.getRoot().getStyleClass().add("background");
		newScene.getStylesheets().add(css);

		// Set the new scene to the stage
		primaryStage.setScene(newScene);
	}

	public void betScene(Stage primaryStage, double money) {
		// Create an empty layout for the new scene

		BlackjackGame game = new BlackjackGame();
		BorderPane pane = new BorderPane();

		game.money = money;

		TextField textmoney = new TextField(String.format("$%.2f", game.money));
		textmoney.setEditable(false);
		textmoney.getStyleClass().add("moneybox"); //begins the game when clicked

		TextField betinput = new TextField(); // bet money input

		betinput.setPromptText("Enter a bet");
		betinput.setMaxWidth(100);

		Button bet = new Button("BET");

		VBox directions = new VBox(betinput, bet, textmoney);

		bet.setOnAction(event -> { // bet button clicked, set bet

			try {
				double user = Double.parseDouble(betinput.getText());
				if(user <= game.money) {
					game.setBet(user); // convert bet money input to double
					betinput.setEditable(false);
					bet.setDisable(true);
					betinput.clear();
					betinput.setPromptText("");
					gameScene(primaryStage, game);
				}
				else{
					betinput.clear();
					betinput.setPromptText("Error: enter valid bet");
				}
			}
			catch (NumberFormatException e){
				betinput.clear();
				betinput.setPromptText("Error: enter valid bet");
			}
		});



		directions.setAlignment(Pos.CENTER_RIGHT);
		HBox directionsWrapper = new HBox(directions);
		directionsWrapper.setPadding(new Insets(0, 20, 0, 0));

		VBox.setMargin(bet, new Insets(0, 70, 20, 0));

		pane.setRight(directionsWrapper);

		Scene newScene = new Scene(pane, 700, 500);
		newScene.getRoot().getStyleClass().add("background");
		newScene.getStylesheets().add(css);

		// Set the new scene to the stage
		primaryStage.setScene(newScene);
	}

	public void gameScene(Stage primaryStage, BlackjackGame game) {
		BorderPane pane = new BorderPane(); // set pane
//		playersCards(pane, game); // call first deck
		HBox dealerDeck = dealerCards(game);
		HBox playerDeck = playersCards(game);
		HBox revealedDealerDeck = revealDealer(game);

		dealerDeck.setAlignment(Pos.CENTER_LEFT);
		playerDeck.setAlignment(Pos.CENTER_LEFT);
		revealedDealerDeck.setAlignment(Pos.CENTER_LEFT);
		//money textfield
		TextField textmoney = new TextField(String.format("$%.2f", game.money));
		textmoney.setEditable(false);
		textmoney.getStyleClass().add("moneybox"); //begins the game when clicked
		// buttons
		Button hit = new Button("HIT");
		Button stand = new Button("STAND");
		Button next = new Button("NEXT");
		Button quit = new Button("QUIT");

		next.setOnAction(e->{
			try {
				betScene(primaryStage, game.money);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});
		quit.setOnAction(e->{
			try {
				start(primaryStage);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});

		// make buttons to vbox
		VBox buttons = new VBox(10,hit,stand);
		VBox nextquitbuttons = new VBox(10, next, quit);

		// set alignment to buttons
		buttons.setAlignment(Pos.CENTER_RIGHT);
		buttons.setPadding(new Insets(30));
		pane.setRight(buttons);
		nextquitbuttons.setPadding(new Insets(30));
		nextquitbuttons.setAlignment(Pos.BOTTOM_LEFT);

		hit.setOnAction(eventhit -> {
			Card newCard = game.hitCard();
			// new game card

			String newCardName = newCard.cardName();
			ImageView newCardImage = new ImageView(new Image(newCardName));
			newCardImage.setPreserveRatio(true);
			newCardImage.setFitWidth(80);

			playerDeck.getChildren().add(newCardImage);
			if (game.checkPlayerBust()) {
				hit.setDisable(true);
				stand.setDisable(true);
				// lost things
				TextField lost = new TextField("PLAYER BUST");
				lost.getStyleClass().add("winloss");
				VBox thebox = new VBox(lost, pane.getRight());
				pane.setRight(thebox);
				pane.setBottom(nextquitbuttons);

				hit.setDisable(true);
				stand.setDisable(true);
			}
		});
		stand.setOnAction(eventhit -> {
			pane.setCenter(new VBox(100, revealedDealerDeck, playerDeck));
			hit.setDisable(true);
			stand.setDisable(true);
			while(game.gameLogic.evaluateBankerDraw(game.bankerHand)) {
				Card newCard = game.dealerHitCard();
				String newCardName = newCard.cardName();
				ImageView newCardImage = new ImageView(new Image(newCardName));
				newCardImage.setPreserveRatio(true);
				newCardImage.setFitWidth(80);

				revealedDealerDeck.getChildren().add(newCardImage);
			}
			if(game.checkDealerBust()) {
				TextField lost = new TextField("DEALER BUST");
				lost.getStyleClass().add("winloss");
				VBox thebox = new VBox(lost, pane.getRight());
				pane.setRight(thebox);
				pane.setBottom(nextquitbuttons);
			}
			TextField whoWon = new TextField();

			double earned = game.evaluateWinnings();
			String temp;

			if (earned < 0) {
				temp = "You lost $" + String.format("%.2f", Math.abs(earned));
			}
			else {
				temp = "You won $" + String.format("%.2f", earned);
			}

			whoWon.setText(temp);

			whoWon.getStyleClass().add("winloss");
			VBox thebox = new VBox(whoWon, pane.getRight());
			pane.setRight(thebox);
			pane.setBottom(nextquitbuttons);
		});

		// finish up scene
		VBox displayTable = new VBox(70,dealerDeck,playerDeck);
		displayTable.setAlignment(Pos.CENTER);
		pane.setCenter(displayTable);
		pane.setTop(textmoney);
		Scene newScene = new Scene(pane, 700, 500);
		// add css
		newScene.getRoot().getStyleClass().add("background");
		newScene.getStylesheets().add(css);
		primaryStage.setScene(newScene);

		if(game.hasBlackjack()){
			stand.fire();
		}
	}

	public HBox dealerCards(BlackjackGame game) {
		String firstbankercard = game.bankerHand.get(0).cardName(); // banker card

		// images of banker cards
		ImageView show1bankercard = new ImageView(new Image(firstbankercard));
		ImageView dealercardcover = new ImageView(new Image("cardcover.jpg"));

		dealercardcover.setPreserveRatio(true);
		show1bankercard.setPreserveRatio(true);

		dealercardcover.setFitWidth(80);
		show1bankercard.setFitWidth(80);

		HBox dealerCardView = new HBox(5, show1bankercard, dealercardcover);

		return dealerCardView;

	}

	public HBox playersCards(BlackjackGame game) {
		String firstplayercard = game.playerHand.get(0).cardName();
		String secondplayercard = game.playerHand.get(1).cardName();

		ImageView show1playercard = new ImageView(new Image(firstplayercard));
		ImageView show2playercard = new ImageView(new Image(secondplayercard));

		show1playercard.setPreserveRatio(true);
		show2playercard.setPreserveRatio(true);

		show1playercard.setFitWidth(80);
		show2playercard.setFitWidth(80);

		HBox playerCardView = new HBox(5, show1playercard, show2playercard);

		return playerCardView;
	}

	public HBox revealDealer(BlackjackGame game) {
		String newfirstdealercard = game.bankerHand.get(0).cardName();
		String newseconddealercard = game.bankerHand.get(1).cardName();

		ImageView show1dealercard = new ImageView(new Image(newfirstdealercard));
		ImageView show2dealercard = new ImageView(new Image(newseconddealercard));

		show1dealercard.setPreserveRatio(true);
		show2dealercard.setPreserveRatio(true);

		show1dealercard.setFitWidth(80);
		show2dealercard.setFitWidth(80);

		HBox newdealerCardView = new HBox(5, show1dealercard, show2dealercard);

		return newdealerCardView;
	}

}
