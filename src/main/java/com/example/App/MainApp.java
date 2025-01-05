package com.example.App;

import com.example.view.DashboardView;
import com.example.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static MainApp instance;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        instance = this;
        showLogin();
    }

    // Methode pour afficher la fenêtre de connexion
    public void showLogin() {
        try {
            LoginView loginView = new LoginView();
            Scene loginScene = loginView.createScene();

            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Connexion");
            primaryStage.setResizable(false);
            primaryStage.show();
            
            // Centrer la fenêtre sur l'ecran
            primaryStage.centerOnScreen();
        } catch (Exception e) {
            // showError("Erreur lors du chargement de la page de connexion", e.getMessage());
        }
    }

    // Methode pour afficher le tableau de bord
    public void showDashboard() {
        try {
            // Creer une nouvelle fenêtre pour le dashboard
            Stage dashboardStage = new Stage();
            DashboardView dashboardView = new DashboardView();
            Scene dashboardScene = dashboardView.createScene();

            dashboardStage.setScene(dashboardScene);
            dashboardStage.setTitle("Tableau de Bord");
            dashboardStage.setMaximized(true); // Ouvrir en plein ecran
            
            // Fermer la fenêtre de connexion
            primaryStage.close();
            
            // Afficher le dashboard
            dashboardStage.show();
        } catch (Exception e) {
            showError("Erreur lors du chargement du tableau de bord", e.getMessage());
        }
    }

    // Methode pour la deconnexion
    public void logout() {
        try {
            // Fermer toutes les fenêtres existantes
            Stage currentStage = (Stage) primaryStage.getScene().getWindow();
            currentStage.close();
            
            // Reafficher la fenêtre de connexion
            showLogin();
        } catch (Exception e) {
            showError("Erreur lors de la deconnexion", e.getMessage());
        }
    }

    // Methode utilitaire pour afficher les erreurs
    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Methode pour acceder à l'instance de MainApp depuis n'importe où
    public static MainApp getInstance() {
        return instance;
    }

    // Methode pour acceder au stage principal
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}