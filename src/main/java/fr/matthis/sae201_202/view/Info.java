package fr.matthis.sae201_202.view;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class Info extends Stage {
    protected VBox root;
    protected HBox coord;

    public Info() {
        super();
        this.setResizable(false);

        this.root = new VBox();
        this.coord = new HBox();

        this.root.getChildren().add(coord);

        Scene s = new Scene(root,200,100);
        this.setScene(s);
    }
    protected abstract String genTitle();
    protected abstract String genType();
}
