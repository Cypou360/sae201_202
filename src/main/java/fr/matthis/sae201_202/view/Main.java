package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.model.*;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Stage {

    private int cellSize;
    private Grille grid;
    private int nbTour;
    private double height;

    public int getCellSize() {
        return cellSize;
    }

    public Grille getGrid() {
        return grid;
    }

    public int getNbTour() {
        return nbTour++;
    }

    public Main() throws IOException {
        super();
        graphical();
        this.show();
    }

    private void graphical() throws IOException { //Methode pour afficher l'ensemble des elements
        this.grid = new Grille();
        this.grid.initialisation();

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds(); //Responsive avec taille d'ecran de l'utilisateur
        double width = screenSize.getWidth();
        this.height = screenSize.getHeight();

        this.cellSize = (int) ((height - 100) / 10); //taille d'un Secteur en graphique

        this.setTitle("Robot");
        this.setMaximized(true);
        HBox root = new HBox();
        Scene scene = new Scene(root, width, height);
        this.setScene(scene);

        gridgen(root, this.grid); //Methode generation de la grille
        Rectangle espace = new Rectangle(50, height);
        espace.setFill(Color.TRANSPARENT);
        root.getChildren().add(espace);
        root.getChildren().add(sideBar());

        root.setLayoutY(50);
        root.setLayoutX(50);

        Image image = new Image(Main.class.getResource("icon.png").openStream()); //Texture à partir du doisser ressources
        this.getIcons().add(image);

        Image image2 = new Image(Main.class.getResource("MinecraftTexture.jpg").openStream()); //Texture
        scene.setFill(new ImagePattern(image2));
    }

    public void gridgen(HBox g, Grille grille) throws IOException { //Methode generation grille
        Group generalGrille = new Group();
        Group groupGrille = new Group();
        EventManager emgr = new EventManager(this);

        groupGrille.setId("grille");

        int prevX = 0;
        int prevY = 0;

        // dessin des Secteurs
        Sector[][] grid = grille.getGrille();
        for (Sector[] s : grid) {
            for (Sector ss : s) {
                if (ss instanceof Vide) {
                    Vide v = ((Vide) ss);
                    Coordonnee pos = v.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    Image image = new Image(launcher.class.getResource("herbe.jpg").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    groupGrille.getChildren().add(r);
                    r.setOnMouseClicked(emgr);

                } else if (ss instanceof Entrepot) {
                    Entrepot e = ((Entrepot) ss);
                    Coordonnee pos = e.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    Rectangle r2 = new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());

                    if (e.getType() == Ore.gold) {
                        Image image = new Image(launcher.class.getResource("ChestOr.png").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    } else {
                        Image image = new Image(launcher.class.getResource("ChestNickel.png").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    }

                    groupGrille.getChildren().add(r);
                    groupGrille.getChildren().add(r2);

                } else if (ss instanceof Mine) {
                    Mine m = ((Mine) ss);
                    Coordonnee pos = m.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    Rectangle r2 = new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());

                    if (m.getMinerai() == Ore.gold) {
                        Image image = new Image(launcher.class.getResource("Gold.jpg").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    } else {
                        Image image = new Image(launcher.class.getResource("Nickel.jpg").openStream());
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    }

                    groupGrille.getChildren().add(r);
                    groupGrille.getChildren().add(r2);

                } else if (ss instanceof Lac) {
                    Lac l = ((Lac) ss);
                    Coordonnee pos = l.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    Image image = new Image(launcher.class.getResource("eau.jpg").openStream());
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    groupGrille.getChildren().add(r);
                    r.setOnMouseClicked(emgr);
                }
            }
        }

        Group groupRobot = new Group();
        groupRobot.setId("robot");
        // dessin des robots
        ArrayList<Robots> robots = grille.getRobots();
        for (Robots r : robots) {
            Coordonnee pos = r.getPosition();
            Rectangle ro = new Rectangle(this.cellSize / 2, this.cellSize / 2); // steve ou alex

            VBox robot = new VBox();
            HBox hRobot = new HBox();
            robot.setOnMouseClicked(emgr);

            hRobot.getChildren().add(ro);

            if (r.getType() == Ore.gold) {
                Image image = new Image(launcher.class.getResource("Steve.jpg").openStream());
                ImagePattern pattern = new ImagePattern(image);
                ro.setFill(pattern);
                if ((grille.getSector(r.getPosition().getX(), r.getPosition().getY()) instanceof Mine) && (((Mine) grille.getSector(r.getPosition().getX(), r.getPosition().getY())).getType() == Ore.gold)) {
                    Image image1 = new Image(launcher.class.getResource("pioche.png").openStream());
                    ImagePattern pattern1 = new ImagePattern(image1);
                    Rectangle ro2 = new Rectangle(this.cellSize / 2, this.cellSize / 2); // pioche
                    ro2.setFill(pattern1);
                    hRobot.getChildren().add(ro2);
                    r.setPioche(true);
                }
            } else {
                Image image = new Image(launcher.class.getResource("Alex.jpg").openStream());
                ImagePattern pattern = new ImagePattern(image);
                ro.setFill(pattern);
                if ((grille.getSector(r.getPosition().getX(), r.getPosition().getY()) instanceof Mine) && (((Mine) grille.getSector(r.getPosition().getX(), r.getPosition().getY())).getType() == Ore.nickel)) {
                    Image image1 = new Image(launcher.class.getResource("pioche.png").openStream());
                    ImagePattern pattern1 = new ImagePattern(image1);
                    Rectangle ro2 = new Rectangle(this.cellSize / 2, this.cellSize / 2); // pioche
                    ro2.setFill(pattern1);
                    hRobot.getChildren().add(ro2);
                    r.setPioche(true);
                }
            }

            String out = " " + r.getId();
            Text t = new Text(out);
            t.setFont(new Font(this.cellSize / 3 + 3));
            t.setFill(Color.GOLD);

            hRobot.setLayoutY(this.cellSize / 2);
            robot.getChildren().addAll(t, hRobot);
            robot.setLayoutX(pos.getX() * this.cellSize);
            robot.setLayoutY(pos.getY() * this.cellSize);
            groupRobot.getChildren().add(robot);

        }
        // dessin de la grille
        for (int i = 0; i <= 10; i++) {
            Line l = new Line(prevX, 0, prevX, this.cellSize * 10);
            prevX += this.cellSize;
            groupGrille.getChildren().add(l);

            Line l2 = new Line(0, prevY, this.cellSize * 10, prevY);
            prevY += this.cellSize;
            groupGrille.getChildren().add(l2);
        }

        generalGrille.getChildren().add(groupGrille);
        generalGrille.getChildren().add(groupRobot);

        g.getChildren().add(generalGrille);
    }

    public Group sideBar() throws IOException {

        EventManager emgr = new EventManager(this);

        Grille grille = this.grid;
        int h = (int) this.height;

        // Création du groupe pour la sideBar et du rectangle pour mettre les actions.
        Group sidebar = new Group();
        Rectangle rectangle = new Rectangle(850, 50, 600, h - 105);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        // Création des lignes pour séparer les différentes actions.
        Group line = new Group();
        Line l = new Line(1150, 50, 1150, 200);
        Line l2 = new Line(850, 200, 1450, 200);
        Line l3 = new Line(850, 310, 1450, 310);
        line.getChildren().addAll(l, l2, l3);

        // Création du groupe pour le choix du robot.
        Group robot = new Group();
        robot.setTranslateX(860);
        robot.setTranslateY(120);

        //Création du choiceBox pour choisir le robot.
        ChoiceBox<String> cb = new ChoiceBox<>();
        cb.setId("idRobot");
        cb.setPrefSize(200, 30);
        cb.setStyle("-fx-font: 15px \"None\";");

        // Ajout des robots dans le choiceBox.
        ArrayList<Robots> robots = grille.getRobots();
        if (!(robots.size() < grille.getNbRobot())) {
            for (Robots ro : robots) {
                String out = "Robot " + ro.getId();
                cb.getItems().add(out);
            }
        }

        cb.setValue("Selectionnez un robot ");
        robot.getChildren().add(cb);
        robot.setId("GroupeCb");

        // Ajout du label pour le choix du robot.
        Label label = new Label("Selectionnez un robot");
        label.setFont(new Font(20));
        label.setTranslateX(0);
        label.setTranslateY(-60);
        robot.getChildren().add(label);

        // Création du groupe pour les actions extraire et déposer
        Group action = new Group();
        Group boutonAct = new Group();
        boutonAct.setId("action");
        Button b = new Button("Extraire");
        Button b2 = new Button("Déposer");
        b.setStyle("-fx-font: 15px \"None\";");
        b.setPrefSize(90, 30);
        b2.setStyle("-fx-font: 15px \"None\";");
        b2.setPrefSize(90, 30);

        // Positionnement des boutons et création du label
        Label text = new Label("Selectionnez une action :");
        text.setFont(new Font(20));
        action.setTranslateX(1160);
        action.setTranslateY(60);
        b.setTranslateY(60);
        b2.setTranslateY(60);
        b2.setTranslateX(150);

        // Ajout des boutons dans le groupe action
        boutonAct.getChildren().addAll(b, b2);
        action.getChildren().addAll(text, boutonAct);

        // Ajout des événements sur les boutons extraire et déposer
        b.setOnMouseClicked(emgr);
        b2.setOnMouseClicked(emgr);

        // Création du groupe pour la direction
        Group direction = new Group();
        Label text2 = new Label("Selectionnez une direction :");
        text2.setFont(new Font(20));
        text2.setTranslateX(860);
        text2.setTranslateY(210);

        // Création des boutons pour les directions
        Group boutonDir = new Group();
        boutonDir.setId("action");
        Button d1 = new Button("Nord");
        d1.setPrefSize(90, 30);
        d1.setFont(new Font(15));
        d1.setTranslateX(860);
        d1.setTranslateY(250);

        Button d2 = new Button("Sud");
        d2.setPrefSize(90, 30);
        d2.setFont(new Font(15));
        d2.setTranslateX(960);
        d2.setTranslateY(250);

        Button d3 = new Button("Est");
        d3.setPrefSize(90, 30);
        d3.setFont(new Font(15));
        d3.setTranslateX(1160);
        d3.setTranslateY(250);

        Button d4 = new Button("Ouest");
        d4.setPrefSize(90, 30);
        d4.setFont(new Font(15));
        d4.setTranslateX(1060);
        d4.setTranslateY(250);

        // Ajout des boutons dans le groupe direction
        boutonDir.getChildren().addAll(d1, d2, d3, d4);
        direction.getChildren().addAll(text2, boutonDir);

        // Ajout des événements sur les boutons de direction
        d1.setOnMouseClicked(emgr);
        d2.setOnMouseClicked(emgr);
        d3.setOnMouseClicked(emgr);
        d4.setOnMouseClicked(emgr);

        // Création du groupe pour le récapitulatif
        Group recap = new Group();
        Label text3 = new Label("Recapitulatif :");
        text3.setFont(new Font(20));
        text3.setTranslateX(860);
        text3.setTranslateY(330);
        recap.getChildren().addAll(text3);

        VBox recapText = generateRecap(grille);
        recapText.setStyle("-fx-font: 15px \"None\";");
        recapText.setTranslateX(860);
        recapText.setTranslateY(360);

        // Ajout du récapitulatif dans le groupe recap
        recap.getChildren().add(recapText);

        // Création du groupe pour le bouton quitter
        Group exit = new Group();
        Button e1 = new Button("Quitter");
        e1.setPrefSize(90, 30);
        e1.setFont(new Font(15));
        e1.setTranslateX(1330);
        e1.setTranslateY(h - 120);

        // Ajout du bouton quitter dans le groupe exit
        exit.getChildren().add(e1);

        // Ajout de l'événement sur le bouton quitter
        e1.setOnMouseClicked(emgr);

        // Création du groupe pour le bouton reset
        Group reset = new Group();
        Button r1 = new Button("Reset");
        r1.setPrefSize(90, 30);
        r1.setFont(new Font(15));
        r1.setTranslateX(900);
        r1.setTranslateY(h - 120);

        // Ajout du bouton reset dans le groupe reset
        reset.getChildren().add(r1);

        // Ajout de l'événement sur le bouton reset
        r1.setOnMouseClicked(emgr);

        // Création du groupe pour le bouton ne rien faire
        Group rienFaire = new Group();
        Button rF = new Button("Ne rien faire");
        rF.setPrefSize(150, 30);
        rF.setFont(new Font(15));
        rF.setTranslateX(1080);
        rF.setTranslateY(h - 120);

        // Ajout du bouton ne rien faire dans le groupe ne rien faire
        rienFaire.getChildren().add(rF);

        // Ajout des groupes dans le groupe sidebar
        sidebar.getChildren().addAll(rectangle, robot, line, action, direction, recap, exit, reset);
        return sidebar;
    }

    public VBox generateRecap(Grille grid) throws IOException { //Genration du recapitulatif dans la barre lateral
        VBox recap = new VBox();
        ArrayList<Robots> robots = grid.getRobots(); //Recuperer les robots depuis la Grille

        Label espace = new Label("\n"); //Different espace entres les Secteur
        Label espace1 = new Label("\n");
        Label espace2 = new Label("\n");

        espace.setFont(new Font(cellSize / 10));
        espace1.setFont(new Font(cellSize / 10));
        espace2.setFont(new Font(cellSize / 10));

        VBox vRobot = new VBox();
        vRobot.setId("boxRobot");
        //Ajout robots recap
        for (Robots r : robots) {
            HBox robotInfo = new HBox();
            Rectangle r3 = new Rectangle(20, 20);
            if (r.getType() == Ore.gold) {
                Image image = new Image(Main.class.getResource("Steve.jpg").openStream());
                Label position = new Label(" Robot ID: " + r.getId() + " | " + " X: " + r.getPosition().getX() + " Y: " + r.getPosition().getY() + " | " + " Capacity: " + r.getCapacity() + "/" + r.getMaxCapacity() + " | " + " Type: " + "OR");
                ImagePattern pattern = new ImagePattern(image);
                r3.setFill(pattern);
                position.setFont(new Font(cellSize / 6));
                robotInfo.getChildren().addAll(r3, position);
                vRobot.getChildren().add(robotInfo);
            } else {
                Image image = new Image(Main.class.getResource("Alex.jpg").openStream());
                Label position = new Label(" Robot ID: " + r.getId() + " | " + " X: " + r.getPosition().getX() + " Y: " + r.getPosition().getY() + " | " + " Capacity: " + r.getCapacity() + "/" + r.getMaxCapacity() + " | " + " Type: " + "NI");
                ImagePattern pattern = new ImagePattern(image);
                r3.setFill(pattern);
                position.setFont(new Font(cellSize / 6));
                robotInfo.getChildren().addAll(r3, position);
                vRobot.getChildren().add(robotInfo);
            }
        }
        recap.getChildren().addAll(espace2, vRobot, espace);

        VBox vMine = new VBox();
        vMine.setId("boxMine");
        //Ajout mine recap
        for (Mine mine : grid.getMines()) {
            HBox mineInfo = new HBox();

            Rectangle r4 = new Rectangle(20, 20);
            if (mine.getMinerai() == Ore.gold) {
                Image image = new Image(Main.class.getResource("Gold.jpg").openStream());
                Label mines = new Label(" Mine ID: " + mine.getId() + " | " + " X: " + mine.getPosition().getX() + " Y: " + mine.getPosition().getY() + " | " + "Capacity: " + mine.getStockage() + "/" + mine.getmaxStockage() + " | " + " Type: " + "OR");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                mines.setFont(new Font(cellSize / 6));
                mineInfo.getChildren().addAll(r4, mines);
                vMine.getChildren().add(mineInfo);
            } else if (mine.getMinerai() == Ore.nickel) {
                Image image = new Image(Main.class.getResource("Nickel.jpg").openStream());
                Label mines = new Label(" Mine ID: " + mine.getId() + " | " + " X: " + mine.getPosition().getX() + " Y: " + mine.getPosition().getY() + " | " + "Capacity: " + mine.getStockage() + "/" + mine.getmaxStockage() + " | " + " Type: " + "NI");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                mines.setFont(new Font(cellSize / 6));
                mineInfo.getChildren().addAll(r4, mines);
                vMine.getChildren().add(mineInfo);
            }
        }
        recap.getChildren().addAll(vMine, espace1);

        VBox vEntre = new VBox();
        vEntre.setId("boxEntre");
        for (Entrepot entrepot : grid.getEntrepots()) {
            Rectangle r4 = new Rectangle(20,20);
            HBox entrepotInfo = new HBox();
            if (entrepot.getType() == Ore.gold) {

                Image image = new Image(Main.class.getResource("ChestOr.png").openStream());
                Label entrepots = new Label(" Entrepot ID: " + entrepot.getId() + " | " + " X: " + entrepot.getPosition().getX() + " Y: " + entrepot.getPosition().getY() + " | " + " Capacity: " + entrepot.getStockage() + " | " + " Type: " + "OR");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                entrepots.setFont(new Font(cellSize / 6));
                entrepotInfo.getChildren().addAll(r4, entrepots);
                vEntre.getChildren().add(entrepotInfo);
            } else {
                Image image = new Image(Main.class.getResource("ChestNickel.png").openStream());
                Label entrepots = new Label(" Entrepot ID: " + entrepot.getId() + " | " + " X: " + entrepot.getPosition().getX() + " Y: " + entrepot.getPosition().getY() + " | " + " Capacity: " + entrepot.getStockage() + " | " + " Type: " + "NI");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                entrepots.setFont(new Font(cellSize / 6));
                entrepotInfo.getChildren().addAll(r4, entrepots);
                vEntre.getChildren().add(entrepotInfo);
            }
        }
        recap.getChildren().add(vEntre);
        return recap;
    }

    public void update() throws IOException { //Methode mise à jour en temps reel
        // update affichage robot
        Group rg = (Group) ((Group) ((HBox) this.getScene().getRoot()).getChildren().getFirst()).getChildren().get(1);
        for (int i = 0 ; i < this.grid.getRobots().size() ; i++) {
            Robots r = this.grid.getRobots().get(i);
            VBox rb = (VBox) rg.getChildren().get(i);
            Coordonnee pos = r.getPosition();
            rb.setLayoutX(pos.getX() * this.cellSize);
            rb.setLayoutY(pos.getY() * this.cellSize);

            HBox hRobot = (HBox) rb.getChildren().getLast();

            Sector s = this.getGrid().getSector(pos.getX(), pos.getY());

            if (s instanceof Mine && r.getCapacity() < r.getMaxCapacity() && (((Mine) s).getMinerai() == r.getType())) {
                if (((Mine) s).getType() == r.getType() && !r.isPioche()) {
                    Image image1 = new Image(launcher.class.getResource("pioche.png").openStream());
                    ImagePattern pattern1 = new ImagePattern(image1);
                    Rectangle ro2 = new Rectangle(this.cellSize / 2, this.cellSize / 2); // pioche
                    ro2.setFill(pattern1);
                    hRobot.getChildren().add(ro2);
                    r.setPioche(true);
                }
            } else {
                if (hRobot.getChildren().size() != 1) {
                    hRobot.getChildren().remove(1);
                    r.setPioche(false);
                }
            }
        }

        //update recap
        ((HBox) this.getScene().getRoot()).getChildren().removeLast();
        ((HBox) this.getScene().getRoot()).getChildren().add(sideBar());

    }
}