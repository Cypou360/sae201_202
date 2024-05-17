package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Robots;
import fr.matthis.sae201_202.model.Sector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class EventManager implements EventHandler {

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
                int x = (int) r.getX() / p.getCellSize();
                int y = (int) r.getY() / p.getCellSize();
                Sector s = p.getGrid().getSector(x, y);
                try {
                    new InfoCellule(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (ev.getSource() instanceof Button) {
            Button b = (Button) ev.getSource();
            if (b.getText().equals("Fermer")) {
                info.close();
            } else if (b.getText().equals("Quitter")) {
                p.close();
            } else if (b.getParent().getId().equals("action")){
                Group sideBar = (Group) b.getParent().getParent().getParent();
                ChoiceBox cb = (ChoiceBox) ((Group)sideBar.getChildren().get(1)).getChildren().get(0);
                if (!cb.getValue().equals("Selectionnez un robot")){
                    String valeur = (String) cb.getValue();
                    String nb = valeur.replace("Robot ", "");
                    int id = Integer.valueOf(nb);
                    Robots r = p.getGrid().getRobot(id);
                    if(b.getText().equals("Extraire")){
                        r.extraction(p.getGrid());
                        System.out.println("extraction");
                    }
                    else if (b.getText().equals("Déposer")){
                        r.deposer(p.getGrid());
                        System.out.println("déposer");
                    }else {
                        String dir = String.valueOf(((String) b.getText()).charAt(0));
                        r.goTo(dir, p.getGrid());
                        System.out.println(dir);
                    }
                }
            }
        } else if (ev.getSource() instanceof VBox) {
                VBox v = (VBox) ev.getSource();
                if (v.getParent().getId().equals("robot")) {
                    int x = (int) ((v.getLayoutX()) / p.getCellSize());
                    int y = (int) ((v.getLayoutY()) / p.getCellSize());
                    Robots r = p.getGrid().getSector(x, y).getRobot();
                    try {
                        new InfoRobot(r);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                System.out.println(e);
            }
        }
    }






