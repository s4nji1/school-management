package com.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DashboardView {

    private StackPane contentArea;

    private ReservationView reservationView;
    private EvenementView evenementView;
    private SalleView salleView;
    private TerrainView terrainView;

    public DashboardView() {

        reservationView = new ReservationView();
        evenementView = new EvenementView();
        salleView = new SalleView();
        terrainView = new TerrainView();
    }

    public Scene createScene() {
        
        HBox navbar = createNavbar();

        
        contentArea = new StackPane();
        contentArea.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-color: #dddddd;" +
            "-fx-border-width: 2;" +
            "-fx-padding: 15;"
        );

        
        switchContent(evenementView);

        
        VBox mainLayout = new VBox(navbar, contentArea);
        VBox.setVgrow(contentArea, Priority.ALWAYS);

        return new Scene(mainLayout, 1000, 600);
    }

    private HBox createNavbar() {
        HBox navbar = new HBox(20);
        navbar.setPadding(new Insets(10, 20, 10, 20));
        navbar.setAlignment(Pos.CENTER);
        navbar.setStyle(
            "-fx-background-color: #1e1e2f;" +
            "-fx-border-color: #cccccc;" +
            "-fx-border-width: 0 0 2 0;"
        );

        
        Label reservationsTab = createNavbarItem("Reservations");
        Label evenementsTab = createNavbarItem("Evenements");
        Label sallesTab = createNavbarItem("Salles");
        Label terrainsTab = createNavbarItem("Terrains");
        Label logoutTab = createLogoutNavbarItem("Logout");

        
        reservationsTab.setOnMouseClicked(e -> switchContent(reservationView));
        evenementsTab.setOnMouseClicked(e -> switchContent(evenementView));
        sallesTab.setOnMouseClicked(e -> switchContent(salleView));
        terrainsTab.setOnMouseClicked(e -> switchContent(terrainView));

        
        navbar.getChildren().addAll(reservationsTab, evenementsTab, sallesTab, terrainsTab, logoutTab);

        return navbar;
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
            "-fx-text-fill: #eeeeee;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;" +
            "-fx-border-color: #eeeeee;" +
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

    private Label createLogoutNavbarItem(String text) {
        Label label = new Label(text);
        label.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;"
        );

        label.setOnMouseEntered(e -> label.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #ff0000;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;" +
            "-fx-border-color: #ff0000;" +
            "-fx-border-width: 0 0 2 0;"
        ));

        label.setOnMouseExited(e -> label.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-text-fill: #ffffff;" +
            "-fx-padding: 10;" +
            "-fx-cursor: hand;"
        ));

        label.setOnMouseClicked(e -> {

            Stage currentStage = (Stage) label.getScene().getWindow();
            currentStage.close();
        
            Stage loginStage = new Stage();
            LoginView loginView = new LoginView();
            Scene loginScene = loginView.createScene();
            loginStage.setScene(loginScene);
            loginStage.setTitle("Login");
            loginStage.show();
        });

        return label;
    }

    private void switchContent(Object view) {
        contentArea.getChildren().clear();
        if (view instanceof ReservationView) {
            contentArea.getChildren().add(((ReservationView) view).createScene().getRoot());
        } else if (view instanceof EvenementView) {
            contentArea.getChildren().add(((EvenementView) view).createScene().getRoot());
        } else if (view instanceof SalleView) {
            contentArea.getChildren().add(((SalleView) view).createScene().getRoot());
        } else if (view instanceof TerrainView) {
            contentArea.getChildren().add(((TerrainView) view).createScene().getRoot());
        }
    }
}
