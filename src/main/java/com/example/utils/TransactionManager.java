package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionManager {

    private final String url;
    private final String username;
    private final String password;

    public TransactionManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void executeInTransaction(Runnable operation) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            conn.setAutoCommit(false);
            
            try {
                operation.run();
                
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager("jdbc:postgresql://localhost:5432/Gestion_evenements",
                                                            "postgres",
                                                            "123");
        try {

            manager.executeInTransaction(() -> {
                System.out.println("Exécution de l'opération dans la transaction");
            });
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'exécution de la transaction : " + e.getMessage());
        }
    }
}
