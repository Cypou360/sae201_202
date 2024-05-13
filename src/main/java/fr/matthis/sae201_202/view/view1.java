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
        gridgen(root);

        primaryStage.show();
    }

    public void gridgen(Group g) {
        int prevX = 50;
        int prevY = 50;
        for (int i = 0; i <= 10; i++) {
            Line l = new Line(prevX, 50, prevX, 50+50*10);
            prevX += 50;
            g.getChildren().add(l);

            Line l2 = new Line(50, prevY, 50+50*10, prevY);
            prevY += 50;
            g.getChildren().add(l2);
        }
    }
}
