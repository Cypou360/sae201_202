module fr.matthis.sae201_202 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;

    opens fr.matthis.sae201_202 to javafx.fxml;
    exports fr.matthis.sae201_202;
}