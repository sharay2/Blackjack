import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

		Button start = new Button("Start");
		Button quit = new Button("Quit");

		start.getStyleClass().add("titleButtons");
		quit.getStyleClass().add("titleButtons");

		TextField title = new TextField("BLACKJACK");
		title.setEditable(false);
		title.getStyleClass().add("title");
		title.setFocusTraversable(false);

		VBox v1 = new VBox(20, title, start, quit);
		HBox h1 = new HBox(10, v1);

		v1.setAlignment(Pos.CENTER);
		h1.setAlignment(Pos.CENTER);
		//setting font and font size
		h1.setStyle("-fx-font-family: 'Comic Sans MS'");
		
		BorderPane pane = new BorderPane();
		pane.setCenter(h1);
				
		Scene scene = new Scene(pane, 900,700);
		scene.getRoot().getStyleClass().add("background");
		scene.getStylesheets().add(css);

		primaryStage.setScene(scene);
		primaryStage.show();

		quit.setOnAction(e->{
			primaryStage.close();
		});
	}

}
