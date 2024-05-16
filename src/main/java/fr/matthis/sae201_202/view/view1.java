package fr.matthis.sae201_202.view;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
import java.util.Arrays;

public class view1 extends Application {

    private Sector[][] grid = new Grille().getGrille();

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
        primaryStage.setMaximized(true);
        Group root = new Group();
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        sideBar(root,(int) scene.getHeight(), (int) scene.getWidth(),grid);
        Stage stageRobot = new Stage();
        gridgen(root, (int) scene.getHeight(), grid, stageRobot);

        primaryStage.show();
    }

    public void gridgen(Group g, int height, Grille grille, Stage stage) throws IOException {
        Group groupGrille = new Group();
        eventManager evGrille = new eventManager(grille);
        int cellsize = (height-100)/10;
        int prevX = 0;
        int prevY = 0;

        // dessin des Secteurs
        Sector[][] grid = grille.getGrille();
        for (Sector[] s : grid) {
            for (Sector ss : s) {
                if (ss instanceof Vide) {
                    Vide v = ((Vide) ss);
                    Coordonnee pos = v.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * cellsize, pos.getY() * cellsize, cellsize, cellsize);
                    Image image = new Image(view1.class.getResource("herbe.jpg").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    groupGrille.getChildren().add(r);

                } else if (ss instanceof Entrepot) {
                    Entrepot e = ((Entrepot) ss);
                    Coordonnee pos = e.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * cellsize, pos.getY() * cellsize, cellsize, cellsize);
                    Rectangle r2 = new Rectangle(r.getX(),r.getY(),r.getWidth(),r.getHeight());

                    if (e.getType() == Ore.gold) {
                        Image image = new Image(view1.class.getResource("ChestOr.png").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(evGrille);
                    }
                    else{
                        Image image = new Image(view1.class.getResource("ChestFer.png").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(evGrille);
                    }

                    groupGrille.getChildren().add(r);
                    groupGrille.getChildren().add(r2);

                } else if (ss instanceof Mine) {
                    Mine m = ((Mine) ss);
                    Coordonnee pos = m.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * cellsize,  pos.getY() * cellsize, cellsize, cellsize);
                    Rectangle r2 = new Rectangle(r.getX(),r.getY(),r.getWidth(),r.getHeight());

                    if (m.getMinerai() == Ore.gold) {
                        Image image = new Image(view1.class.getResource("Gold.jpg").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(evGrille);
                    }
                    else{
                        Image image = new Image(view1.class.getResource("Fer.jpg").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(evGrille);
                    }

                    groupGrille.getChildren().add(r);
                    groupGrille.getChildren().add(r2);

                } else if (ss instanceof Lac) {
                    Lac l = ((Lac) ss);
                    Coordonnee pos = l.getPosition();
                    Rectangle r = new Rectangle( pos.getX() * cellsize,  pos.getY() * cellsize, cellsize, cellsize);
                    Image image = new Image(view1.class.getResource("eau.jpg").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    groupGrille.getChildren().add(r);
                }
            }
        }
        Group groupRobot = new Group();
        // dessin des robots
        Robots[] robots = grille.getRobots();
        for (Robots r : robots) {
            EventRobot ev=new EventRobot(grille,r,stage);
            Coordonnee pos = r.getPosition();
            Rectangle ro = new Rectangle(50 + pos.getX()*cellsize, 50 + pos.getY()*cellsize + cellsize/2, cellsize/2, cellsize/2);
            Rectangle ro2 = new Rectangle(96 + pos.getX()*cellsize, 50 + pos.getY()*cellsize + cellsize/2, cellsize/2, cellsize/2);
            Rectangle r2 = new Rectangle(ro.getX(),ro.getY(),ro.getWidth(),ro.getHeight());

            if (r.getType() == Ore.gold){
                Image image = new Image(view1.class.getResource("Alex.jpg").openStream());
                ImagePattern pattern = new ImagePattern(image);
                ro.setFill(pattern);

                r2.setFill(pattern);
                r2.setOnMouseClicked(ev);
                if ((grille.getSector(r.getPosition().getX(),r.getPosition().getY()) instanceof Mine) && (((Mine) grille.getSector(r.getPosition().getX(),r.getPosition().getY())).getType() == Ore.gold)){
                    Image image1 = new Image(view1.class.getResource("GoldPioche.jpg").openStream());
                    ImagePattern pattern1 = new ImagePattern(image1);
                    ro2.setFill(pattern1);
                    g.getChildren().add(ro2);

                }
            }else{
                Image image = new Image(view1.class.getResource("Robot.jpg").openStream());
                ImagePattern pattern = new ImagePattern(image);
                ro.setFill(pattern);

                r2.setFill(pattern);
                r2.setOnMouseClicked(ev);
                if ((grille.getSector(r.getPosition().getX(),r.getPosition().getY()) instanceof Mine) && (((Mine) grille.getSector(r.getPosition().getX(),r.getPosition().getY())).getType() == Ore.nickel)){
                    Image image1 = new Image(view1.class.getResource("FerPioche.jpg").openStream());
                    ImagePattern pattern1 = new ImagePattern(image1);
                    ro2.setFill(pattern1);
                    g.getChildren().add(ro2);
                }
            }

            String out = " " + r.getId();
            Text t = new Text( 45 + pos.getX() * cellsize + 10, 50 + pos.getY() * cellsize + 25, out);
            t.setFont(new Font(20));
            t.setFill(Color.GOLD);
            groupRobot.getChildren().add(ro);
            groupRobot.getChildren().add(r2);
            groupRobot.getChildren().add(t);

}



        // dessin de la grille
        for (int i = 0; i <= 10; i++) {
            Line l = new Line(prevX, 0, prevX, cellsize*10);
            prevX += cellsize;
            groupGrille.getChildren().add(l);

            Line l2 = new Line(0, prevY, cellsize*10, prevY);
            prevY += cellsize;
            groupGrille.getChildren().add(l2);
        }

        groupGrille.setLayoutX(50);
        groupGrille.setLayoutY(50);
        g.getChildren().add(groupGrille);
        g.getChildren().add(groupRobot);
    }

    public void sideBar(Group g,int h,int w,Grille grille){

        Group sidebar = new Group();
        Rectangle rectangle = new Rectangle(850,50,600,720);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        Group line = new Group();
        Line l = new Line(1150, 50, 1150, 200);
        Line l2 = new Line(850, 200, 1450, 200);
        Line l3 = new Line(850, 330, 1450, 330);
        line.getChildren().addAll(l,l2,l3);

        Group robot = new Group();
        robot.setTranslateX(860);
        robot.setTranslateY(120);

        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.setPrefSize(200, 30);
        cb.setStyle("-fx-font: 15px \"None\";");
        Robots[] robots = grille.getRobots();
        for (Robots ro: robots){
            String out = "Robot " + ro.getId();
            cb.getItems().add(out);
            }
        cb.setValue("Selectionnez un robot");
        robot.getChildren().add(cb);

        Label label = new Label("Selectionnez un robot");
        label.setFont(new Font(20));
        label.setTranslateX(0);
        label.setTranslateY(-60);
        robot.getChildren().add(label);

        Group action = new Group();
        Button b = new Button("Extraire");
        Button b2 = new Button("Déposer");
        Label text = new Label("Selectionnez une action :");
        text.setFont(new Font(20));
        action.setTranslateX(1160);
        action.setTranslateY(60);

        action.getChildren().addAll(b,b2,text);



        /*Button d1 = new Button("Nord");
        d1.setTranslateX(860);
        d1.setTranslateY(260);
        d1.setPrefSize(90, 30);
        d1.setFont(new Font(15));

        Button d2 = new Button("Sud");
        d2.setTranslateX(980);
        d2.setTranslateY(260);
        d2.setPrefSize(90, 30);
        d2.setFont(new Font(15));

        Button d3 = new Button("Est");
        d3.setTranslateX(1100);
        d3.setTranslateY(260);
        d3.setPrefSize(90, 30);
        d3.setFont(new Font(15));

        Button d4 = new Button("Ouest");
        d4.setTranslateX(1220);
        d4.setTranslateY(260);
        d4.setPrefSize(90, 30);
        d4.setFont(new Font(15));







        Text t3 = new Text(860, 230, "Selectionnez une direction :");
        t3.setFont(new Font(20));

        Text t4 = new Text(860, 360, "Récapitulatif :");
        t4.setFont(new Font(20));*/

        g.getChildren().add(sidebar);
        sidebar.getChildren().addAll(rectangle);
        sidebar.getChildren().addAll(robot);
        sidebar.getChildren().addAll(line);
        sidebar.getChildren().addAll(action);
    }


}