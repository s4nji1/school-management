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

        // Configuration du GridPane
        this.setPadding(new Insets(40));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: white;");

        // Titre
        Text titleText = new Text("Inscription");
        titleText.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #2196F3;");

        // Champs du formulaire
        Label nameLabel = new Label("Nom:");
        nameField = new TextField();
        nameField.setPromptText("Entrez votre nom");

        Label lastNameLabel = new Label("Prenom:");
        lastNameField = new TextField();
        lastNameField.setPromptText("Entrez votre prenom");

        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        emailField.setPromptText("Entrez votre email");

        Label passwordLabel = new Label("Mot de passe:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Entrez votre mot de passe");

        Label typeLabel = new Label("Type:");
        typeComboBox = new ComboBox<>();
        typeComboBox.getItems().addAll("ETUDIANT", "PROFESSEUR");
        typeComboBox.setValue("ETUDIANT");

        // Style des champs
        String fieldStyle = "-fx-background-radius: 5px; -fx-border-radius: 5px; " +
                          "-fx-border-color: #cccccc; -fx-border-width: 1px; " +
                          "-fx-padding: 8px; -fx-font-size: 13px;";
        nameField.setStyle(fieldStyle);
        lastNameField.setStyle(fieldStyle);
        emailField.setStyle(fieldStyle);
        passwordField.setStyle(fieldStyle);
        typeComboBox.setStyle(fieldStyle);

        // Boutons
        registerButton = new Button("S'inscrire");
        backButton = new Button("Retour");

        // Style des boutons
        registerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; " +
                              "-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                              "-fx-background-radius: 5px; -fx-cursor: hand;");
        backButton.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; " +
                          "-fx-font-size: 14px; -fx-padding: 10px 20px; " +
                          "-fx-background-radius: 5px; -fx-cursor: hand;");

        // Conteneur pour les boutons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(backButton, registerButton);

        // Ajout des elements au GridPane
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

        // Gestionnaires d'evenements
        registerButton.setOnAction(e -> handleRegister());
        backButton.setOnAction(e -> handleBack());//retourner a connexion
    }

    private void handleRegister() {
        // Validation des champs
        if (nameField.getText().isEmpty() || 
            lastNameField.getText().isEmpty() || 
            emailField.getText().isEmpty() || 
            passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Champs requis", "Veuillez remplir tous les champs.");
            return;
        }

        try {
           
            // Creation d'un nouvel utilisateur
            Utilisateur newUser = new Utilisateur(
                nameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                typeComboBox.getValue(),
                passwordField.getText()
               
                

                
            );

            // Tentative d'inscription
            if (utilisateurDAO.ajouter(newUser)) {
                showAlert(Alert.AlertType.INFORMATION, "Succes", "Inscription reussie ! Vous pouvez maintenant vous connecter.");
                // Retour à la page de connexion
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