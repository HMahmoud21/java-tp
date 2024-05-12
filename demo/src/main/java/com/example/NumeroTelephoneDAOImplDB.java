package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NumeroTelephoneDAOImplDB implements NumeroTelephoneDAO {
    private Connection connexion;

    // Constructeur prenant une connexion à la base de données
    public NumeroTelephoneDAOImplDB(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void add(NumeroTelephone numeroTelephone) {
        // Implémentation de l'ajout d'un numéro de téléphone dans la base de données
        String query = "INSERT INTO numero_telephone (valeur, type) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, numeroTelephone.getValeur());
            preparedStatement.setString(2, String.valueOf(numeroTelephone.getType()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
    }

    @Override
    public NumeroTelephone findById(String valeur) {
        // Implémentation de la recherche d'un numéro de téléphone par sa valeur
        String query = "SELECT * FROM numero_telephone WHERE valeur = ?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, valeur);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Créer et retourner une instance de NumeroTelephone à partir des données de la base de données
                    return new NumeroTelephone(
                            resultSet.getString("valeur"),
                            resultSet.getString("type").charAt(0)
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return null; // Retourner null si le numéro de téléphone n'est pas trouvé
    }

    @Override
    public List<NumeroTelephone> findAll() {
        // Implémentation de la recherche de tous les numéros de téléphone dans la base de données
        List<NumeroTelephone> numerosTelephone = new ArrayList<>();
        String query = "SELECT * FROM numero_telephone";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Créer et ajouter une instance de NumeroTelephone à la liste à partir des données de la base de données
                numerosTelephone.add(new NumeroTelephone(
                        resultSet.getString("valeur"),
                        resultSet.getString("type").charAt(0)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return numerosTelephone;
    }
}
