package com.example.view;

import com.example.dao.impl.UtilisateurDAOImpl;
import com.example.model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class UtilisateurView extends GridPane {//disposition en grille pour organises les elem graphique

    private TableView<Utilisateur> tableView;//tableau dans le quel on va afficher users 
    private ObservableList<Utilisateur> userList;//pour socker les users
    private UtilisateurDAOImpl utilisateurDAO;//interagir avec bd a travers UtilisateurDao

    @SuppressWarnings("unchecked")
    public UtilisateurView() {
        utilisateurDAO = new UtilisateurDAOImpl();

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2e2e40; -fx-text-fill: white;");
//le formulaire creer user
        Label titleLabel = new Label("Gestion des Utilisateurs");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label nomLabel = new Label("Nom:");
        nomLabel.setStyle("-fx-text-fill: white;");
        TextField nomField = createStyledTextField();

        Label prenomLabel = new Label("Prenom:");
        prenomLabel.setStyle("-fx-text-fill: white;");
        TextField prenomField = createStyledTextField();

        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: white;");
        TextField emailField = createStyledTextField();

        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");
        TextField typeField = createStyledTextField();
//buttons
        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        addButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);
//defenir la forme du tableau les colls
        tableView = new TableView<>();
         //definir les coll   setCellValueFactory lie la coll nom l attribut nom d objet user
        TableColumn<Utilisateur, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));

        TableColumn<Utilisateur, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrenom()));

        TableColumn<Utilisateur, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Utilisateur, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));
//ajouter les coll au table view ,chaque coll est associe a un attribut a Utilisateur
        tableView.getColumns().addAll(nomColumn, prenomColumn, emailColumn, typeColumn);
// Fxcoll:Convertit la liste récupérée en une liste observable
        userList = FXCollections.observableArrayList(utilisateurDAO.afficherTous());//recuperee les users depuis notre bd et les stocker
        //associe les users recuperer au tableView pour les afficher
        tableView.setItems(userList);
//ajouter les elems a la gridPane (position)
        this.add(titleLabel, 0, 0, 2, 1);
        this.add(nomLabel, 0, 1);
        this.add(nomField, 1, 1);
        this.add(prenomLabel, 0, 2);
        this.add(prenomField, 1, 2);
        this.add(emailLabel, 0, 3);
        this.add(emailField, 1, 3);
        this.add(typeLabel, 0, 4);
        this.add(typeField, 1, 4);
        this.add(buttonBox, 0, 5, 2, 1);
        this.add(tableView, 0, 6, 2, 1);

        addButton.setOnAction(e -> {
            Utilisateur utilisateur = new Utilisateur(nomField.getText(), prenomField.getText(), emailField.getText(), typeField.getText());
            utilisateurDAO.ajouter(utilisateur);//les ajouter dans la base
            refreshTable();//rafraichit le tableau
            clearFields(nomField, prenomField, emailField, typeField);
        });

        updateButton.setOnAction(e -> {
            Utilisateur selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setNom(nomField.getText());
                selected.setPrenom(prenomField.getText());
                selected.setEmail(emailField.getText());
                selected.setType(typeField.getText());
                utilisateurDAO.modifier(selected);
                refreshTable();
                clearFields(nomField, prenomField, emailField, typeField);
            }
        });

        deleteButton.setOnAction(e -> {
            Utilisateur selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                utilisateurDAO.supprimer(selected.getIdUser());
                refreshTable();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomField.setText(newSelection.getNom());
                prenomField.setText(newSelection.getPrenom());
                emailField.setText(newSelection.getEmail());
                typeField.setText(newSelection.getType());
            }
        });
    }

    private TextField createStyledTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-control-inner-background: #3a3a50; -fx-text-fill: white; -fx-border-color: #5a5a75; -fx-border-radius: 3; -fx-background-radius: 3;");
        return textField;
    }

    private void refreshTable() {
        userList.setAll(utilisateurDAO.afficherTous());
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public Scene createScene() {
        return new Scene(this, 800, 600);
    }

}
