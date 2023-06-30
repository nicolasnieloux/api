package com.test.api;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonnageDaolmpl implements PersonnageDao {
    private static final List<Personnage> personnages = new ArrayList<>();

    static {
        personnages.add(new Personnage(1, "Charly portable", Type.Guerrier, 10));
        personnages.add(new Personnage(2, "Cedric Robot", Type.Magicien, 11));
        personnages.add(new Personnage(3, "Christophe de Ping Pong", Type.Guerrier, 12));
    }

    @Override
    public List<Personnage> findAll() {
        return personnages;
    }

    @Override
    public Personnage findById(int id) {
        for (Personnage personnage : personnages) {
            if (personnage.getId() == id) {
                return personnage;
            }
        }
        return null;

    }

    @Override
    public Personnage save(Personnage personnage) {
        personnages.add(personnage);

        return personnage;
    }

}