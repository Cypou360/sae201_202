package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Grille;
import fr.matthis.sae201_202.model.Robots;
import fr.matthis.sae201_202.model.Sector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class EventManager implements EventHandler {

    private Main p;
    private Info info;

    private Grille grille;

    public EventManager(Main p,Grille grille) {
        this.grille = grille;
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
            } else if (b.getText().equals("Reset")) {
                p.close();
                grille.getRobots().clear();
                grille.getEntrepots().clear();
                grille.getMines().clear();
                launcher launcher = new launcher();
                try {
                    launcher.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
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







