package com.example.dao.impl;

import com.example.dao.GenericDAO;
import com.example.model.Reservation;
import com.example.utils.PostgreSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements GenericDAO<Reservation> {

    @Override
    public void ajouter(Reservation reservation) {
        String sql = "INSERT INTO reservations (id_user, id_event, id_salle, id_terrain, date_reservation) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getIdUser());
            statement.setInt(2, reservation.getIdEvent());
            statement.setInt(3, reservation.getIdSalle());
            statement.setInt(4, reservation.getIdTerrain());
            statement.setDate(5, Date.valueOf(reservation.getDateReservation()));
            statement.executeUpdate();
            System.out.println("Réservation ajoutée avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation afficher(int id) {
        String sql = "SELECT * FROM reservations WHERE id_reservation = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(
                    resultSet.getInt("id_reservation"),
                    resultSet.getInt("id_user"),
                    resultSet.getInt("id_event"),
                    resultSet.getInt("id_salle"),
                    resultSet.getInt("id_terrain"),
                    resultSet.getDate("date_reservation").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> afficherTous() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                reservations.add(new Reservation(
                    resultSet.getInt("id_reservation"),
                    resultSet.getInt("id_user"),
                    resultSet.getInt("id_event"),
                    resultSet.getInt("id_salle"),
                    resultSet.getInt("id_terrain"),
                    resultSet.getDate("date_reservation").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void modifier(Reservation reservation) {
        String sql = "UPDATE reservations SET id_user = ?, id_event = ?, id_salle = ?, id_terrain = ?, date_reservation = ? WHERE id_reservation = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getIdUser());
            statement.setInt(2, reservation.getIdEvent());
            statement.setInt(3, reservation.getIdSalle());
            statement.setInt(4, reservation.getIdTerrain());
            statement.setDate(5, Date.valueOf(reservation.getDateReservation()));
            statement.setInt(6, reservation.getIdReservation());
            statement.executeUpdate();
            System.out.println("Réservation modifiée avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM reservations WHERE id_reservation = ?";
        try (Connection connection = PostgreSQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Réservation supprimée avec succès!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
