    package com.example.App;

    import com.example.view.*;
    import javafx.application.Application;
    import javafx.geometry.HPos;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.StackPane;
    import javafx.stage.Stage;

    public class MainApp extends Application {

        private StackPane contentArea;

        @Override
        public void start(Stage primaryStage) {

            GridPane mainLayout = new GridPane();
            mainLayout.setPadding(new Insets(10));
            mainLayout.setVgap(10);
            mainLayout.setHgap(10);

            Button utilisateursButton = new Button("Utilisateurs");
            Button evenementsButton = new Button("Evenements");
            Button sallesButton = new Button("Salles");
            Button terrainsButton = new Button("Terrains");
            Button reservationsButton = new Button("Reservations");

            GridPane navbar = new GridPane();
            navbar.setHgap(15);
            navbar.setAlignment(Pos.CENTER);

            navbar.add(utilisateursButton, 9, 0);
            navbar.add(evenementsButton, 12, 0);
            navbar.add(sallesButton, 15, 0);
            navbar.add(terrainsButton, 18, 0);
            navbar.add(reservationsButton, 21, 0);

            mainLayout.add(navbar, 0, 0);
            GridPane.setHalignment(navbar, HPos.CENTER);

            contentArea = new StackPane();
            contentArea.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f9f9f9; -fx-padding: 10;");

            UtilisateurView utilisateurView = new UtilisateurView();
            contentArea.getChildren().add(utilisateurView.createScene().getRoot());

            mainLayout.add(contentArea, 0, 1);

            utilisateursButton.setOnAction(e -> {
                contentArea.getChildren().clear();
                contentArea.getChildren().add(utilisateurView.createScene().getRoot());
            });

            evenementsButton.setOnAction(e -> {
                EvenementView evenementsView = new EvenementView();
                contentArea.getChildren().clear();
                contentArea.getChildren().add(evenementsView.createScene().getRoot());
            });

            sallesButton.setOnAction(e -> {
                SalleView sallesView = new SalleView();
                contentArea.getChildren().clear();
                contentArea.getChildren().add(sallesView.createScene().getRoot());
            });

            terrainsButton.setOnAction(e -> {
                TerrainView terrainsView = new TerrainView();
                contentArea.getChildren().clear();
                contentArea.getChildren().add(terrainsView.createScene().getRoot());
            });

            reservationsButton.setOnAction(e -> {
                ReservationView reservationsView = new ReservationView();
                contentArea.getChildren().clear();
                contentArea.getChildren().add(reservationsView.createScene().getRoot());
            });

            Scene scene = new Scene(mainLayout, 800, 600);
            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
