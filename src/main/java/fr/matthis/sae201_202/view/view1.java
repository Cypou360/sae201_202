package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import fr.matthis.sae201_202.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class view1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

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
        System.out.println(scene.getHeight());


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
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);

                    // Chargement de l'image
                    Image image = new Image("file:///C:/Users/matthis/IdeaProjects/sae201_202/src/main/java/fr/matthis/sae201_202/view/img/herbe.jpg");


                    // Utilisation de la texture d'image comme remplissage du rectangle
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);



                    g.getChildren().add(r);

                } else if (ss instanceof Entrepot) {
                    Entrepot e = ((Entrepot) ss);
                    Coordonnee pos = e.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);

                    Image image = new Image("file:///C:/Users/matthis/IdeaProjects/sae201_202/src/main/java/fr/matthis/sae201_202/view/img/Chest.png");

                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);

                    //String out = "E    " + e.getId();
                    Text t1 = new Text(50 + pos.getX() * cellsize + 10, 50 + pos.getY() * cellsize + 25,"");
                    t1.setFont(new Font(20));
                    g.getChildren().add(r);
                    g.getChildren().add(t1);

                } else if (ss instanceof Mine) {
                    Mine m = ((Mine) ss);
                    Coordonnee pos = m.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);

                    if (m.getType() == Ore.gold){
                        Image image = new Image("file:///C:/Users/matthis/IdeaProjects/sae201_202/src/main/java/fr/matthis/sae201_202/view/img/Gold.jpg");


                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);
                    }else{
                        Image image = new Image("file:///C:/Users/matthis/IdeaProjects/sae201_202/src/main/java/fr/matthis/sae201_202/view/img/FEr.jpg");


                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);
                    }


                    //String out = "M   " + m.getId();
                    Text t2 = new Text(50 + pos.getX() * cellsize + 10, 50 + pos.getY() * cellsize + 25, "");
                    t2.setFont(new Font(20));
                    g.getChildren().add(r);
                    g.getChildren().add(t2);

                } else if (ss instanceof Lac) {
                    Lac l = ((Lac) ss);
                    Coordonnee pos = l.getPosition();
                    Rectangle r = new Rectangle(50 + pos.getX() * cellsize, 50 + pos.getY() * cellsize, cellsize, cellsize);

                    Image image = new Image("file:///C:/Users/matthis/IdeaProjects/sae201_202/src/main/java/fr/matthis/sae201_202/view/img/eau.jpg");


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

            Image image = new Image("file:///C:/Users/matthis/IdeaProjects/sae201_202/src/main/java/fr/matthis/sae201_202/view/img/Robot.jpg");


            ImagePattern pattern = new ImagePattern(image);
            ro.setFill(pattern);
            //String out = "R    " + r.getId();
            Text t3 = new Text(50 + pos.getX() * cellsize + 10, 80 + pos.getY() * cellsize + 25, "");
            t3.setFont(new Font(20));
            g.getChildren().add(ro);
            g.getChildren().add(t3);
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