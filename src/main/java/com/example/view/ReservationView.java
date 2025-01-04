package com.example.view;

import com.example.dao.impl.ReservationDAOImpl;
import com.example.model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;

public class ReservationView extends GridPane {

    private TableView<Reservation> tableView;
    private ObservableList<Reservation> reservationList;
    private ReservationDAOImpl reservationDAO;

    @SuppressWarnings("unchecked")
    public ReservationView() {
        reservationDAO = new ReservationDAOImpl();

        // Styling the grid pane
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #2e2e40;");

        // Title label
        Label titleLabel = new Label("Gestion des Reservations");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Form labels and fields
        Label idUserLabel = createStyledLabel("ID Utilisateur:");
        TextField idUserField = createStyledTextField();

        Label idEventLabel = createStyledLabel("ID Evenement:");
        TextField idEventField = createStyledTextField();

        Label idSalleLabel = createStyledLabel("ID Salle:");
        TextField idSalleField = createStyledTextField();

        Label idTerrainLabel = createStyledLabel("ID Terrain:");
        TextField idTerrainField = createStyledTextField();

        Label dateReservationLabel = createStyledLabel("Date Reservation:");
        DatePicker dateReservationPicker = new DatePicker();
        dateReservationPicker.setStyle("-fx-control-inner-background: #3a3a50; -fx-text-fill: white;");

        // Buttons
        Button addButton = createStyledButton("Ajouter");
        Button updateButton = createStyledButton("Modifier");
        Button deleteButton = createStyledButton("Supprimer");

        HBox buttonBox = new HBox(10, addButton, updateButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER);

        // TableView
        tableView = new TableView<>();
        tableView.setStyle("-fx-background-color: #3a3a50; -fx-text-fill: white;");
        TableColumn<Reservation, Integer> idUserColumn = new TableColumn<>("ID Utilisateur");
        idUserColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdUser()).asObject());

        TableColumn<Reservation, Integer> idEventColumn = new TableColumn<>("ID evenement");
        idEventColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdEvent()).asObject());

        TableColumn<Reservation, Integer> idSalleColumn = new TableColumn<>("ID Salle");
        idSalleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdSalle()).asObject());

        TableColumn<Reservation, Integer> idTerrainColumn = new TableColumn<>("ID Terrain");
        idTerrainColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdTerrain()).asObject());

        TableColumn<Reservation, LocalDate> dateReservationColumn = new TableColumn<>("Date Reservation");
        dateReservationColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getDateReservation()));

        tableView.getColumns().addAll(idUserColumn, idEventColumn, idSalleColumn, idTerrainColumn, dateReservationColumn);

        // Populate table data
        reservationList = FXCollections.observableArrayList(reservationDAO.afficherTous());
        tableView.setItems(reservationList);

        // Adding elements to the grid
        this.add(titleLabel, 0, 0, 2, 1);
        this.add(idUserLabel, 0, 1);
        this.add(idUserField, 1, 1);
        this.add(idEventLabel, 0, 2);
        this.add(idEventField, 1, 2);
        this.add(idSalleLabel, 0, 3);
        this.add(idSalleField, 1, 3);
        this.add(idTerrainLabel, 0, 4);
        this.add(idTerrainField, 1, 4);
        this.add(dateReservationLabel, 0, 5);
        this.add(dateReservationPicker, 1, 5);
        this.add(buttonBox, 0, 6, 2, 1);
        this.add(tableView, 0, 7, 2, 1);

        // Button actions
        addButton.setOnAction(e -> {
            Reservation reservation = new Reservation(0,
                    Integer.parseInt(idUserField.getText()),
                    Integer.parseInt(idEventField.getText()),
                    Integer.parseInt(idSalleField.getText()),
                    Integer.parseInt(idTerrainField.getText()),
                    dateReservationPicker.getValue());
            reservationDAO.ajouter(reservation);
            refreshTable();
            clearFields(idUserField, idEventField, idSalleField, idTerrainField);
            dateReservationPicker.setValue(null);
        });

        updateButton.setOnAction(e -> {
            Reservation selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setIdUser(Integer.parseInt(idUserField.getText()));
                selected.setIdEvent(Integer.parseInt(idEventField.getText()));
                selected.setIdSalle(Integer.parseInt(idSalleField.getText()));
                selected.setIdTerrain(Integer.parseInt(idTerrainField.getText()));
                selected.setDateReservation(dateReservationPicker.getValue());
                reservationDAO.modifier(selected);
                refreshTable();
                clearFields(idUserField, idEventField, idSalleField, idTerrainField);
                dateReservationPicker.setValue(null);
            }
        });

        deleteButton.setOnAction(e -> {
            Reservation selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                reservationDAO.supprimer(selected.getIdReservation());
                refreshTable();
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idUserField.setText(String.valueOf(newSelection.getIdUser()));
                idEventField.setText(String.valueOf(newSelection.getIdEvent()));
                idSalleField.setText(String.valueOf(newSelection.getIdSalle()));
                idTerrainField.setText(String.valueOf(newSelection.getIdTerrain()));
                dateReservationPicker.setValue(newSelection.getDateReservation());
            }
        });
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: white;");
        return label;
    }

    private TextField createStyledTextField() {
        TextField textField = new TextField();
        textField.setStyle("-fx-control-inner-background: #3a3a50; -fx-text-fill: white;");
        return textField;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #5a5a72; -fx-text-fill: white;");
        return button;
    }

    private void refreshTable() {
        reservationList.setAll(reservationDAO.afficherTous());
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
