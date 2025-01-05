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

public class RegisterView extends GridPane {
    private TextField nameField;
    private TextField lastNameField;
    private TextField emailField;
    private PasswordField passwordField;
    private ComboBox<String> typeComboBox;
    private Button registerButton;
    private Button backButton;
    private UtilisateurDAOImpl utilisateurDAO;

    public RegisterView() {
        utilisateurDAO = new UtilisateurDAOImpl();

        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2E2E2E;");

        Text titleText = new Text("Inscription");
        titleText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #FFEB3B;");

        Label nameLabel = new Label("Nom:");
        nameLabel.setStyle("-fx-text-fill: white;");
        nameField = new TextField();
        nameField.setPromptText("Entrez votre nom");
        nameField.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-border-radius: 5px; -fx-border-color: #666666;");

        Label lastNameLabel = new Label("Prenom:");
        lastNameLabel.setStyle("-fx-text-fill: white;");
        lastNameField = new TextField();
        lastNameField.setPromptText("Entrez votre prenom");
        lastNameField.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-border-radius: 5px; -fx-border-color: #666666;");

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: white;");
        emailField = new TextField();
        emailField.setPromptText("Entrez votre email");
        emailField.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-border-radius: 5px; -fx-border-color: #666666;");

        Label passwordLabel = new Label("Mot de passe:");
        passwordLabel.setStyle("-fx-text-fill: white;");
        passwordField = new PasswordField();
        passwordField.setPromptText("Entrez votre mot de passe");
        passwordField.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-border-radius: 5px; -fx-border-color: #666666;");

        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");
        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("ETUDIANT", "PROFESSEUR");
        typeComboBox.setValue("ETUDIANT");
        typeComboBox.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-border-radius: 5px; -fx-border-color: #666666;");

        registerButton = new Button("S'inscrire");
        backButton = new Button("Retour");

        registerButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; " +
                              "-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                              "-fx-background-radius: 5px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, #2196F3, 10, 0.5, 0, 2);");
        backButton.setStyle("-fx-background-color: #FFEB3B; -fx-text-fill: black; -fx-effect: dropshadow(gaussian, #FFEB3B, 10, 0.5, 0, 2);" + // Yellow button
                          "-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                          "-fx-background-radius: 5px; -fx-cursor: hand;");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backButton, registerButton);

        this.add(titleText, 0, 0, 2, 1);
        this.add(nameLabel, 0, 1);
        this.add(nameField, 0, 2, 2, 1);
        this.add(lastNameLabel, 0, 3);
        this.add(lastNameField, 0, 4, 2, 1);
        this.add(emailLabel, 0, 5);
        this.add(emailField, 0, 6, 2, 1);
        this.add(passwordLabel, 0, 7);
        this.add(passwordField, 0, 8, 2, 1);
        this.add(typeLabel, 0, 9);
        this.add(typeComboBox, 0, 10, 2, 1);
        this.add(buttonBox, 0, 11, 2, 1);

        registerButton.setOnAction(e -> handleRegister());
        backButton.setOnAction(e -> handleBack());
    }

    private void handleRegister() {
        if (nameField.getText().isEmpty() || 
            lastNameField.getText().isEmpty() || 
            emailField.getText().isEmpty() || 
            passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champs requis", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            Utilisateur newUser = new Utilisateur(
                nameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                typeComboBox.getValue(),
                passwordField.getText()
            );

            if (utilisateurDAO.ajouter(newUser)) {
                showAlert(Alert.AlertType.INFORMATION, "Succes", "Inscription reussie ! Vous pouvez maintenant vous connecter.");
                handleBack();
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur", "L'inscription a echoue. Veuillez reessayer.");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de l'inscription.\n" + e.getMessage());
        }
    }

    private void handleBack() {
        try {
            LoginView loginView = new LoginView();
            Scene loginScene = loginView.createScene();
            MainApp.getPrimaryStage().setScene(loginScene);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du retour à la page de connexion.\n" + e.getMessage());
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
        return new Scene(this, 400, 600);
    }
}
