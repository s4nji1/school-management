package com.example.view;

import com.example.App.MainApp;
import com.example.dao.impl.UtilisateurDAOImpl;
import com.example.model.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LoginView extends GridPane {
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;
    private UtilisateurDAOImpl utilisateurDAO;

    public LoginView() {
        utilisateurDAO = new UtilisateurDAOImpl();
        
        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2E2E2E;");

        Text titleText = new Text("SE Connecter");
        titleText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #FFEB3B;");

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #E0E0E0;");
        emailField = new TextField();
        emailField.setPromptText("Entrez votre email");
        emailField.setPrefWidth(250);
        
        Label passwordLabel = new Label("Mot de passe:");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #E0E0E0;");
        passwordField = new PasswordField();
        passwordField.setPromptText("Entrez votre mot de passe");
        passwordField.setPrefWidth(250);

        String fieldStyle = "-fx-background-radius: 5px; -fx-border-radius: 5px; " +
                          "-fx-border-color: #444444; -fx-border-width: 1px; " +
                          "-fx-padding: 8px; -fx-font-size: 13px; -fx-background-color: #333333; " +
                          "-fx-text-fill: #E0E0E0;";
        emailField.setStyle(fieldStyle);
        passwordField.setStyle(fieldStyle);

        loginButton = new Button("Connexion");
        registerButton = new Button("Nouveau compte");

        loginButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; " +
                           "-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                           "-fx-background-radius: 5px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, #2196F3, 10, 0.5, 0, 2);");
        registerButton.setStyle("-fx-background-color: #FFEB3B; -fx-text-fill: black; " +
                              "-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                              "-fx-background-radius: 5px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, #FFEB3B, 10, 0.5, 0, 2);");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(loginButton, registerButton);

        this.add(titleText, 0, 0, 2, 1);
        this.add(emailLabel, 0, 2);
        this.add(emailField, 0, 3, 2, 1);
        this.add(passwordLabel, 0, 4);
        this.add(passwordField, 0, 5, 2, 1);
        this.add(buttonBox, 0, 6, 2, 1);

        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegister());
 
        passwordField.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("ENTER")) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champs requis", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            Utilisateur utilisateur = utilisateurDAO.connecter(email, password);

            if (utilisateur != null) {
                MainApp.getInstance().showDashboard();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur de connexion", "Email ou mot de passe incorrect.");
                passwordField.clear();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de la connexion.\n" + e.getMessage());
        }
    }

    private void handleRegister() {
        try {
            RegisterView registerView = new RegisterView();
            Scene registerScene = registerView.createScene();
            MainApp.getPrimaryStage().setScene(registerScene);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement de la page d'inscription.\n" + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Scene createScene() {
        return new Scene(this, 400, 500);
    }
}
