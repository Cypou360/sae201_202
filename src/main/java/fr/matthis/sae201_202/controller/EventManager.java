package fr.matthis.sae201_202.controller;

import fr.matthis.sae201_202.model.*;
import fr.matthis.sae201_202.view.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class EventManager implements EventHandler {

    private Main p;
    private Info info;

    public EventManager(Main p) {
        this.p = p;
    }

    public EventManager(Info info) {
        this.info = info;
    }

    @Override
    public void handle(Event e) {
        MouseEvent ev = ((MouseEvent) e);
        if (ev.getSource() instanceof Rectangle) {
            Rectangle r = (Rectangle) ev.getTarget();
            // detection cellule cliquer
            if (r.getParent().getParent().getId().equals("grille")) {
                int x = (int) r.getX() / p.getCellSize();
                int y = (int) r.getY() / p.getCellSize();
                Sector s = p.getGrid().getSector(x, y);
                try {
                    new InfoCell(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (ev.getSource() instanceof Button) {
            Button b = (Button) ev.getSource();
            if (b.getText().equals("Fermer")) {
                info.close();
            } else if (b.getText().equals("Quitter")) {
                p.close();
            } else if (b.getText().equals("Reset")) {
                p.getGrid().getRobots().get(0).setIdCounter(1);
                p.getGrid().getMines().get(0).setIdCounter(1);
                p.getGrid().getEntrepots().get(0).setIdCounter(1);
                p.getGrid().getRobots().clear();
                p.getGrid().getMines().clear();
                p.getGrid().getEntrepots().clear();
                p.close();
                launcher launcher = new launcher();
                try {
                    launcher.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else if (b.getText().equals("Auto")) {
                startAutoUpdate();
            } else if (b.getParent().getId().equals("action")){
                Stage stage = new Stage();
                Group group = new Group();
                Scene scene = new Scene(group);
                stage.setScene(scene);
                stage.setResizable(false);
                Group sideBar = (Group) b.getParent().getParent().getParent();
                ChoiceBox cb = (ChoiceBox) ((Group)sideBar.getChildren().get(1)).getChildren().get(0);
                if (!cb.getValue().equals("Selectionnez un robot")){
                    String valeur = (String) cb.getValue();
                    String nb = valeur.replace("Robot ", "");
                    int id = Integer.valueOf(nb);
                    Robots r = p.getGrid().getRobot(id);
                    if (cb.getItems().isEmpty()){
                        for (Robots ro : p.getGrid().getRobots()) {
                            String out = "Robot " + ro.getId();
                            cb.getItems().add(out);
                        }
                    }
                    int cpt = 0;
                    int idRobot = 0;
                    for (Object s: cb.getItems()){
                        if (s == cb.getValue()){
                            idRobot += cpt;
                        }else{
                            cpt += 1;
                        }
                    }
                    if(b.getText().equals("Extraire")){
                        if (p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY()) instanceof Mine) {
                            if (((Mine) p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY())).getType() == r.getType()) {
                                if (r.getCapacity() < r.getMaxCapacity()) {
                                    r.extraction(p.getGrid());
                                    cb.getItems().remove(idRobot);
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous êtes déja remplie !");
                                    alert.show();
                                }
                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Pas le bon type de mine!");
                                alert.show();
                            }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous êtes pas sur une mine !");
                            alert.show();
                        }
                    }
                    else if (b.getText().equals("Ne rien faire")){
                        cb.getItems().remove(idRobot);
                    }
                    else if (b.getText().equals("Déposer")){
                        if (p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY()) instanceof Entrepot) {
                            if (((Entrepot) p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY())).getType() == r.getType()) {
                                if (r.getCapacity() != 0) {
                                    r.deposer(p.getGrid());
                                    cb.getItems().remove(idRobot);
                                } else {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous êtes déja vide !");
                                    alert.show();
                                }
                            }else{
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Pas le bon type d'entrepôt !");
                                alert.show();
                            }
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous êtes pas sur un entrepôt !");
                            alert.show();
                        }
                    } else if (b.getText().equals("Nord")) {
                        if (r.getPosition().getY() > 0 && p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY()-1).getDisponible()) {
                            r.goTo("O", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Impossible d'aller au nord !");
                            alert.show();
                        }
                    } else if (b.getText().equals("Sud")) {
                        if (r.getPosition().getY() < 9 && p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY()+1).getDisponible()) {
                            r.goTo("E", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Impossible d'aller au sud !");
                            alert.show();
                        }
                    } else if (b.getText().equals("Est")) {
                        if (r.getPosition().getX() < 9 && p.getGrid().getSector(r.getPosition().getX()+1,r.getPosition().getY()).getDisponible()) {
                            r.goTo("S", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Impossible d'aller à l'est !");
                            alert.show();
                        }
                    } else if (b.getText().equals("Ouest")) {
                        if (r.getPosition().getX() > 0 && p.getGrid().getSector(r.getPosition().getX()-1,r.getPosition().getY()).getDisponible()) {
                            r.goTo("N", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Impossible d'aller à l'ouest !");
                            alert.show();
                        }
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Veuillez choisir un robot !");
                    alert.show();
                }
                try {
                    p.update();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (ev.getSource() instanceof VBox) {
            VBox v = (VBox) ev.getSource();
            if (v.getParent().getId().equals("robot")) {
                int x = (int) ((v.getLayoutX()) / p.getCellSize());
                int y = (int) ((v.getLayoutY()) / p.getCellSize());
                Robots r = p.getGrid().getSector(x, y).getRobot();
                try {
                    new InfoRobot(r);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else {
            System.out.println(e);
        }
    }

    private void executeAndUpdate() throws IOException {
        for (Robots r : p.getGrid().getRobots()) {
            r.automation(p.getGrid());

        }
        p.setNbTour(p.getNbTour()+1);
        p.setLabeltour(new Label("NbTour : " + String.valueOf(p.getNbTour())));
        p.update();
    }

    // Méthode pour démarrer la mise à jour automatique
    private void startAutoUpdate() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    executeAndUpdate();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Répéter indéfiniment
        timeline.play(); // Démarrer le timeline
    }
}








