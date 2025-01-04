package com.example.dao.impl;

import com.example.dao.GenericDAO;
import com.example.model.Terrain;
import com.example.utils.PostgreSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerrainDAOImpl implements GenericDAO<Terrain> {

    @Override
    public void ajouter(Terrain terrain) {
        String sql = "INSERT INTO terrains (nom_terrain, type) VALUES (?, ?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, terrain.getNomTerrain());
            statement.setString(2, terrain.getType());
            statement.executeUpdate();
            System.out.println("Terrain insere avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Terrain afficher(int id) {
        String sql = "SELECT * FROM terrains WHERE id_terrain = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Terrain(
                    resultSet.getInt("id_terrain"),
                    resultSet.getString("nom_terrain"),
                    resultSet.getString("type")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Terrain> afficherTous() {
        List<Terrain> terrains = new ArrayList<>();
        String sql = "SELECT * FROM terrains";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                terrains.add(new Terrain(
                    resultSet.getInt("id_terrain"),
                    resultSet.getString("nom_terrain"),
                    resultSet.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return terrains;
    }

    @Override
    public void modifier(Terrain terrain) {
        String sql = "UPDATE terrains SET nom_terrain = ?, type = ? WHERE id_terrain = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, terrain.getNomTerrain());
            statement.setString(2, terrain.getType());
            statement.setInt(3, terrain.getIdTerrain());
            statement.executeUpdate();
            System.out.println("Terrain modifie avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM terrains WHERE id_terrain = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Terrain supprime avec succes!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
