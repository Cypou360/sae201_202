package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class view1 extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Mainview(20.0));
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
