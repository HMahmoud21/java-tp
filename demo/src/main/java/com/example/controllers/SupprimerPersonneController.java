package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.example.PersonneDAO;
import com.example.PersonneDAOImplDB;

public class SupprimerPersonneController implements Initializable {

    @FXML
    private TextField numcin;

    @FXML
    private Button supp;

    @FXML
    private Button annule;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void handleSupprimer() {
        String numeroCIN = numcin.getText();
    
        try (Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpp", "root", "")) {
            PersonneDAO personneDAO = new PersonneDAOImplDB(connexion); 
            personneDAO.delete(numeroCIN);
            
            System.out.println("Personne supprimée avec succès de la base de données.");
        } catch (SQLException e) {
            // Gérer les erreurs liées à la base de données
            e.printStackTrace();
            System.out.println("Erreur lors de la suppression de la personne de la base de données.");
        }
}
}

