package com.example;
import java.util.List;

public interface PersonneDAO {
    void add(Personne personne);
    Personne findById(String numCIN);
    List<Personne> findAll();
    void delete(String numCIN);
}
