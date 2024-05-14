package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;


public class eventManager implements EventHandler  {

    @Override
    public void handle(Event e) {

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        double height = screenSize.getHeight();



        MouseEvent ev=((MouseEvent) e);
        System.out.println(ev);

        Rectangle r = (Rectangle) ev.getSource();

        int cellsize = (int) ((height-100)/10);

        int x = (int) (r.getX()/cellsize);

        int y = (int) (r.getY()/cellsize);


        System.out.println(x);
        System.out.println(y);

        ImagePattern img = (ImagePattern) r.getFill();

        Image img2 = img.getImage();

        Class img3 = img2.getClass();

        System.out.println(img3);



        try {
            System.out.println(getClass().getResource("ChestOr.png").openStream());
            System.out.println(r.getFill().getClass());
            if (r.getFill().equals(getClass().getResource("ChestOr.png").openStream())) {
                // Ton code ici
                System.out.println("c'est un Minerai");
            }

            else if(r.getFill().equals(getClass().getResource("ChestFer.png"))){
                System.out.println("c'est un Coffre");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
