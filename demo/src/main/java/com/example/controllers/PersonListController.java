package com.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.example.Personne;
import com.example.PersonneDAO;
import com.example.PersonneDAOImplDB;

public class PersonListController {

    @FXML

    private TableView<Personne> tableView;

    @FXML
    private TableColumn<Personne, String> nomm;  // Make sure the fx:id matches

    @FXML
    private TableColumn<Personne, String> prenomm;

    // ... autres colonnes si nécessaire

    public void initialize() {
        // Initialisez vos colonnes ici si nécessaire

        // Chargez les données dans le TableView
        loadPersonData();
    }

    private void loadPersonData() {
    // Obtenez les données de la base de données
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpp", "root", "")) {
        PersonneDAO personneDAO = new PersonneDAOImplDB(connection);
        List<Personne> personnes = personneDAO.findAll();

        // Utilisez Platform.runLater pour mettre à jour l'interface utilisateur depuis un thread non-JavaFX
        Platform.runLater(() -> {
            // Effacez les anciennes données du TableView
            tableView.getItems().clear();

            // Ajoutez les données à votre TableView
            tableView.getItems().addAll(personnes);
        });

    } catch (SQLException e) {
        e.printStackTrace();
        // Gérer les erreurs liées à la base de données
    }
}
}