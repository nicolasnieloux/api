package com.test.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController

public class PersonnageController {

    private final PersonnageDao personnageDao;

    public PersonnageController(PersonnageDao personnageDao) {
        this.personnageDao = personnageDao;
    }

    private int idCount;

    @GetMapping("/personnage")
    public Iterable<Personnage> getPersonages() {

        return personnageDao.findAll();
    }

    @PostMapping("/personnage")

    public Personnage ajouterPersonnage(
            @RequestBody Personnage personnage) {

        if (personnageDao.existsById(personnage.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "déjà existant");
        }
        String name = personnage.getName();
        Type type = Type.valueOf(String.valueOf(personnage.getType()));

        Personnage perso = new Personnage(name, type);
        personnageDao.save(perso);
        return perso;
    }
//

    @GetMapping("/personnage/{id}")
    public ResponseEntity<Personnage> findPersonnage(@PathVariable("id") int id) {

        Optional<Personnage> optionalPersonnage = personnageDao.findById(id);

        if (optionalPersonnage.isPresent()) {
            Personnage personnage = optionalPersonnage.get();
            return ResponseEntity.ok(personnage);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/personnage/{id}")
    public void deletePersonnage(@PathVariable("id") int id) {

        personnageDao.deleteById(id);
    }

    @PutMapping("/personnage/{id}")
    public Personnage updatePersonnage(@PathVariable("id") int id,
                                       @RequestBody Personnage personnageToUpdate) {

        Optional<Personnage> personnage = personnageDao.findById(id);

        if (personnage.isPresent()) {
            Personnage perso = personnage.get();
            perso.setName(personnageToUpdate.getName());

            perso.setType(personnageToUpdate.getType());
            perso.setLifePoint(personnageToUpdate.getLifePoint());

            personnageDao.save(perso);
        }

        return null;

    }

}
