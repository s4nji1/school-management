package com.example.dao.impl;

import com.example.dao.GenericDAO;
import com.example.model.Salle;
import com.example.utils.PostgreSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleDAOImpl implements GenericDAO<Salle> {

    @Override
    public void ajouter(Salle salle) {
        String sql = "INSERT INTO salles (nom_salle, capacite) VALUES (?, ?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, salle.getNomSalle());
            statement.setInt(2, salle.getCapacite());
            statement.executeUpdate();
            System.out.println("Salle inseree avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Salle afficher(int id) {
        String sql = "SELECT * FROM salles WHERE id_salle = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Salle(
                    resultSet.getInt("id_salle"),
                    resultSet.getString("nom_salle"),
                    resultSet.getInt("capacite")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Salle> afficherTous() {
        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT * FROM salles";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                salles.add(new Salle(
                    resultSet.getInt("id_salle"),
                    resultSet.getString("nom_salle"),
                    resultSet.getInt("capacite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }

    @Override
    public void modifier(Salle salle) {
        String sql = "UPDATE salles SET nom_salle = ?, capacite = ? WHERE id_salle = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, salle.getNomSalle());
            statement.setInt(2, salle.getCapacite());
            statement.setInt(3, salle.getIdSalle());
            statement.executeUpdate();
            System.out.println("Salle modifiee avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM salles WHERE id_salle = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Salle supprimee avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
