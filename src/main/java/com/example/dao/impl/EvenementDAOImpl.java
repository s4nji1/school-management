package com.example.dao.impl;

import com.example.dao.GenericDAO;
import com.example.model.Evenement;
import com.example.utils.PostgreSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvenementDAOImpl implements GenericDAO<Evenement> {

    @Override
    public void ajouter(Evenement evenement) {
        String sql = "INSERT INTO evenements (nom_event, date_event, description, id_user) VALUES (?, ?, ?, ?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, evenement.getNomEvent());
            statement.setDate(2, Date.valueOf(evenement.getDateEvent()));
            statement.setString(3, evenement.getDescription());
            statement.setInt(4, evenement.getIdUser());
            statement.executeUpdate();
            System.out.println("Evenement insere avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Evenement afficher(int id) {
        String sql = "SELECT * FROM evenements WHERE id_event = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Evenement(
                    resultSet.getInt("id_event"),
                    resultSet.getString("nom_event"),
                    resultSet.getDate("date_event").toLocalDate(),  // Convert Date to LocalDate
                    resultSet.getString("description"),
                    resultSet.getInt("id_user")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Evenement> afficherTous() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "SELECT * FROM evenements";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                evenements.add(new Evenement(
                    resultSet.getInt("id_event"),
                    resultSet.getString("nom_event"),
                    resultSet.getDate("date_event").toLocalDate(),
                    resultSet.getString("description"),
                    resultSet.getInt("id_user")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenements;
    }

    @Override
    public void modifier(Evenement evenement) {
        String sql = "UPDATE evenements SET nom_event = ?, date_event = ?, description = ?, id_user = ? WHERE id_event = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, evenement.getNomEvent());
            statement.setDate(2, Date.valueOf(evenement.getDateEvent()));  // Convert LocalDate to Date
            statement.setString(3, evenement.getDescription());
            statement.setInt(4, evenement.getIdUser());
            statement.setInt(5, evenement.getIdEvent());
            statement.executeUpdate();
            System.out.println("Evenement modifie avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM evenements WHERE id_event = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Evenement supprime avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
