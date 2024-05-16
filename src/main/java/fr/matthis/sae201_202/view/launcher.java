package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new Main();
    }

    public static void main(String[] args) {
        launch(args);
    }
}