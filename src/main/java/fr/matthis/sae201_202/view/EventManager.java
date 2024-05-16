package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Sector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class EventManager implements EventHandler  {

    private Main p;

    public EventManager(Main p) {
        this.p = p;
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
                new infoCellule(s);
            }
        }
    }
}






