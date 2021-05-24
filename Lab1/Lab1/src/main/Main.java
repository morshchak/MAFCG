package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;

public class Main extends Application{
    Color violet = Color.rgb(129,129,255);
    Color orange = Color.rgb(255,129,62);
	
	public static void main (String args[]) {
		launch(args);
		}
	
	@Override
	public void start(Stage primaryStage)
	{
		Group root = new Group();
		
		Scene scene = new Scene (root, 500, 300);
		scene.setFill(Color.MEDIUMSLATEBLUE); 
		
		Rectangle rect = new Rectangle(150, 100, 200, 50);
		rect.setFill(Color.WHITE);
		root.getChildren().add(rect);
				
        Rectangle smallRect = new Rectangle(150, 120, 70, 8);
        smallRect.setFill(orange);
        root.getChildren().add(smallRect);
		
		Polygon triangle = new Polygon(
				350.0, 100.0,
                400.0, 125.0,
                350.0, 150.0
        );
		triangle.setFill(orange);
        root.getChildren().add(triangle);

        Polygon sideBlock1 = new Polygon(
        		150.0, 70.0, 
        		180.0, 70.0, 
        		195.0, 100.0, 
        		165.0, 100.0);
        sideBlock1.setFill(orange);
        root.getChildren().add(sideBlock1);

        Polygon sideBlock2 = new Polygon(
        		165.0, 150.0, 
        		195.0, 150.0, 
        		180.0, 180.0, 
        		150.0, 180.0);
        sideBlock2.setFill(orange);
        root.getChildren().add(sideBlock2);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
