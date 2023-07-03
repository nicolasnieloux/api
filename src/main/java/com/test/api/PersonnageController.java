package com.test.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController

public class PersonnageController {

    private final PersonnageDao personnageDao;

    public PersonnageController(PersonnageDao personnageDao) {
        this.personnageDao = personnageDao;
    }

    private int idCount;

    @GetMapping("/personnage")
    public Iterable<Personnage> getPersonages() {

        Iterable<Personnage> personnages = personnageDao.findAll();
        return personnages;
    }

    @PostMapping("/personnage")

    public ResponseEntity<Void> ajouterPersonnage(
            @RequestBody Personnage personnage) {

        Personnage personnageAdd = personnageDao.save(personnage);
        if (personnageAdd == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(personnageAdd.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
//

    @GetMapping("/personnage/{id}")
    public Personnage findPersonnage(@PathVariable("id") int id) {
        return personnageDao.findById(id);

    }


    @DeleteMapping("/personnage/{id}")
    public void deletePersonnage(@PathVariable("id") int id) {

        personnageDao.deleteById(id);
    }

    @PutMapping("/personnage/{id}")
    public Personnage updatePersonnage(@PathVariable("id") int id,
                                       @RequestBody Personnage personnageToUpdate) {

        Personnage personnage = personnageDao.findById(id);

        personnage.setName(personnageToUpdate.getName());
        personnage.setType(personnageToUpdate.getType());
        personnage.setLifePoint(personnageToUpdate.getLifePoint());

        return personnageDao.save(personnage);

    }

}
