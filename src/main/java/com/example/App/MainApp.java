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

    public void showLogin() {
        try {
            LoginView loginView = new LoginView();
            Scene loginScene = loginView.createScene();

            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
            primaryStage.setResizable(false);
            primaryStage.show();
            
            primaryStage.centerOnScreen();
        } catch (Exception e) {
        }
    }

    public void showDashboard() {
        try {
            Stage dashboardStage = new Stage();
            DashboardView dashboardView = new DashboardView();
            Scene dashboardScene = dashboardView.createScene();

            dashboardStage.setScene(dashboardScene);
            dashboardStage.setTitle("Gestion des reservations des evenements de l'universite");
            dashboardStage.setMaximized(true);
            
            
            primaryStage.close();
            
            
            dashboardStage.show();
        } catch (Exception e) {
            showError("Erreur lors du chargement du tableau de bord", e.getMessage());
        }
    }

    public void logout() {
        try {
            Stage currentStage = (Stage) primaryStage.getScene().getWindow();
            currentStage.close();
            
            showLogin();
        } catch (Exception e) {
            showError("Erreur lors de la deconnexion", e.getMessage());
        }
    }

    private void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static MainApp getInstance() {
        return instance;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}