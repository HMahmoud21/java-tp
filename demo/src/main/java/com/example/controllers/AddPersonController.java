// package com.example.controllers;

// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.RadioButton;
// import javafx.scene.control.TextField;

// public class AddPersonController {

//     @FXML
//     private TextField numcin;

//     @FXML
//     private TextField snom;

//     @FXML
//     private TextField sprenoom;

//     @FXML
//     private RadioButton cfemme;

//     @FXML
//     private RadioButton cHomme;

//     @FXML
//     private Button ajout;

//     @FXML
//     private Button anuul;

//     // Ajoutez les méthodes pour gérer les actions de l'utilisateur
// }


package com.example.controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.Personne;
import com.example.PersonneDAO;
import com.example.PersonneDAOImplDB;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
 import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddPersonController {

    @FXML
    private TextField numcin;

    @FXML
    private TextField snom;

    @FXML
    private TextField sprenoom;

    @FXML
    private RadioButton cfemme;

    @FXML
    private RadioButton cHomme;

    @FXML
      private void handleAjouter(ActionEvent event) {
        // Récupérer les valeurs des champs
        String numeroCin = numcin.getText();
        String nom = snom.getText();
        String prenom = sprenoom.getText();
        char civilite = cfemme.isSelected() ? 'F' : 'H';

        // Vérifier que les champs obligatoires sont renseignés
        if (numeroCin.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
            // Afficher un message d'erreur ou gérer la situation d'erreur
            System.out.println("Veuillez remplir tous les champs obligatoires.");
            return;
        }

        // Appeler la méthode DAO pour ajouter la personne à la base de données
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpp", "root", "")) {
            PersonneDAO personneDAO = new PersonneDAOImplDB(connection);
            Personne nouvellePersonne = new Personne(numeroCin, nom, prenom, String.valueOf(civilite));
            personneDAO.add(nouvellePersonne);
            System.out.println("Personne ajoutée avec succès à la base de données.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout de la personne à la base de données.");
        }
    }

    public void setStage(Stage primaryStage) {
    }
}
