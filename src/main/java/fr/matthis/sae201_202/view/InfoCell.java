package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.IOException;

public class InfoCell extends Info {
    private Sector s;

    public InfoCell(Sector s) throws IOException {
        super();
        this.s = s;
        graphical();
        this.show();
    }

    private void graphical() throws IOException {
        this.setTitle(genTitle());
        coord.getChildren().add(new Text(this.s.getPosition().toString()));
        Label type = new Label(genType());
        Label cap = new Label(genCapacity());

        Button fermer = new Button("Fermer");
        fermer.setOnMouseClicked(new EventManager(this));

        root.getChildren().addAll(type, cap, fermer);
        this.getIcons().add(genImage());
    }

    @Override
    protected String genTitle() {
        if (this.s instanceof Mine) {
            if (((Mine) this.s).getType() == Ore.gold) {
                return "Minerai d'Or " + ((Mine) this.s).getId();
            } else {
                return "Minerai de Nickel " + ((Mine) this.s).getId();
            }
        } else if (this.s instanceof Entrepot) {
            if (((Entrepot) this.s).getType() == Ore.gold) {
                return "Entrepot d'Or " + ((Entrepot) this.s).getId();
            } else {
                return "Entrepot de Nickel " + ((Entrepot) this.s).getId();
            }
        } else if (this.s instanceof Lac) {
            return "Lac";
        } else {
            return "Vide";
        }
    }

    @Override
    protected String genType() {
        if (this.s instanceof Mine) {
            if (((Mine) this.s).getType() == Ore.gold) {
                return "Type : OR";
            } else {
                return "Type : NI";
            }
        } else if (this.s instanceof Entrepot) {
            if (((Entrepot) this.s).getType() == Ore.gold) {
                return "Type : OR";
            } else {
                return "Type : NI";
            }
        } else {
            return "";
        }
    }

    @Override
    protected Image genImage() throws IOException {
        if (this.s instanceof Mine) {
            if (((Mine) this.s).getType() == Ore.gold) {
                return new Image(InfoCell.class.getResource("Gold.jpg").openStream());
            } else {
                return new Image(InfoCell.class.getResource("Nickel.jpg").openStream());
            }
        } else if (this.s instanceof Entrepot) {
            if (((Entrepot) this.s).getType() == Ore.gold) {
                return new Image(InfoCell.class.getResource("ChestOr.png").openStream());
            } else {
                return new Image(InfoCell.class.getResource("ChestNickel.png").openStream());
            }
        } else if (this.s instanceof Lac) {
            return new Image(InfoCell.class.getResource("eau.jpg").openStream());
        } else {
            return new Image(InfoCell.class.getResource("herbe.jpg").openStream());
        }
    }

    private String genCapacity() {
        if (this.s instanceof Mine) {
            Mine m = (Mine) this.s;
            return "Capacite : " + m.getStockage() + " / " + m.getmaxStockage();
        } else if (this.s instanceof Entrepot) {
            Entrepot e = (Entrepot) this.s;
            return "Capacite : " + e.getStockage();
        } else {
            return "";
        }
    }
}
