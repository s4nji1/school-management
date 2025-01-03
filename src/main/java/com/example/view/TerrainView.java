package com.example.view;

import com.example.dao.impl.TerrainDAOImpl;
import com.example.model.Terrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TerrainView extends GridPane {

    private TableView<Terrain> tableView;
    private ObservableList<Terrain> terrainList;
    private TerrainDAOImpl terrainDAO;

    @SuppressWarnings("unchecked")
    public TerrainView() {
        terrainDAO = new TerrainDAOImpl();

        // Set padding and layout
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);

        // Title
        Label titleLabel = new Label("Gestion des Terrains");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input fields
        Label nomTerrainLabel = new Label("Nom:");
        TextField nomTerrainField = new TextField();
        Label typeLabel = new Label("Type:");
        TextField typeField = new TextField();

        // Buttons
        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Table view
        tableView = new TableView<>();
        TableColumn<Terrain, String> nomTerrainColumn = new TableColumn<>("Nom");
        nomTerrainColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNomTerrain()));

        TableColumn<Terrain, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));

        tableView.getColumns().addAll(nomTerrainColumn, typeColumn);

        // Fetch data from database
        terrainList = FXCollections.observableArrayList(terrainDAO.afficherTous());
        tableView.setItems(terrainList);

        // Add components to the layout
        this.add(titleLabel, 0, 0, 2, 1);
        this.add(nomTerrainLabel, 0, 1);
        this.add(nomTerrainField, 1, 1);
        this.add(typeLabel, 0, 2);
        this.add(typeField, 1, 2);
        this.add(buttonBox, 0, 3, 2, 1);
        this.add(tableView, 0, 4, 2, 1);

        // Button actions
        addButton.setOnAction(e -> {
            Terrain terrain = new Terrain(0, nomTerrainField.getText(), typeField.getText());
            terrainDAO.ajouter(terrain);
            refreshTable();
            clearFields(nomTerrainField, typeField);
        });

        updateButton.setOnAction(e -> {
            Terrain selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setNomTerrain(nomTerrainField.getText());
                selected.setType(typeField.getText());
                terrainDAO.modifier(selected);
                refreshTable();
                clearFields(nomTerrainField, typeField);
            }
        });

        deleteButton.setOnAction(e -> {
            Terrain selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                terrainDAO.supprimer(selected.getIdTerrain());
                refreshTable();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomTerrainField.setText(newSelection.getNomTerrain());
                typeField.setText(newSelection.getType());
            }
        });
    }

    private void refreshTable() {
        terrainList.setAll(terrainDAO.afficherTous());
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
