import javafx.application.*;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;

import java.util.concurrent.Flow;

public class HelloWorld extends Application {
 
    public static void main(String[] args) {
        //launch(args);
        System.out.println("Hello World");
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Java FX Demo Program");
        //Group root = new Group();
        FlowPane fp = new FlowPane();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
               
        drawShapes(gc);

        Text t = new Text("Hello World");
        t.setFill(Color.PURPLE);
        Font f= new Font(25);
        
        t.setFont(f);
        fp.getChildren().add(t);




       // root.getChildren().add(canvas);
        fp.getChildren().add(canvas);

     //   primaryStage.setScene(new Scene(root));
        primaryStage.setScene(new Scene(fp));

        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc) {
//        gc.setFill(Color.PURPLE);
//        gc.fillText("Hello World",0 ,0);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);

        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }
}