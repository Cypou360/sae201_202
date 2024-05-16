package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;


public class eventManager implements EventHandler  {

    private Grille grid;

    public eventManager(Grille grille) {
        this.grid = grille;
    }
    @Override
    public void handle(Event e) {
        MouseEvent ev = ((MouseEvent) e);
        Rectangle r = (Rectangle) ev.getSource();
        Stage stage = new Stage();
        stage.close();
        Group root = new Group();

        Scene sc = new Scene(root, r.getWidth()+300, r.getHeight()+10);

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        double height = screenSize.getHeight();

        int cellsize = (int) ((height - 100) / 10);

        int x = (int) (r.getX() / cellsize);

        int y = (int) (r.getY() / cellsize);

        System.out.println(x);
        System.out.println(y);

        Sector ss = grid.getSector(x, y);

        if (ss instanceof Mine) {
            // Ton code ici
            if (((Mine) ss).getType() == Ore.gold) {
                stage.setTitle("Minerai d'or " + ((Mine) ss).getId());
                String out = "X: " + ss.getPosition().getX() + " Y: " + ss.getPosition().getY() + " Capacity: " + ss.getStockage() + "/" + ((Mine) ss).getmaxStockage() + " Type: OR";
                Text lb = new Text(out);
                lb.setFont(new Font(20));
                lb.setX(20);
                lb.setY(20);
                root.getChildren().add(lb);
            } else {
                stage.setTitle("Minerai de Fer " + ((Mine) ss).getId());
                String out = "X: " + ss.getPosition().getX() + " Y: " + ss.getPosition().getY() + " Capacity: " + ss.getStockage() + "/" + ((Mine) ss).getmaxStockage() + " Type: NI";
                Text lb = new Text(out);
                lb.setFont(new Font(20));
                lb.setX(20);
                lb.setY(20);
                root.getChildren().add(lb);
            }

        } else if (ss instanceof Entrepot) {
            if (((Entrepot) ss).getType() == Ore.gold) {
                stage.setTitle("Entrepot d'or " + ((Entrepot) ss).getId());
                String out = "X: " + ss.getPosition().getX() + " Y: " + ss.getPosition().getY() + " Capacity: " + ss.getStockage() + " Type: NI";
                Text lb = new Text(out);
                lb.setFont(new Font(20));
                lb.setX(20);
                lb.setY(20);
                root.getChildren().add(lb);
            } else {
                stage.setTitle("Entrepot de Fer " + ((Entrepot) ss).getId());
                String out = "X: " + ss.getPosition().getX() + " Y: " + ss.getPosition().getY() + " Capacity: " + ss.getStockage() + " Type: OR";
                Text lb = new Text(out);
                lb.setFont(new Font(20));
                lb.setX(20);
                lb.setY(20);
                root.getChildren().add(lb);
            }
        }


        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();

        root.getChildren().removeAll();

        }
    }

