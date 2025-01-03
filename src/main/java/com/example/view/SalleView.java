package com.example.view;

import com.example.dao.impl.SalleDAOImpl;
import com.example.model.Salle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SalleView extends GridPane {

    private TableView<Salle> tableView;
    private ObservableList<Salle> salleList;
    private SalleDAOImpl salleDAO;

    @SuppressWarnings("unchecked")
    public SalleView() {
        salleDAO = new SalleDAOImpl();

        // Set padding and layout
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);

        // Title
        Label titleLabel = new Label("Gestion des Salles");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input fields
        Label nomLabel = new Label("Nom de la Salle:");
        TextField nomField = new TextField();
        Label capaciteLabel = new Label("Capacité:");
        TextField capaciteField = new TextField();

        // Buttons
        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Table view
        tableView = new TableView<>();
        TableColumn<Salle, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNomSalle()));

        TableColumn<Salle, Integer> capaciteColumn = new TableColumn<>("Capacité");
        capaciteColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getCapacite()));

        tableView.getColumns().addAll(nomColumn, capaciteColumn);

        // Fetch data from database
        salleList = FXCollections.observableArrayList(salleDAO.afficherTous());
        tableView.setItems(salleList);

        // Add components to the layout
        this.add(titleLabel, 0, 0, 2, 1);
        this.add(nomLabel, 0, 1);
        this.add(nomField, 1, 1);
        this.add(capaciteLabel, 0, 2);
        this.add(capaciteField, 1, 2);
        this.add(buttonBox, 0, 3, 2, 1);
        this.add(tableView, 0, 4, 2, 1);

        // Button actions
        addButton.setOnAction(e -> {
            Salle salle = new Salle(0, nomField.getText(), Integer.parseInt(capaciteField.getText()));
            salleDAO.ajouter(salle);
            refreshTable();
            clearFields(nomField, capaciteField);
        });

        updateButton.setOnAction(e -> {
            Salle selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setNomSalle(nomField.getText());
                selected.setCapacite(Integer.parseInt(capaciteField.getText()));
                salleDAO.modifier(selected);
                refreshTable();
                clearFields(nomField, capaciteField);
            }
        });

        deleteButton.setOnAction(e -> {
            Salle selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                salleDAO.supprimer(selected.getIdSalle());
                refreshTable();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomField.setText(newSelection.getNomSalle());
                capaciteField.setText(String.valueOf(newSelection.getCapacite()));
            }
        });
    }

    private void refreshTable() {
        salleList.setAll(salleDAO.afficherTous());
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
