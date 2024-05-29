package fr.matthis.sae201_202.view;

import fr.matthis.sae201_202.controller.EventManager;
import fr.matthis.sae201_202.model.*;

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
    private int nbTour = -1;
    private Label labeltour = null;
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

    public ChoiceBox<String> cb;

    private Automatique grilleV2;

    public Main() throws IOException {
        super();
        this.cb = new ChoiceBox<>();
        graphical();
        this.grilleV2 = new Automatique(this.grid);
        this.show();
    }

    private void graphical() throws IOException {//Methode pour afficher l'ensemble des elements
        this.grid = new Grille();
        this.grid.initialisation();

        int mgold = 0;
        int mnickel = 0;
        int eAll = 0;

        for (Mine m : this.grid.getMines()){
            if (m.getMinerai() == Ore.gold){
                mgold += m.getmaxStockage();
            }else{
                mnickel += m.getmaxStockage();
            }
        }
        for (Entrepot e: this.grid.getEntrepots()){
            eAll += e.getStockage();
        }

        if (eAll == (mnickel+mgold)){
            Stage a = new Stage();
            Group g = new Group();
            Scene s = new Scene(g,200,200);

            Label l = new Label("Partie Terminer !");
            l.setLayoutX(25);
            l.setLayoutY(75);
            l.setFont(new Font(20));
            g.getChildren().add(l);

            a.setScene(s);

            a.show();

        }

        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds(); //Responsive avec taille d'ecran de l'utilisateur
        double width = screenSize.getWidth();
        this.height = screenSize.getHeight();

        this.cellSize = (int) ((height - 100) / 10); //taille d'un Secteur en graphique

        this.setTitle("Robot");
        this.setMaximized(true);
        HBox root = new HBox();
        Scene scene = new Scene(root, width, height);
        scene.setFill(Color.TRANSPARENT);
        this.setScene(scene);

        gridgen(root, this.grid); //Methode generation de la grille
        Rectangle espace = new Rectangle(cellSize*2.5, height);
        espace.setFill(Color.TRANSPARENT);
        root.getChildren().add(espace);
        root.getChildren().add(sideBar());

        root.setLayoutY(50);
        root.setLayoutX(50);
        root.setStyle("-fx-background-color: transparent;");

        String imagePath = "/images/Icon.png";
        Image image = new Image(getClass().getResourceAsStream(imagePath)); //Texture à partir du dossier ressources
        this.getIcons().add(image);

        String imagePath2 = "/images/MinecraftTexture.png";
        Image image2 = new Image(getClass().getResourceAsStream(imagePath2)); //Texture
        scene.setFill(new ImagePattern(image2));
    }

    public void gridgen(HBox g, Grille grille) throws IOException { //Methode generation grille
        Group generalGrille = new Group();
        Group groupGrille = new Group();
        Group Mine = new Group();
        Group Entrepot = new Group();
        Group Lac = new Group();
        Group Vide = new Group();
        EventManager emgr = new EventManager(this);

        groupGrille.setId("grille");

        int prevX = 0;
        int prevY = 0;

        grilleV2.dejaExplorer();
        // dessin des Secteurs
        for (Sector[] s : grilleV2.getGrilleV2()) {
            for (Sector ss : s) {
                if (ss instanceof Vide) {
                    Vide v = ((Vide) ss);
                    Coordonnee pos = v.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    String imagePath3 = "/images/Herbe.jpg";
                    Image image = new Image(getClass().getResourceAsStream(imagePath3));
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    Vide.getChildren().add(r);
                    r.setOnMouseClicked(emgr);
                } else if (ss instanceof Entrepot) {
                    Entrepot e = ((Entrepot) ss);
                    Coordonnee pos = e.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    Rectangle r2 = new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                    Text l = new Text("" + e.getId());
                    l.setFont(new Font(this.cellSize / 3 + 3));
                    l.setFill(Color.BLUE);
                    l.setLayoutX(e.getPosition().getX() * this.cellSize + this.cellSize/1.5);
                    l.setLayoutY(e.getPosition().getY() * this.cellSize + this.cellSize/2.5);

                    if (e.getType() == Ore.gold) {
                        String imagePath4 = "/images/ChestOr.png";
                        Image image = new Image(getClass().getResourceAsStream(imagePath4));
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    } else {
                        String imagePath5 = "/images/ChestNickel.png";
                        Image image = new Image(getClass().getResourceAsStream(imagePath5));
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    }
                    Entrepot.getChildren().addAll(r,r2,l);
                } else if (ss instanceof Mine) {
                    Mine m = ((Mine) ss);
                    Coordonnee pos = m.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    Rectangle r2 = new Rectangle(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                    Text l = new Text("" + m.getId());
                    l.setFont(new Font(this.cellSize / 3 + 3));
                    l.setFill(Color.RED);
                    l.setLayoutX(m.getPosition().getX() * this.cellSize + this.cellSize/1.5);
                    l.setLayoutY(m.getPosition().getY() * this.cellSize + this.cellSize/2.5);
                    if (m.getMinerai() == Ore.gold) {
                        String imagePath6 = "/images/Gold.jpg";
                        Image image = new Image(getClass().getResourceAsStream(imagePath6));
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    } else {
                        String imagePath7 = "/images/Nickel.jpg";
                        Image image = new Image(getClass().getResourceAsStream(imagePath7));
                        ImagePattern pattern = new ImagePattern(image);
                        r.setFill(pattern);

                        r2.setFill(pattern);
                        r2.setOnMouseClicked(emgr);
                    }
                    Mine.getChildren().addAll(r,r2,l);
                } else if (ss instanceof Lac) {
                    Lac l = ((Lac) ss);
                    Coordonnee pos = l.getPosition();
                    Rectangle r = new Rectangle(pos.getX() * this.cellSize, pos.getY() * this.cellSize, this.cellSize, this.cellSize);
                    String imagePath8 = "/images/Eau.jpg";
                    Image image = new Image(getClass().getResourceAsStream(imagePath8));
                    ImagePattern pattern = new ImagePattern(image);
                    r.setFill(pattern);
                    Lac.getChildren().add(r);
                    r.setOnMouseClicked(emgr);
                }
            }
        }
        groupGrille.getChildren().add(Vide);
        groupGrille.getChildren().add(Mine);
        groupGrille.getChildren().add(Entrepot);
        groupGrille.getChildren().add(Lac);
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
                String imagePath9 = "/images/Steve.jpg";
                Image image = new Image(getClass().getResourceAsStream(imagePath9));
                ImagePattern pattern = new ImagePattern(image);
                ro.setFill(pattern);
                if ((grille.getSector(r.getPosition().getX(), r.getPosition().getY()) instanceof Mine) && (((Mine) grille.getSector(r.getPosition().getX(), r.getPosition().getY())).getType() == Ore.gold)) {
                    String imagePath10 = "/images/Pioche.png";
                    Image image1 = new Image(getClass().getResourceAsStream(imagePath10));
                    ImagePattern pattern1 = new ImagePattern(image1);
                    Rectangle ro2 = new Rectangle(this.cellSize / 2, this.cellSize / 2); // pioche
                    ro2.setFill(pattern1);
                    hRobot.getChildren().add(ro2);
                    r.setPioche(true);
                }
            } else {
                String imagePath11 = "/images/Alex.jpg";
                Image image = new Image(getClass().getResourceAsStream(imagePath11));
                ImagePattern pattern = new ImagePattern(image);
                ro.setFill(pattern);
                if ((grille.getSector(r.getPosition().getX(), r.getPosition().getY()) instanceof Mine) && (((Mine) grille.getSector(r.getPosition().getX(), r.getPosition().getY())).getType() == Ore.nickel)) {
                    String imagePath12 = "/images/Pioche.png";
                    Image image1 = new Image(getClass().getResourceAsStream(imagePath12));
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
        Rectangle rectangle = new Rectangle(0, 50, this.cellSize*7, h - 100);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);

        // Création des lignes pour séparer les différentes actions.
        Group line = new Group();
        Line l = new Line(cellSize*3.5, 50, cellSize*3.5, 200);
        Line l2 = new Line(0, 200, this.cellSize*7, 200);
        Line l3 = new Line(0, 310, this.cellSize*7, 310);
        line.getChildren().addAll(l, l2, l3);

        // Création du groupe pour le choix du robot.
        Group robot = new Group();
        robot.setTranslateX(this.cellSize /3);
        robot.setTranslateY(120);

        cb.setId("idRobot");
        cb.setPrefSize(this.cellSize*2, this.cellSize/2);
        cb.setStyle("-fx-font: 15px \"None\";");

        // Ajout des robots dans le choiceBox.
        ArrayList<Robots> robots = grille.getRobots();
        if (cb.getItems().size() == 1 || cb.getItems().size() == 0) {
            nbTour += 1;
            labeltour = new Label("NbTour : " + String.valueOf(nbTour));
            if (!cb.getItems().contains("Selectionnez un robot")){
                cb.getItems().add("Selectionnez un robot");
            }
            for (Robots ro : robots) {
                String out = "Robot " + ro.getId();
                cb.getItems().add(out);
            }
        }
        cb.setValue(cb.getItems().get(0));
        robot.getChildren().add(cb);
        robot.setId("GroupeCb");

        // Ajout du label pour le choix du robot.
        Label label = new Label("Selectionnez un robot");
        label.setFont(new Font(cellSize/4));
        label.setTranslateX(0);
        label.setTranslateY(-60);
        robot.getChildren().add(label);

        // Création du groupe pour les actions extraire et déposer
        Group action = new Group();
        Group boutonAct = new Group();
        boutonAct.setId("action");
        Button b = new Button("Extraire");
        Button b2 = new Button("Déposer");
        Button b3 = new Button("Ne rien faire");
        b.setFont(new Font(cellSize/5));
        b2.setFont(new Font(cellSize/5));
        b3.setFont(new Font(cellSize/5));


        // Positionnement des boutons et création du label
        Label text = new Label("Selectionnez une action :");
        text.setFont(new Font(cellSize/4));
        action.setTranslateX(cellSize*4);
        action.setTranslateY(60);
        b.setTranslateY(60);
        b.setTranslateX(cellSize*1.5);
        b2.setTranslateY(60);
        b2.setTranslateX(0);
        b3.setTranslateY(100);
        b3.setTranslateX(cellSize/2);

        // Ajout des boutons dans le groupe action
        boutonAct.getChildren().addAll(b, b2, b3);
        action.getChildren().addAll(text, boutonAct);

        // Ajout des événements sur les boutons extraire et déposer
        b.setOnMouseClicked(emgr);
        b2.setOnMouseClicked(emgr);
        b3.setOnMouseClicked(emgr);

        // Création du groupe pour la direction
        Group direction = new Group();
        Label text2 = new Label("Selectionnez une direction :");
        text2.setFont(new Font(cellSize/4));
        text2.setTranslateX(0);
        text2.setTranslateY(210);

        // Création des boutons pour les directions
        Group boutonDir = new Group();
        boutonDir.setId("action");
        Button d1 = new Button("Nord");
        d1.setPrefSize(cellSize, cellSize/3);
        d1.setFont(new Font(cellSize/5));
        d1.setTranslateX(0);
        d1.setTranslateY(250);

        Button d2 = new Button("Sud");
        d2.setPrefSize(cellSize, cellSize/3);
        d2.setFont(new Font(cellSize/5));
        d2.setTranslateX(cellSize*1.5);
        d2.setTranslateY(250);

        Button d3 = new Button("Est");
        d3.setPrefSize(cellSize, cellSize/3);
        d3.setFont(new Font(cellSize/5));
        d3.setTranslateX(cellSize*4.5);
        d3.setTranslateY(250);

        Button d4 = new Button("Ouest");
        d4.setPrefSize(cellSize, cellSize/3);
        d4.setFont(new Font(cellSize/5));
        d4.setTranslateX(cellSize*3);
        d4.setTranslateY(250);

        direction.setTranslateX(cellSize/3); //deplacement group

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
        text3.setFont(new Font(cellSize/4));
        text3.setTranslateX(0);
        text3.setTranslateY(330);
        recap.getChildren().addAll(text3);

        VBox recapText = generateRecap(grille);
        recapText.setStyle("-fx-font: 15px \"None\";");
        recapText.setTranslateX(0);
        recapText.setTranslateY(360);

        recap.setTranslateX(cellSize/3);
        // Ajout du récapitulatif dans le groupe recap
        recap.getChildren().add(recapText);

        // Création du groupe pour le bouton quitter
        Group bottom = new Group();
        Button e1 = new Button("Quitter");
        e1.setPrefSize(90, 30);
        e1.setFont(new Font(cellSize/5));
        e1.setTranslateX(0);
        e1.setTranslateY(h - 120);

        // Ajout du bouton quitter dans le groupe exit
        bottom.getChildren().add(e1);

        // Ajout de l'événement sur le bouton quitter
        e1.setOnMouseClicked(emgr);

        // Création du groupe pour le bouton reset
        Button r1 = new Button("Reset");
        r1.setPrefSize(90, 30);
        r1.setFont(new Font(cellSize/5));
        r1.setTranslateX(cellSize*4.5);
        r1.setTranslateY(h - 120);



        // Affichage nombre de tour
        labeltour.setFont(new Font(cellSize/5));
        labeltour.setTranslateX(cellSize*2.5);
        labeltour.setTranslateY(h-120);
        bottom.getChildren().addAll(labeltour);

        bottom.setTranslateX(cellSize/3);

        // Ajout du bouton reset dans le groupe reset
        bottom.getChildren().add(r1);

        // Ajout de l'événement sur le bouton reset
        r1.setOnMouseClicked(emgr);

        // Ajout des groupes dans le groupe sidebar
        sidebar.getChildren().addAll(rectangle, robot, line, action, direction, recap, bottom);
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
                String imagePath = "/images/Steve.jpg";
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                Label position = new Label(" Robot ID: " + r.getId() + " | " + " X: " + r.getPosition().getX() + " Y: " + r.getPosition().getY() + " | " + " Capacity: " + r.getCapacity() + "/" + r.getMaxCapacity() + " | " + " Type: " + "OR");
                ImagePattern pattern = new ImagePattern(image);
                r3.setFill(pattern);
                position.setFont(new Font(cellSize / 6));
                robotInfo.getChildren().addAll(r3, position);
                vRobot.getChildren().add(robotInfo);
            } else {
                String imagePath2 = "/images/Alex.jpg";
                Image image = new Image(getClass().getResourceAsStream(imagePath2));
                Label position = new Label(" Robot ID: " + r.getId() + " | " + " X: " + r.getPosition().getX() + " Y: " + r.getPosition().getY() + " | " + " Capacity: " + r.getCapacity() + "/" + r.getMaxCapacity() + " | " + " Type: " + "NI");
                ImagePattern pattern = new ImagePattern(image);
                r3.setFill(pattern);
                position.setFont(new Font(cellSize / 6));
                robotInfo.getChildren().addAll(r3, position);
                vRobot.getChildren().add(robotInfo);
            }
        }
        recap.getChildren().addAll(vRobot, espace);

        VBox vMine = new VBox();
        vMine.setId("boxMine");
        //Ajout mine recap
        for (Mine mine : grid.getMines()) {
            HBox mineInfo = new HBox();

            Rectangle r4 = new Rectangle(20, 20);
            if (mine.getMinerai() == Ore.gold) {
                String imagePath3 = "/images/Gold.jpg";
                Image image = new Image(getClass().getResourceAsStream(imagePath3));
                Label mines = new Label(" Mine ID: " + mine.getId() + " | " + " X: " + mine.getPosition().getX() + " Y: " + mine.getPosition().getY() + " | " + "Capacity: " + mine.getStockage() + "/" + mine.getmaxStockage() + " | " + " Type: " + "OR");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                mines.setFont(new Font(cellSize / 6));
                mineInfo.getChildren().addAll(r4, mines);
                vMine.getChildren().add(mineInfo);
            } else if (mine.getMinerai() == Ore.nickel) {
                String imagePath4 = "/images/Nickel.jpg";
                Image image = new Image(getClass().getResourceAsStream(imagePath4));
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
        //Ajout entrepots recap
        for (Entrepot entrepot: grid.getEntrepots()) {
            Rectangle r4 = new Rectangle(20,20);
            HBox entrepotInfo = new HBox();
            if (entrepot.getType() == Ore.gold) {

                String imagePath5 = "/images/ChestOr.png";
                Image image = new Image(getClass().getResourceAsStream(imagePath5));
                Label entrepots = new Label(" Entrepot ID: " + entrepot.getId() + " | " + " X: " + entrepot.getPosition().getX() + " Y: " + entrepot.getPosition().getY() + " | " + " Capacity: " + entrepot.getStockage() + " | " + " Type: " + "OR");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                entrepots.setFont(new Font(cellSize / 6));
                entrepotInfo.getChildren().addAll(r4, entrepots);
                vEntre.getChildren().add(entrepotInfo);
            } else {
                String imagePath6 = "/images/ChestNickel.png";
                Image image = new Image(getClass().getResourceAsStream(imagePath6));
                Label entrepots = new Label(" Entrepot ID: " + entrepot.getId() + " | " + " X: " + entrepot.getPosition().getX() + " Y: " + entrepot.getPosition().getY() + " | " + " Capacity: " + entrepot.getStockage() + " | " + " Type: " + "NI");
                ImagePattern pattern = new ImagePattern(image);
                r4.setFill(pattern);
                entrepots.setFont(new Font(cellSize / 6));
                entrepotInfo.getChildren().addAll(r4, entrepots);
                vEntre.getChildren().add(entrepotInfo);
            }

        }
        recap.getChildren().add(vEntre);
        // Création du boutton automatique
        Button auto = new Button("Automatique");
        auto.setPrefSize(100, 30);
        auto.setFont(new Font(cellSize/5));
        recap.getChildren().add(auto);
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
                    String imagePath = "/images/Pioche.png";
                    Image image1 = new Image(getClass().getResourceAsStream(imagePath));
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