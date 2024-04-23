package fr.matthis.sae201_202.view;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;

import java.awt.*;

public class Mainview extends VBox {
    public Mainview(double spacing) {

        super(spacing);

        TextInputControl text = new TextField();

        ObservableList component = this.getChildren();

        component.addAll(text);

        if (text.equals("Salut")) {
            Label lab = new Label("TAGEUL");
            component.addAll(lab);
        }
    }
}
