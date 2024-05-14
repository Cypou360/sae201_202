package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import fr.matthis.sae201_202.model.*;

import java.io.IOException;

public class view1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Grille grid = new Grille();
        grid.initialisation();

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        primaryStage.setTitle("Robot");
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        sideBar(root);
        gridgen(root, (int) scene.getHeight(), grid);

        primaryStage.show();
    }

    public void gridgen(Group g, int height, Grille grille) throws IOException {
        int cellsize = (height-100)/10;
        int prevX = 50;
        int prevY = 50;

        // dessin des Secteurs
        Sector[][] grid = grille.getGrille();
        for (Sector[] s : grid) {
            for (Sector ss : s) {
                if (ss instanceof Vide) {
                    Vide v = ((Vide) ss);
                    Coordonnee pos = v.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);
                    Image image = new Image(view1.class.getResource("herbe.jpg").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    g.getChildren().add(r);

                } else if (ss instanceof Entrepot) {
                    Entrepot e = ((Entrepot) ss);
                    Coordonnee pos = e.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);
                    Image image = new Image(view1.class.getResource("Chest.png").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);

                    String out = "E   " + e.getId();
                    Text t1 = new Text(50 + pos.getX() * cellsize + 10, 50 + pos.getY() * cellsize + 25, out);
                    t1.setFont(new Font(20));
                    g.getChildren().add(r);
                    g.getChildren().add(t1);

                } else if (ss instanceof Mine) {
                    Mine m = ((Mine) ss);
                    Coordonnee pos = m.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);

                    if (m.getMinerai() == Ore.gold) {
                        Image image = new Image(view1.class.getResource("Gold.jpg").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);
                    }
                    else{
                        Image image = new Image(view1.class.getResource("FEr.jpg").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);
                    }

                    String out = "M  " + m.getId();
                    Text t2 = new Text(50 + pos.getX() * cellsize + 10, 50 + pos.getY() * cellsize + 25, out);
                    t2.setFont(new Font(20));
                    g.getChildren().add(r);
                    g.getChildren().add(t2);

                } else if (ss instanceof Lac) {
                    Lac l = ((Lac) ss);
                    Coordonnee pos = l.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);
                    Image image = new Image(view1.class.getResource("eau.jpg").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    g.getChildren().add(r);
                }
            }
        }
        // dessin des robots
        Robots[] robots = grille.getRobots();
        for (Robots r : robots) {
            Coordonnee pos = r.getPosition();
            Rectangle ro = new Rectangle(50 + pos.getX()*cellsize, 80 + pos.getY()*cellsize, cellsize/2, cellsize/2);
            Image image = new Image(view1.class.getResource("Robot.jpg").openStream());
            ImagePattern pattern = new ImagePattern(image);
            ro.setFill(pattern);
            g.getChildren().add(ro);
        }
        // dessin de la grille
        for (int i = 0; i <= 10; i++) {
            Line l = new Line(prevX, 50, prevX, cellsize*10+50);
            prevX += cellsize;
            g.getChildren().add(l);

            Line l2 = new Line(50, prevY, cellsize*10+50, prevY);
            prevY += cellsize;
            g.getChildren().add(l2);
        }
    }

    public void sideBar(Group g) {
        Rectangle r = new Rectangle(850, 50, 600, 710);
        r.setFill(Color.WHITE);
        r.setStroke(Color.BLACK);

        VBox robot = new VBox();
        robot.setTranslateX(860);
        robot.setTranslateY(100);
        robot.setSpacing(10);
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.getItems().addAll("Robot 1", "Robot 2", "Robot 3", "Robot 4", "Robot 5");
        cb.setValue("Selectionnez un robot");
        robot.getChildren().add(cb);




        Line l = new Line(1150, 50, 1150, 200);
        Line l2 = new Line(850, 200, 1450, 200);
        Line l3 = new Line(850, 330, 1450, 330);

        Text t = new Text(860, 80, "Selectionnez un robot :");
        t.setFont(new Font(20));

        Text t2 = new Text(1160, 80, "Selectionnez une action :");
        t2.setFont(new Font(20));

        Text t3 = new Text(860, 230, "Selectionnez une direction :");
        t3.setFont(new Font(20));

        Text t4 = new Text(860, 360, "Récapitulatif :");
        t4.setFont(new Font(20));


        g.getChildren().addAll(r);
        g.getChildren().addAll(t, t2, t3, t4);
        g.getChildren().addAll(l, l2, l3);
        g.getChildren().add(robot);

    }
}