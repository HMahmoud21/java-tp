package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {

    // URL de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/annuairetel";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    private Connection connexion;

    // Constructeur
    public MaConnexion() {
        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établissement de la connexion
            connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
            System.out.println("Connexion à la base de données établie.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la connexion à la base de données.");
        }
    }

    // Méthode pour obtenir la connexion
    public Connection getConnexion() {
        return connexion;
    }

    // Méthode pour fermer la connexion
    public void fermerConnexion() {
        try {
            if (connexion != null && !connexion.isClosed()) {
                connexion.close();
                System.out.println("Connexion à la base de données fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la fermeture de la connexion à la base de données.");
        }
    }
}
