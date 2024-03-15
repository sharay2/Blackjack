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

		Scene titlescene = new Scene(pane, 900,700);
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
		mymoney.setPromptText("Enter current money");

		mymoney.setAlignment(Pos.CENTER);
		entermoney.setAlignment(Pos.CENTER);

		BorderPane pane = new BorderPane();
		pane.setCenter(game);

		entermoney.setOnAction(e -> {
			money = Double.parseDouble(mymoney.getText());
			openNewScene(primaryStage, money);
		}
		);

		Scene newScene = new Scene(pane, 900, 700);
		newScene.getRoot().getStyleClass().add("background");
		newScene.getStylesheets().add(css);

		// Set the new scene to the stage
		primaryStage.setScene(newScene);
	}

	public void openNewScene(Stage primaryStage, double money) {
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
		Button hit = new Button("HIT");
		Button stand = new Button("STAND");

		hit.setDisable(true);
		stand.setDisable(true);

		VBox directions = new VBox(betinput, bet, hit, stand, textmoney);

		bet.setOnAction(event -> { // bet button clicked, set bet
			try {
				game.currentBet = Double.parseDouble(betinput.getText()); // convert bet money input to double
				betinput.setEditable(false);
				bet.setDisable(true);
				betinput.clear();
				betinput.setPromptText("");
				firstgame(pane, game);
				hit.setDisable(false);
				stand.setDisable(false);
				hit.setOnAction(eventhit -> {
					game.hitCard();
					String anothercard = game.playerHand.get(2).cardName();
					ImageView showcard = new ImageView(new Image(anothercard));
					showcard.setPreserveRatio(true);
					showcard.setFitWidth(100);
					VBox board = new VBox(firstgame(pane,game));
					HBox newboard = new HBox(board, showcard);
					pane.setCenter(newboard);
					BorderPane.setAlignment(newboard, Pos.CENTER);
				});
				stand.setOnAction(eventhit -> {

				});
			}
			catch (NumberFormatException e){
				betinput.setText("Enter correct bet!");
				betinput.setText("Enter correct money!");
			}
		});



		directions.setAlignment(Pos.CENTER_RIGHT);
		HBox directionsWrapper = new HBox(directions);
		directionsWrapper.setPadding(new Insets(0, 20, 0, 0));

		VBox.setMargin(hit, new Insets(20, 70, 20, 0)); // Insets(top, right, bottom, left)
		VBox.setMargin(stand, new Insets(0, 70, 20, 0));
		VBox.setMargin(bet, new Insets(0, 70, 20, 0));


		// DISPLAY CARDS VVVV
//		public void firstgame(){
//
//		}


		// DISPLAY CARDS ^^^^

		pane.setRight(directionsWrapper);

		Scene newScene = new Scene(pane, 900, 700);
		newScene.getRoot().getStyleClass().add("background");
		newScene.getStylesheets().add(css);

		// Set the new scene to the stage
		primaryStage.setScene(newScene);
	}

	public VBox firstgame(BorderPane pane, BlackjackGame game) {
		String firstplayercard = game.playerHand.get(0).cardName();
		String secondplayercard = game.playerHand.get(1).cardName();
		String firstbankercard = game.bankerHand.get(0).cardName(); // banker card

		ImageView show1playercard = new ImageView(new Image(firstplayercard));
		ImageView show2playercard = new ImageView(new Image(secondplayercard));
		ImageView dealercardcover = new ImageView(new Image("cardcover.jpg"));
		ImageView show1bankercard = new ImageView(new Image(firstbankercard));

		show1playercard.setPreserveRatio(true);
		show2playercard.setPreserveRatio(true);
		dealercardcover.setPreserveRatio(true);
		show1bankercard.setPreserveRatio(true);

		dealercardcover.setFitWidth(100);
		show1bankercard.setFitWidth(100);
		show1playercard.setFitWidth(100);
		show2playercard.setFitWidth(100);

		HBox dealerCardView = new HBox(10, show1bankercard, dealercardcover);
		HBox playerCardView = new HBox(10, show1playercard, show2playercard);
		VBox dealerplayerView = new VBox(50, dealerCardView, playerCardView);

		pane.setCenter(dealerplayerView);
		BorderPane.setAlignment(dealerplayerView, Pos.CENTER);

		return dealerplayerView;
	}

	public void hitcard(BorderPane pane, BlackjackGame game) {








	}

}
