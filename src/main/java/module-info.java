module fr.matthis.sae201_202 {
    requires javafx.controls;
    requires org.junit.jupiter.api;
    requires junit;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    exports fr.matthis.sae201_202.model;
    exports fr.matthis.sae201_202.controller;
    exports fr.matthis.sae201_202.view;
    opens fr.matthis.sae201_202;
}