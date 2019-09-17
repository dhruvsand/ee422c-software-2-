

/*
 * Name 1:
 * 
 * Name 2:
 * 
 * Name 3:
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private static final int width = 600;
  private static final int height = 600;
  private GraphicsContext gc;
  private ColorPicker colorPicker;
  private Color color = Color.BLACK;
  private Button clrButton;
  BorderPane pane;

  @Override
  public void start(Stage stage) throws Exception {
	    pane = new BorderPane();
	    Canvas canvas = new Canvas(width, height);
	    pane.setCenter(canvas);
	    gc = canvas.getGraphicsContext2D();

	    MouseHandler handler = new MouseHandler();
	    canvas.setOnMouseClicked(handler);
	    canvas.setOnMouseMoved(handler);


	    colorPicker = new ColorPicker();
	    pane.setTop(colorPicker);
	    colorPicker.setValue(color);

	    colorPicker.setOnAction(new ColorChanger());
	    
	    Scene scene = new Scene(pane, width, height);
	    stage.setScene(scene);
	    stage.show();
  }


    private class ColorChanger implements EventHandler<ActionEvent> {

      @Override
      public void  handle (ActionEvent event){
        color=colorPicker.getValue();
        gc.setStroke(color);


      }

    }




  private class MouseHandler implements EventHandler<MouseEvent> {
	  
	  boolean drawing;
	  double newX;
	  double newY;
	  double oldX;
	  double oldY;
	  
	  public MouseHandler() {
		  drawing = false;
	  }
	
    @Override
    public void handle(MouseEvent event) {
    	if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
    	{
    		drawing = !drawing;
    		newX = event.getX();
    		newY = event.getY();
    System.out.println(newX + "   " + newY);
    	}
    	
    	if (event.getEventType() == MouseEvent.MOUSE_MOVED)
    	{
    		if (drawing) {
    			oldX = newX;
    			oldY = newY;
        		newX = event.getX();
        		newY = event.getY();
    			gc.strokeLine(oldX, oldY, newX, newY);
    		}
    		


    	}
   
    		
 
   
    }

  }
}
