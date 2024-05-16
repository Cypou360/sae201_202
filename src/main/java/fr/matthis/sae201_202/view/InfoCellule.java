package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InfoCellule extends Stage {
    private Sector s;

    public InfoCellule(Sector s) {
        super();
        this.s = s;
        graphical();
        this.show();
    }

    private void graphical() {
        this.setResizable(false);
        this.setTitle(genTitle());

        VBox root = new VBox();
        HBox coord = new HBox();

        coord.getChildren().add(new Text(this.s.getPosition().toString()));
        Label type = new Label(genType());
        Label cap = new Label(String.valueOf(this.s.getStockage()));

        Button fermer = new Button("Fermer");

        root.getChildren().addAll(coord, type, cap, fermer);

        Scene s = new Scene(root,200, 100);
        this.setScene(s);
    }

    private String genTitle() {
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

    private String genType() {
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
}
