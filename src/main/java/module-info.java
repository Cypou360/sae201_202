module fr.matthis.sae201_202 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;


    exports fr.matthis.sae201_202.model;
    opens fr.matthis.sae201_202.model to javafx.fxml;
    exports fr.matthis.sae201_202.controller;
    opens fr.matthis.sae201_202.controller to javafx.fxml;
    exports fr.matthis.sae201_202.view;
}