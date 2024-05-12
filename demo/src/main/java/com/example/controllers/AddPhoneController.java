package com.example.controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.NumeroTelephone;
import com.example.NumeroTelephoneDAO;
import com.example.NumeroTelephoneDAOImplDB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPhoneController {

    @FXML
    private TextField sv;

    @FXML
    private RadioButton tp;

    @FXML
    private RadioButton tf;

    @FXML
    private Button ajout;

    @FXML
    private Button annule;

    @FXML
   private void handleAjouter(ActionEvent event) {
        // Récupérer les valeurs des champs
        String valeur = sv.getText();
        char type = tp.isSelected() ? 'P' : 'F';

        // Vérifier que les champs obligatoires sont renseignés
        if (valeur.isEmpty()) {
            // Afficher un message d'erreur ou gérer la situation d'erreur
            System.out.println("Veuillez saisir la valeur du téléphone.");
            return;
        }

        // Appeler la méthode DAO pour ajouter le téléphone à la base de données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpp", "root", "")) {
            NumeroTelephoneDAO numeroTelephoneDAO = new NumeroTelephoneDAOImplDB(connection);
            NumeroTelephone nouveauNumero = new NumeroTelephone(valeur, type);
            numeroTelephoneDAO.add(nouveauNumero);
            System.out.println("Numéro de téléphone ajouté avec succès à la base de données.");
        } catch (SQLException e) {
            // Gérer les erreurs liées à la base de données
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du numéro de téléphone à la base de données.");
        }
    }

    public void setStage(Stage primaryStage) {
    }

}
