package com.example;

public class NumeroTelephone {
    private String valeur;
    private char type; // 'T' pour fixe professionnel, 'D' pour fixe à domicile, 'P' pour portable, '?' pour inconnu

    // Constructeur
    public NumeroTelephone(String valeur, char type) {
        this.valeur = valeur;
        this.type = type;
    }

    // Méthodes d'accès et de modification pour les propriétés

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String typeString;
        switch (type) {
            case 'T':
                typeString = "Fixe professionnel";
                break;
            case 'D':
                typeString = "Fixe à domicile";
                break;
            case 'P':
                typeString = "Portable";
                break;
            case '?':
                typeString = "Type inconnu";
                break;
            default:
                typeString = "Type non défini";
                break;
        }

        return "Numéro : " + valeur + " (Type : " + typeString + ")";
    }
}
