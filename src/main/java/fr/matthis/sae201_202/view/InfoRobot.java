package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Ore;
import fr.matthis.sae201_202.model.Robots;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoRobot extends Info {
    private Robots r;

    public InfoRobot(Robots r) {
        super();
        this.r = r;
        graphical();
        this.show();
    }

    private void graphical() {
        this.coord.getChildren().add(new Label(this.r.getPosition().toString()));
        Label type = new Label(genType());
        String capT = "" + r.getCapacity() + " / " + r.getMaxCapacity();
        Label cap = new Label(capT);

        Button fermer = new Button("Fermer");
        fermer.setOnMouseClicked(new EventManager(this));

        this.root.getChildren().addAll(type,cap,fermer);
    }

    @Override
    protected String genTitle() {
        if (r.getType().equals(Ore.gold)) {
            return "Robot d'or" + r.getId();
        } else {
            return "Robot de nickel" + r.getId();
        }
    }

    @Override
    protected String genType() {
        if (this.r.getType().equals(Ore.gold)) {
            return "Type : OR";
        } else {
            return "Type : NI";
        }
    }
}