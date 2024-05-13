package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class view1 extends Application {
    @Override
    public void start(Stage stage) {
        javafx.scene.text.Text text = new Text(10, 40, "Hello bande de FDP");
        Group group1 = new Group(text);
        stage.setTitle("Robot Mineur");
        Scene scene = new Scene(group1);
        stage.show();
    }
}
