package classwork15_javafx;
	
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) { // primaryStage is created by Java VM
		try {
			GridPane grid = new GridPane();
			Scene scene = new Scene(grid, 600, 600); // creates a scene object with the GridPane
			primaryStage.setScene(scene); // puts the scene onto the stage
			primaryStage.setTitle("Primary Stage");
			primaryStage.show(); // display the stage with the scene
			
			Painter.paint(grid); // paints the icons on the grid
			
			Stage secondStage = new Stage();
			secondStage.setTitle("second");
			secondStage.show();
			

			
			StackPane pane = new StackPane();
			Scene secondS = new Scene(pane, 250, 250);
			
			secondStage.setScene(secondS);
			
			Button btn = new Button("Changer...");
			pane.getChildren().add(btn);
			
			/*
	        btn.setOnAction(new EventHandler<ActionEvent>() {     
	            @Override 
	            public void handle(ActionEvent e) {
	            	Painter.paint(grid);
	            }
	        });
			*/
			
			btn.setOnAction(e-> {
				System.out.println(e);
				Painter.paint(grid);
			});
			
			
	
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
}

