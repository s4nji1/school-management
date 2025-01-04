package com.example.view;

import com.example.dao.impl.EvenementDAOImpl;
import com.example.model.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class EvenementView extends GridPane {

    private TableView<Evenement> tableView;
    private ObservableList<Evenement> evenementList;
    private EvenementDAOImpl evenementDAO;

    @SuppressWarnings("unchecked")
    public EvenementView() {
        evenementDAO = new EvenementDAOImpl();

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2e2e40;");

        Label titleLabel = new Label("Gestion des Evenements");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label nomLabel = new Label("Nom:");
        nomLabel.setStyle("-fx-text-fill: white;");
        TextField nomField = createStyledTextField();

        Label dateLabel = new Label("Date:");
        dateLabel.setStyle("-fx-text-fill: white;");
        DatePicker datePicker = new DatePicker();
        datePicker.setStyle("-fx-control-inner-background: #3a3a50; -fx-text-fill: white;");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-text-fill: white;");
        TextField descriptionField = createStyledTextField();

        Label idUserLabel = new Label("ID Utilisateur:");
        idUserLabel.setStyle("-fx-text-fill: white;");
        TextField idUserField = createStyledTextField();

        Button addButton = new Button("Ajouter");
        Button updateButton = new Button("Modifier");
        Button deleteButton = new Button("Supprimer");

        addButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        updateButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        tableView = new TableView<>();
        TableColumn<Evenement, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNomEvent()));

        TableColumn<Evenement, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDateEvent()));

        TableColumn<Evenement, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));

        TableColumn<Evenement, Integer> idUserColumn = new TableColumn<>("ID Utilisateur");
        idUserColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getIdUser()));

        tableView.getColumns().addAll(nomColumn, dateColumn, descriptionColumn, idUserColumn);

        evenementList = FXCollections.observableArrayList(evenementDAO.afficherTous());
        tableView.setItems(evenementList);

        this.add(titleLabel, 0, 0, 2, 1);
        this.add(nomLabel, 0, 1);
        this.add(nomField, 1, 1);
        this.add(dateLabel, 0, 2);
        this.add(datePicker, 1, 2);
        this.add(descriptionLabel, 0, 3);
        this.add(descriptionField, 1, 3);
        this.add(idUserLabel, 0, 4);
        this.add(idUserField, 1, 4);
        this.add(buttonBox, 0, 5, 2, 1);
        this.add(tableView, 0, 6, 2, 1);

        addButton.setOnAction(e -> {
            Evenement evenement = new Evenement(
                0,
                nomField.getText(),
                datePicker.getValue(),
                descriptionField.getText(),
                Integer.parseInt(idUserField.getText())
            );
            evenementDAO.ajouter(evenement);
            refreshTable();
            clearFields(nomField, descriptionField, idUserField);
            datePicker.setValue(null);
        });

        updateButton.setOnAction(e -> {
            Evenement selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setNomEvent(nomField.getText());
                selected.setDateEvent(datePicker.getValue());
                selected.setDescription(descriptionField.getText());
                selected.setIdUser(Integer.parseInt(idUserField.getText()));
                evenementDAO.modifier(selected);
                refreshTable();
                clearFields(nomField, descriptionField, idUserField);
                datePicker.setValue(null);
            }
        });

        deleteButton.setOnAction(e -> {
            Evenement selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                evenementDAO.supprimer(selected.getIdEvent());
                refreshTable();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomField.setText(newSelection.getNomEvent());
                datePicker.setValue(newSelection.getDateEvent());
                descriptionField.setText(newSelection.getDescription());
                idUserField.setText(String.valueOf(newSelection.getIdUser()));
            }
        });
    }

    private TextField createStyledTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-control-inner-background: #3a3a50; -fx-text-fill: white; -fx-border-color: #5a5a75; -fx-border-radius: 3; -fx-background-radius: 3;");
        return textField;
    }

    private void refreshTable() {
        evenementList.setAll(evenementDAO.afficherTous());
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
