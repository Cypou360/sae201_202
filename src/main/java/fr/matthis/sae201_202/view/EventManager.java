package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.Robots;
import fr.matthis.sae201_202.model.Sector;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
                    new InfoCellule(s);
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
                        System.out.println(cb.getItems());
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
                        if (r.getCapacity() < r.getMaxCapacity()) {
                            r.extraction(p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Label l = new Label(" Capaciter déja au maximum ! ");
                            l.setFont(new Font(20));
                            group.getChildren().add(l);
                            stage.setTitle("Extraction");
                            Image image = null;
                            try {
                                image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.getIcons().add(image);
                            stage.show();
                        }
                    }
                    else if (b.getText().equals("Ne rien faire")){
                        cb.getItems().remove(idRobot);
                    }
                    else if (b.getText().equals("Déposer")){
                        if (r.getCapacity() != 0) {
                            r.deposer(p.getGrid());
                            cb.getItems().remove(idRobot);
                        }else {
                            Label l = new Label(" Depot pas possible ! ");
                            l.setFont(new Font(20));
                            group.getChildren().add(l);
                            stage.setTitle("Deposer");
                            Image image = null;
                            try {
                                image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.getIcons().add(image);
                            stage.show();
                        }
                    } else if (b.getText().equals("Nord")) {
                        if (r.getPosition().getY() > 0 && p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY()-1).getDisponible()) {
                            r.goTo("O", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Label l = new Label(" Impossible d'aller au nord ! ");
                            l.setFont(new Font(20));
                            group.getChildren().add(l);
                            stage.setTitle("Direction");
                            Image image = null;
                            try {
                                image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.getIcons().add(image);
                            stage.show();
                        }
                    } else if (b.getText().equals("Sud")) {
                        if (r.getPosition().getY() < 9 && p.getGrid().getSector(r.getPosition().getX(),r.getPosition().getY()+1).getDisponible()) {
                            r.goTo("E", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Label l = new Label(" Impossible d'aller au sud ! ");
                            l.setFont(new Font(20));
                            group.getChildren().add(l);
                            stage.setTitle("Direction");
                            Image image = null;
                            try {
                                image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.getIcons().add(image);
                            stage.show();
                        }
                    } else if (b.getText().equals("Est")) {
                        if (r.getPosition().getX() < 9 && p.getGrid().getSector(r.getPosition().getX()+1,r.getPosition().getY()).getDisponible()) {
                            r.goTo("S", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Label l = new Label(" Impossible d'aller a l'est ! ");
                            l.setFont(new Font(20));
                            group.getChildren().add(l);
                            stage.setTitle("Direction");
                            Image image = null;
                            try {
                                image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.getIcons().add(image);
                            stage.show();
                        }
                    } else if (b.getText().equals("Ouest")) {
                        if (r.getPosition().getX() > 0 && p.getGrid().getSector(r.getPosition().getX()-1,r.getPosition().getY()).getDisponible()) {
                            r.goTo("N", p.getGrid());
                            cb.getItems().remove(idRobot);
                        }
                        else {
                            Label l = new Label(" Impossible d'aller a l'ouest ! ");
                            l.setFont(new Font(20));
                            group.getChildren().add(l);
                            stage.setTitle("Direction");
                            Image image = null;
                            try {
                                image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            stage.getIcons().add(image);
                            stage.show();
                        }
                    }
                }else{
                    Label l = new Label(" Veuillez choisir un robot ! ");
                    l.setFont(new Font(20));
                    group.getChildren().add(l);
                    stage.setTitle("Choix");
                    Image image = null;
                    try {
                        image = new Image(Main.class.getResource("Panneau interdit.png").openStream());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    stage.getIcons().add(image);
                    stage.show();
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
}








