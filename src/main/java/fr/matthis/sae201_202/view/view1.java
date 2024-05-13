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
        gridgen(root, (int) scene.getWidth(), (int) scene.getHeight());

        primaryStage.show();
    }

    public void gridgen(Group g, int witdh, int height) {
        int prevX = witdh - 1870;
        int prevY = height - 920;
        for (int i = 0; i <= 10; i++) {
            Line l = new Line(prevX, 50, prevX, height-50);
            prevX += 50;
            g.getChildren().add(l);

            Line l2 = new Line(50, prevY, witdh-50, prevY);
            prevY += 50;
            g.getChildren().add(l2);
        }
    }
}
