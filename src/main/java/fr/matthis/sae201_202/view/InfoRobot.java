package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.controller.EventManager;
import fr.matthis.sae201_202.model.Ore;
import fr.matthis.sae201_202.model.Robots;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class InfoRobot extends Info {
    private Robots r;

    public InfoRobot(Robots ro) throws IOException {
        super();
        this.r = ro;
        this.setTitle(genTitle());
        graphical();
        this.show();
    }

    private void graphical() throws IOException {
        this.coord.getChildren().add(new Label(this.r.getPosition().toString()));
        Label type = new Label(genType());
        String capT = "" + this.r.getCapacity() + " / " + this.r.getMaxCapacity();
        Label cap = new Label(capT);

        Label chemin = new Label("Chemin : " + this.r.getPath());
        ScrollBar scroll = new ScrollBar();
        scroll.setMin(0);
        scroll.setMax(100);
        scroll.setValue(50);
        scroll.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        ScrollPane sp = new ScrollPane();
        sp.setContent(chemin);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        VBox cheminScroll = new VBox();
        cheminScroll.getChildren().addAll(chemin,sp);


        Button fermer = new Button("Fermer");
        fermer.setOnMouseClicked(new EventManager(this));

        this.root.getChildren().addAll(type,cap,cheminScroll,fermer);
        this.getIcons().add(genImage());
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

    @Override
    protected Image genImage() throws IOException {
        if (this.r.getType() == Ore.gold) {
            String imagePath = "/images/Steve.jpg";
            return new Image(getClass().getResourceAsStream(imagePath));
        } else {
            String imagePath = "/images/Alex.jpg";
            return new Image(getClass().getResourceAsStream(imagePath));
        }
    }
}