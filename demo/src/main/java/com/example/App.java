// package com.example;

// import javafx.application.Application;
// import javafx.application.Platform;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// import java.io.IOException;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// import com.example.controllers.AddPersonController;

// public class App extends Application {


//     public static void main(String[] args) {
//         // Lancer l'application JavaFX
//         launch(args);
//     }
   
//     @Override
// public void start(Stage primaryStage) {
//     // Connexion à la base de données
//     try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpp", "root", "")) {
//         // Charger l'interface utilisateur JavaFX depuis le fichier FXML
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ajout.fxml"));

//         Parent root = loader.load();
        

//         // Configurer la scène
//         Scene scene = new Scene(root, 600, 400);

//         // Configurer et afficher la fenêtre principale
//         primaryStage.setTitle("Gestion des Personnes");
//         primaryStage.setScene(scene);
//         primaryStage.show();

//         // Si vous avez besoin d'accéder au contrôleur de votre interface, vous pouvez le faire comme suit :
//         AddPersonController ajoutController = loader.getController();
//         // Vous pouvez appeler des méthodes ou définir des valeurs dans votre contrôleur si nécessaire.
//     } catch (SQLException | IOException e) {
//         e.printStackTrace();
//     }
// }


//     // Reste du code...
// }










package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.controllers.AddPersonController;
import com.example.controllers.AddPhoneController;
import com.example.controllers.SupprimerPersonneController;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpp", "root", "")) {
            // Charger l'interface d'ajout de personne depuis le fichier FXML
            FXMLLoader personneLoader = new FXMLLoader(getClass().getResource("/fxml/Ajout.fxml"));
            Parent personneRoot = personneLoader.load();
            AddPersonController addPersonController = personneLoader.getController();

            // Charger l'interface d'ajout de téléphone depuis le fichier FXML
            FXMLLoader phoneLoader = new FXMLLoader(getClass().getResource("/fxml/Ajouttel.fxml"));
            Parent phoneRoot = phoneLoader.load();
            AddPhoneController addPhoneController = phoneLoader.getController();
            // interface supprimer personne : 
            FXMLLoader suppressionLoader = new FXMLLoader(getClass().getResource("/fxml/SupprimePers.fxml"));
            Parent suppressionRoot = suppressionLoader.load();
            SupprimerPersonneController suppressionController = suppressionLoader.getController();
            // interface liste des personne 
            FXMLLoader affichLoader = new FXMLLoader(getClass().getResource("/fxml/affiche.fxml"));
             Parent afficheRoot = affichLoader.load();
            // Créer un TabPane pour afficher les deux interfaces dans des onglets
            TabPane tabPane = new TabPane();
            Tab personneTab = new Tab("Ajout Personne");
            personneTab.setContent(personneRoot);
            
            Tab phoneTab = new Tab("Ajout Téléphone");
            phoneTab.setContent(phoneRoot);
            
            Tab suppressionTab = new Tab("Suppression Personne");
            suppressionTab.setContent(suppressionRoot);
            
            Tab afficheTab = new Tab("Affiche Personne ");
            afficheTab .setContent(afficheRoot);
            
            tabPane.getTabs().addAll(personneTab, phoneTab, suppressionTab);

            // Configurer la scène avec le TabPane
            Scene scene = new Scene(tabPane, 800, 600);

            // Configurer et afficher la fenêtre principale
            primaryStage.setTitle("Gestion des Personnes et Téléphones");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Vous pouvez accéder aux contrôleurs ici si nécessaire
            addPersonController.setStage(primaryStage);
            addPhoneController.setStage(primaryStage);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

















