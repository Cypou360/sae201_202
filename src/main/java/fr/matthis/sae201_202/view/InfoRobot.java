package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Ore;
import fr.matthis.sae201_202.model.Robots;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoRobot extends Info {
    private Robots r;

    public InfoRobot(Robots ro) {
        super();
        this.r = ro;
        this.setTitle(genTitle());
        graphical();
        this.show();
    }

    private void graphical() {
        this.coord.getChildren().add(new Label(this.r.getPosition().toString()));
        Label type = new Label(genType());
        String capT = "" + this.r.getCapacity() + " / " + this.r.getMaxCapacity();
        Label cap = new Label(capT);

        Button fermer = new Button("Fermer");
        fermer.setOnMouseClicked(new EventManager(this));

        this.root.getChildren().addAll(type,cap,fermer);
    }

    @Override
    protected String genTitle() {
        if (this.r.getType() == Ore.gold) {
            return "Robot d'or" + this.r.getId();
        } else {
            return "Robot de nickel" + this.r.getId();
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