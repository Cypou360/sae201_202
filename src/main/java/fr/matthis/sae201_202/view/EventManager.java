package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Robots;
import fr.matthis.sae201_202.model.Sector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class EventManager implements EventHandler  {

    private Main p;
    private Info info;

    public EventManager(Main p) {
        this.p = p;
    }

    public EventManager(Info info) {
        this.info = info;
    }

    @Override
    public void handle(Event e) {
        MouseEvent ev = ((MouseEvent) e);
        if (ev.getSource() instanceof Rectangle) {
            Rectangle r = (Rectangle) ev.getTarget();
            if (r.getParent().getId().equals("grille")) {
                int x= (int) r.getX()/p.getCellSize();
                int y= (int) r.getY()/p.getCellSize();
                Sector s = p.getGrid().getSector(x, y);
                new InfoCellule(s);
            }
        } else if (ev.getSource() instanceof Button) {
            System.out.println("1");
            Button b = (Button) ev.getSource();
            if (b.getText().equals("Fermer")) {
                System.out.println("2");
                info.close();
            }
        } else if (ev.getSource()instanceof VBox) {
            VBox v = (VBox) ev.getSource();
            if (v.getParent().getId().equals("robot")) {
                int x = (int) ((v.getLayoutX())/p.getCellSize());
                int y = (int) ((v.getLayoutY())/p.getCellSize());
                Robots r = p.getGrid().getSector(x,y).getRobot();

                System.out.println("" + x + " " + y);

                System.out.println(p.getGrid().getSector(x,y).getDisponible());
                System.out.println(r);
                new InfoRobot(r);
            }
        }

        if (e.getSource() instanceof Button) {
            Button clickedButton = (Button) e.getSource();
            if ("Quitter".equals(clickedButton.getText())) {
                p.close();
            }
        }
        /*if (e.getSource() instanceof Button) {
            Button clickedButton = (Button) e.getSource();
            if (("Reset").equals(clickedButton.getText())) {
                try {
                    p.reset();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        }*/
    }
}






