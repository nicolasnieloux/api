package com.test.api;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.test.api.Type.Magicien;

@RestController

public class PersonnageController {
    private final List<Personnage> personnages = new ArrayList<>();
    private int idCount ;

    @GetMapping("/personnage")
    public List<Personnage> getPersonages() {

        return personnages;
    }

    @PostMapping("/personnage")
    public ResponseEntity<String> ajouterPersonnage(
            @RequestParam("name") String name,
            @RequestParam("type") String type) {


        Personnage personnage = new Personnage(this.idCount, name, Type.valueOf(type),10 );
        personnages.add(personnage);
        this.idCount++;

        return ResponseEntity.ok("Personnage ajouté avec succès");
    }

    @GetMapping("/personnage/{id}")
    public ResponseEntity<Personnage> findPersonnage(@PathVariable("id") int id){

        Personnage personnage = personnages.stream().filter(person -> person.getId() == id).findFirst().orElse(null);

        return ResponseEntity.ok(personnage);
    }
    @PutMapping("/personnage/{id}")

    public ResponseEntity<Personnage> updatePersonnage(@PathVariable("id") int id,
                                                       @RequestParam("name") String name,
                                                       @RequestParam("type") String type) {

        Personnage personnage = personnages.stream().filter(person -> person.getId() == id).findFirst().orElse(null);

        personnage.setName(name);
        personnage.setType(Type.valueOf(type));


        return ResponseEntity.ok(personnage);
    }
    @DeleteMapping("/personnage/{id}")
    public ResponseEntity<String> deletePersonnage(@PathVariable("id") int id){
        Personnage personnage = personnages.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
        personnages.remove(personnage);

        return ResponseEntity.ok("Personnage supprimé avec succès");
    }
}
