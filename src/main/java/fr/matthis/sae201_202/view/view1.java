package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class view1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Robot");
        Group root = new Group();
        Scene scene = new Scene(root, 1920, 970);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        gridgen(root, (int) scene.getHeight());

        primaryStage.show();
    }

    public void gridgen(Group g, int height) {
        int cellsize = (height-100)/10;
        int prevX = 50;
        int prevY = 50;

        for (int i = 0; i <= 10; i++) {
            Line l = new Line(prevX, 50, prevX, cellsize*10+50);
            prevX += cellsize;
            g.getChildren().add(l);

            Line l2 = new Line(50, prevY, cellsize*10+50, prevY);
            prevY += cellsize;
            g.getChildren().add(l2);
        }
    }
}