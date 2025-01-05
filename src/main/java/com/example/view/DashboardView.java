package com.example.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView extends BorderPane {
    private Button reservationButton;
    private Button evenementButton;
    private Button salleButton;
    private Button terrainButton;
    private Button logoutButton;
    
    public DashboardView() {
        // Créer la barre de navigation
        VBox navbar = createNavbar();
        
        // Placer la navbar à gauche
        this.setLeft(navbar);
        
        // Afficher la vue des réservations par défaut
        this.setCenter(new EvenementView());
    }
    
    private VBox createNavbar() {
        VBox navbar = new VBox(10); // Espacement de 10 entre les boutons
        navbar.setPadding(new Insets(10));
        navbar.setStyle("-fx-background-color: #f0f0f0;");
        
        // Créer les boutons de navigation
        reservationButton = new Button("Réservations");
        evenementButton = new Button("Événements");
        salleButton = new Button("Salles");
        terrainButton = new Button("Terrains");
        logoutButton = new Button("Déconnexion");
        
        // Styliser les boutons
        String buttonStyle = "-fx-min-width: 150px; -fx-min-height: 40px;";
        reservationButton.setStyle(buttonStyle);
        evenementButton.setStyle(buttonStyle);
        salleButton.setStyle(buttonStyle);
        terrainButton.setStyle(buttonStyle);
        logoutButton.setStyle(buttonStyle + "-fx-background-color: #ff4444; -fx-text-fill: white;");
        
        // Ajouter les gestionnaires d'événements pour la navigation
        reservationButton.setOnAction(e -> this.setCenter(new ReservationView()));
        evenementButton.setOnAction(e -> this.setCenter(new EvenementView()));
        salleButton.setOnAction(e -> this.setCenter(new SalleView()));
        terrainButton.setOnAction(e -> this.setCenter(new TerrainView()));
        logoutButton.setOnAction(e -> handleLogout());
        
        // Ajouter les boutons à la navbar
        navbar.getChildren().addAll(
            reservationButton,
            evenementButton,
            salleButton,
            terrainButton,
            logoutButton
        );
        
        return navbar;
    }
    
    private void handleLogout() {
        // Fermer la fenêtre actuelle
        Stage currentStage = (Stage) this.getScene().getWindow();
        currentStage.close();
        
        // Ouvrir une nouvelle fenêtre de connexion
        Stage loginStage = new Stage();
        LoginView loginView = new LoginView();
        Scene loginScene = loginView.createScene();
        
        loginStage.setScene(loginScene);
        loginStage.setTitle("Application de Connexion");
        loginStage.show();
    }
    
    public Scene createScene() {
        return new Scene(this, 1000, 600);
    }
}