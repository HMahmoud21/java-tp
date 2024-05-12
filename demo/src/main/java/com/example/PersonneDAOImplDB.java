package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAOImplDB implements PersonneDAO {
    private Connection connexion;

    // Constructeur prenant une connexion à la base de données
    public PersonneDAOImplDB(Connection connexion) {
        this.connexion = connexion;
    }

    @Override
    public void add(Personne personne) {
        // Implémentation de l'ajout d'une personne dans la base de données
        String query = "INSERT INTO personne (numCIN, nom, prenom, civilite) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, personne.getNumCIN());
            preparedStatement.setString(2, personne.getNom());
            preparedStatement.setString(3, personne.getPrenom());
            preparedStatement.setString(4, personne.getCivilite());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
    }

    @Override
    public Personne findById(String numCIN) {
        // Implémentation de la recherche d'une personne par son numéro CIN
        String query = "SELECT * FROM personne WHERE numCIN = ?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, numCIN);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Créer et retourner une instance de Personne à partir des données de la base de données
                    return new Personne(
                            resultSet.getString("numCIN"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("civilite")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return null; // Retourner null si la personne n'est pas trouvée
    }

    @Override
    public List<Personne> findAll() {
        // Implémentation de la recherche de toutes les personnes dans la base de données
        List<Personne> personnes = new ArrayList<>();
        String query = "SELECT * FROM personne";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Créer et ajouter une instance de Personne à la liste à partir des données de la base de données
                personnes.add(new Personne(
                        resultSet.getString("numCIN"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("civilite")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon vos besoins
        }
        return personnes;
    }
    // public void delete(String numCIN) {
    //     // Implémentation de la suppression d'une personne dans la base de données
    //     String query = "DELETE FROM personne WHERE numCIN = ?";
    //     try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
    //         preparedStatement.setString(1, numCIN);
    //         preparedStatement.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    public void delete(String numCIN) {
        // Vérifier d'abord si la personne existe
        if (personExists(numCIN)) {
            // La personne existe, on peut la supprimer
            String query = "DELETE FROM personne WHERE numCIN = ?";
            try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
                preparedStatement.setString(1, numCIN);
                preparedStatement.executeUpdate();
                System.out.println("Personne supprimée avec succès.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erreur lors de la suppression de la personne.");
            }
        } else {
            // La personne n'existe pas, afficher un message d'erreur
            System.out.println("Personne avec le numéro de CIN " + numCIN + " n'existe pas.");
        }
    }
    
    // Méthode pour vérifier si la personne existe
    private boolean personExists(String numCIN) {
        String query = "SELECT COUNT(*) FROM personne WHERE numCIN = ?";
        try (PreparedStatement preparedStatement = connexion.prepareStatement(query)) {
            preparedStatement.setString(1, numCIN);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // En cas d'erreur, considérer que la personne n'existe pas
    }
   
}

