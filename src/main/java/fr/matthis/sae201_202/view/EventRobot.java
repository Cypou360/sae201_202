package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Grille;
import fr.matthis.sae201_202.model.Ore;
import fr.matthis.sae201_202.model.Robots;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class EventRobot implements EventHandler {
    private Grille grid;
    private Robots robots;

    private Stage stage;


    public EventRobot(Grille grille,Robots robots, Stage stage) {
        this.grid = grille;
        this.robots = robots;
        this.stage = stage;
    }
    @Override
    public void handle(Event e) {
        MouseEvent ev = ((MouseEvent) e);
        VBox r = (VBox) ev.getSource();
        Group root = new Group();
        Scene sc = new Scene(root, r.getWidth()+300, r.getHeight()+70);

        if (robots.getType() == Ore.gold) {
            stage.setTitle("Robot d'or " + robots.getId());
            String out = "X: " + robots.getPosition().getX() + " Y: " + robots.getPosition().getY() + " Capacity: " + robots.getCapacity() + "/" + robots.getMaxCapacity() + " Type: NI";
            Text lb = new Text(out);
            lb.setFont(new Font(20));
            lb.setX(20);
            lb.setY(20);
            root.getChildren().add(lb);
        } else {
            stage.setTitle("Robot de Nickel " + (robots.getId()));
            String out = "X: " + robots.getPosition().getX() + " Y: " + robots.getPosition().getY() + " Capacity: " + robots.getCapacity() + "/" + robots.getMaxCapacity() + " Type: OR";
            Text lb = new Text(out);
            lb.setFont(new Font(20));
            lb.setX(20);
            lb.setY(20);
            root.getChildren().add(lb);
        }

        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();
    }

}
