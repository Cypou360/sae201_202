package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.security.Principal;

public class eventManager implements EventHandler  {

    private Main p;

    public eventManager(Main p) {
        this.p = p;
    }

    @Override
    public void handle(Event e) {
        if (e.getSource() instanceof MouseEvent) {
            MouseEvent ev = ((MouseEvent) e);
            if (ev.getTarget() instanceof Rectangle) {
                Rectangle r = (Rectangle) ev.getTarget();

            }
        }
        }
    }

        stage.setScene(sc);
        stage.setResizable(false);
        stage.show();

        root.getChildren().removeAll();



    }
}






