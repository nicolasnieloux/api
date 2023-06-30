package com.test.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface PersonnageDao extends JpaRepository<Personnage, Integer> {
    List<Personnage> findAll();
    Personnage findById(int id);
    Personnage save(Personnage personnage);
}