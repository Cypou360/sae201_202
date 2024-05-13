package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import fr.matthis.sae201_202.model.*;

public class view1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

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

    public void gridgen(Group g, int height, Grille grille) {
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
                    Rectangle r = new Rectangle(50 + pos.getX()*cellsize, 50 + pos.getY()*cellsize, cellsize, cellsize);
                    r.setFill(Color.GREEN);
                    g.getChildren().add(r);

                } else if (ss instanceof Entrepot) {
                    Entrepot e = ((Entrepot) ss);
                    Coordonnee pos = e.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX()*cellsize, 50 + pos.getY()*cellsize, cellsize, cellsize);
                    r.setFill(Color.BROWN);
                    String type = e.getType().toString();
                    Text t = new Text(50 + pos.getX()*cellsize + 10, 50 + pos.getY()*cellsize + 10, type);
                    g.getChildren().add(r);
                    g.getChildren().add(t);

                } else if (ss instanceof Mine) {
                    Mine m = ((Mine) ss);
                    Coordonnee pos = m.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX()*cellsize, 50 + pos.getY()*cellsize, cellsize, cellsize);
                    r.setFill(Color.GRAY);
                    g.getChildren().add(r);

                } else if (ss instanceof Lac) {
                    Lac l = ((Lac) ss);
                    Coordonnee pos = l.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX()*cellsize, 50 + pos.getY()*cellsize, cellsize, cellsize);
                    r.setFill(Color.BLUE);
                    g.getChildren().add(r);
                }
            }
        }
        // dessin des robots
        Robots[] robots = grille.getRobots();
        for (Robots r : robots) {
            Coordonnee pos = r.getPosition();
            Rectangle ro = new Rectangle(50 + pos.getX()*cellsize, 50 + pos.getY()*cellsize, cellsize/2, cellsize/2);
            ro.setFill(Color.RED);
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

        Line l = new Line(1150, 50, 1150, 760);
        g.getChildren().addAll(r, l);



    }
}