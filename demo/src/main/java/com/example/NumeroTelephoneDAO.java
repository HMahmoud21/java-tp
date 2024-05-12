package com.example;

import java.util.List;

public interface NumeroTelephoneDAO {
    void add(NumeroTelephone numeroTelephone);
    NumeroTelephone findById(String valeur);
    List<NumeroTelephone> findAll();
}
