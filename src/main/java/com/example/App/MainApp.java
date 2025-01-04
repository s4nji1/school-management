package com.example.App;

import com.example.view.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    private StackPane contentArea;

    private UtilisateurView utilisateurView;
    private EvenementView evenementView;
    private SalleView salleView;
    private TerrainView terrainView;
    private ReservationView reservationView;

    @Override
    public void start(Stage primaryStage) {

        utilisateurView = new UtilisateurView();
        evenementView = new EvenementView();
        salleView = new SalleView();
        terrainView = new TerrainView();
        reservationView = new ReservationView();

        // Navbar
        HBox navbar = new HBox(20);
        navbar.setPadding(new Insets(10, 20, 10, 20));
        navbar.setStyle(
            "-fx-background-color: #1e1e2f;" +
            "-fx-border-color: #F5F5F5;" +
            "-fx-border-width: 0 0 2 0;"
        );

        Label utilisateursTab = createNavbarItem("Utilisateurs");
        Label evenementsTab = createNavbarItem("Evenements");
        Label sallesTab = createNavbarItem("Salles");
        Label terrainsTab = createNavbarItem("Terrains");
        Label reservationsTab = createNavbarItem("Reservations");

        navbar.getChildren().addAll(utilisateursTab, evenementsTab, sallesTab, terrainsTab, reservationsTab);
        navbar.setAlignment(Pos.CENTER);

        // Content Area
        contentArea = new StackPane();
        contentArea.setStyle(
            "-fx-background-color: #2e2e40;" +
            "-fx-border-color: #F5F5F5;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 10px;" +
            "-fx-padding: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(245, 245, 245, 0.93), 0, 0.5, 96, 2);"
        );

        // Main Layout
        VBox mainLayout = new VBox(navbar, contentArea);
        VBox.setVgrow(contentArea, Priority.ALWAYS);

        // Tab Actions
        utilisateursTab.setOnMouseClicked(e -> switchContent(utilisateurView));
        evenementsTab.setOnMouseClicked(e -> switchContent(evenementView));
        sallesTab.setOnMouseClicked(e -> switchContent(salleView));
        terrainsTab.setOnMouseClicked(e -> switchContent(terrainView));
        reservationsTab.setOnMouseClicked(e -> switchContent(reservationView));

        // Scene and Stage
        Scene scene = new Scene(mainLayout, 800, 600);
        primaryStage.setTitle("Gestion des reservations des evenements de l'universite");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createNavbarItem(String text) {
        Label label = new Label(text);
        label.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;"
        );

        label.setOnMouseEntered(e -> label.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #F5F5F5;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;" +
            "-fx-border-color: #F5F5F5;" +
            "-fx-border-width: 0 0 2 0;"
        ));

        label.setOnMouseExited(e -> label.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;"
        ));

        return label;
    }

    private void switchContent(Object view) {
        contentArea.getChildren().clear();
        if (view instanceof UtilisateurView) {
            contentArea.getChildren().add(((UtilisateurView) view).createScene().getRoot());
        } else if (view instanceof EvenementView) {
            contentArea.getChildren().add(((EvenementView) view).createScene().getRoot());
        } else if (view instanceof SalleView) {
            contentArea.getChildren().add(((SalleView) view).createScene().getRoot());
        } else if (view instanceof TerrainView) {
            contentArea.getChildren().add(((TerrainView) view).createScene().getRoot());
        } else if (view instanceof ReservationView) {
            contentArea.getChildren().add(((ReservationView) view).createScene().getRoot());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
