

package  com.example;
import java.util.ArrayList;
import java.util.List;

public class Personne {
    private String numCIN;
    private String nom;
    private String prenom;
    private String civilite;
    private List<String> numerosTelephone;

    // Constructeur
    public Personne(String numCIN, String nom, String prenom, String civilite) {
        this.numCIN = numCIN;
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.numerosTelephone = new ArrayList<>();
    }

    // Méthode pour ajouter un numéro de téléphone
    public void ajouterNumeroTelephone(String numero) {
        this.numerosTelephone.add(numero);
    }

    // Autres méthodes d'accès et de modification pour les propriétés

    public String getNumCIN() {
        return numCIN;
    }

    public void setNumCIN(String numCIN) {
        this.numCIN = numCIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public List<String> getNumerosTelephone() {
        return numerosTelephone;
    }

    public void setNumerosTelephone(List<String> numerosTelephone) {
        this.numerosTelephone = numerosTelephone;
    }

    @Override
    public String toString() {
        return civilite + " " + prenom + " " + nom + " (CIN: " + numCIN + ")";
    }
}
