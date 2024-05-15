package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Grille;
import fr.matthis.sae201_202.model.Ore;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Scene sc = new Scene(root, r.getX(), r.getY());

        if (grid.getRobot(id).getType() == Ore.gold) {
            stage.setTitle("Robot d'or " + grid.getRobot(id).getId());
            Text lb = new Text("Robot d'OR");
            lb.setX(20);
            lb.setY(20);
            root.getChildren().add(lb);
        } else {
            stage.setTitle("Robot de Nickel " + grid.getRobot(id).getId());
            Text lb = new Text("Robot de Nickel");
            lb.setX(20);
            lb.setY(20);
            root.getChildren().add(lb);
        }
        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();
    }

}
