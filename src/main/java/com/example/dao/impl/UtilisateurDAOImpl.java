package com.example.dao.impl;

import com.example.model.Utilisateur;
import com.example.utils.PostgreSQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOImpl {

    public void ajouter(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs (nom ,prenom ,email ,type) VALUES (?, ?, ?, ?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getType());
            statement.executeUpdate();
            System.out.println("Utilisateur insere avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur afficher(int id) {
        String sql = "SELECT * FROM utilisateurs WHERE id_user = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Utilisateur(
                    resultSet.getInt("id_user"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("email"),
                    resultSet.getString("type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> afficherTous() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                utilisateurs.add(new Utilisateur(
                    resultSet.getInt("id_user"),
                    resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("email"),
                    resultSet.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public void modifier(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, type = ? WHERE id_user = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getType());
            statement.setInt(5, utilisateur.getIdUser());
            statement.executeUpdate();
            System.out.println("Utilisateur modifie avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id_user = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Utilisateur supprime avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
