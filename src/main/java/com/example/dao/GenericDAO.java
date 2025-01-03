package com.example.dao;

import java.util.List;

public interface GenericDAO<T> {
    
    void ajouter(T object);
    T afficher(int id);
    List<T> afficherTous();
    void modifier(T object);
    void supprimer(int id);

}
