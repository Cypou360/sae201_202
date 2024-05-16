package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Grille;
import fr.matthis.sae201_202.model.Ore;
import fr.matthis.sae201_202.model.Robots;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class EventRobot implements EventHandler {
    private Grille grid;
    private int id;

    public EventRobot(Grille grille,int identifiant) {
        this.grid = grille;
        this.id = identifiant;
    }
    @Override
    public void handle(Event e) {
        MouseEvent ev = ((MouseEvent) e);
        Rectangle r = (Rectangle) ev.getSource();
        Stage stage = new Stage();

        Group root = new Group();
        Scene sc = new Scene(root, r.getWidth()+300, r.getHeight()+70);


        Robots rob = grid.getRobot(id);

        if (rob.getType() == Ore.gold) {
            stage.setTitle("Robot d'or " + rob.getId());
            String out = "X: " + rob.getPosition().getX() + " Y: " + rob.getPosition().getY() + " Capacity: " + rob.getCapacity() + "/" + rob.getMaxCapacity() + " Type: NI";
            Text lb = new Text(out);
            lb.setFont(new Font(20));
            lb.setX(20);
            lb.setY(20);
            root.getChildren().add(lb);
        } else {
            stage.setTitle("Robot de Nickel " + grid.getRobot(id).getId());
            String out = "X: " + rob.getPosition().getX() + " Y: " + rob.getPosition().getY() + " Capacity: " + rob.getCapacity() + "/" + rob.getMaxCapacity() + " Type: OR";
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
