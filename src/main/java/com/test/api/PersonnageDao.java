package com.test.api;

import java.util.List;



public interface PersonnageDao {
    List<Personnage> findAll();
    Personnage findById(int id);
    Personnage save(Personnage personnage);
}