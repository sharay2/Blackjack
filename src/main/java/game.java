import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class game extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Project 2");
		//initialize features of the window
		Button b1 = new Button("button 1");
		Button b2 = new Button("button 2");
		TextField t1 = new TextField("enter text here then press button 1");
		TextField t2 = new TextField("final string goes here");
		t2.setEditable(false); //setting t2 to not be editable

		VBox v1 = new VBox(10, b1, b2);
		HBox h1 = new HBox(10,v1, t1, t2);

		v1.setAlignment(Pos.CENTER);
		h1.setAlignment(Pos.CENTER);
		//setting font and font size
		h1.setStyle("-fx-font-family: 'Comic Sans MS', 24");
		
		BorderPane pane = new BorderPane();
		pane.setCenter(h1);
		//button 1 utilizing an anonymous class
		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				t2.setText(t1.getText() + " : from the center text field!");
				b1.setText("pressed");
				b1.setDisable(true);

			}
		});
		//button 2 utilizing a lambda expression
		b2.setOnAction(e->{
			t1.clear();
			t2.clear();
			t2.setText("final string goes here");
			b1.setText("button 1");
			b1.setDisable(false);
		});
				
		Scene scene = new Scene(pane, 700,500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
