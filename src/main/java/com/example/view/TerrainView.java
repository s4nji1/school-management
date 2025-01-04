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

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2e2e40;");

        Label titleLabel = new Label("Gestion des Terrains");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label nomTerrainLabel = new Label("Nom:");
        nomTerrainLabel.setStyle("-fx-text-fill: white;");
        TextField nomTerrainField = createStyledTextField();

        Label typeLabel = new Label("Type:");
        typeLabel.setStyle("-fx-text-fill: white;");
        TextField typeField = createStyledTextField();

        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        addButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        tableView = new TableView<>();
        tableView.setPrefWidth(300);

        TableColumn<Terrain, String> nomTerrainColumn = new TableColumn<>("Nom");
        nomTerrainColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNomTerrain()));
        nomTerrainColumn.setPrefWidth(150);

        TableColumn<Terrain, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));
        typeColumn.setPrefWidth(150);
        
        tableView.getColumns().addAll(nomTerrainColumn, typeColumn);

        terrainList = FXCollections.observableArrayList(terrainDAO.afficherTous());
        tableView.setItems(terrainList);

        this.add(titleLabel, 0, 0, 2, 1);
        this.add(nomTerrainLabel, 0, 1);
        this.add(nomTerrainField, 1, 1);
        this.add(typeLabel, 0, 2);
        this.add(typeField, 1, 2);
        this.add(buttonBox, 0, 3, 2, 1);
        this.add(tableView, 0, 4, 2, 1);

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

    private TextField createStyledTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-control-inner-background: #3a3a50; -fx-text-fill: white; -fx-border-color: #5a5a75; -fx-border-radius: 3; -fx-background-radius: 3;");
        return textField;
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
