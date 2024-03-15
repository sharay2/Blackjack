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
	String css = getClass().getResource("styles.css").toExternalForm(); //since multiple scenes will be referencing from the .css file, this is left here
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

		start.setOnAction(e -> openNewScene(primaryStage));
		quit.setOnAction(e->{
			primaryStage.close();
		});
	}

	public void openNewScene(Stage primaryStage) {
		// Create an empty layout for the new scene
		VBox newSceneLayout = new VBox();

		// Create the new scene\
		BlackjackGame game = new BlackjackGame();
		game.hitCard();
		ImageView cardcover = new ImageView(new Image("cardcover.jpg"));
		ImageView card = new ImageView(new Image("1clubs.jpg"));
		cardcover.setPreserveRatio(true);
		card.setPreserveRatio(true);

		cardcover.setPreserveRatio(true);
		card.setPreserveRatio(true);

		cardcover.setFitWidth(100);
		card.setFitWidth(100);

		HBox dealerCardView = new HBox(10, card, cardcover);

		BorderPane pane = new BorderPane();
		pane.setCenter(dealerCardView);
		BorderPane.setAlignment(dealerCardView,Pos.CENTER);

		Scene newScene = new Scene(pane, 900, 700);
		newScene.getRoot().getStyleClass().add("background");
		newScene.getStylesheets().add(css);

		// Set the new scene to the stage
		primaryStage.setScene(newScene);
	}

}
