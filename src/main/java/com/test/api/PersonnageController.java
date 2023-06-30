package com.test.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController

public class PersonnageController {
    private final List<Personnage> personnages = new ArrayList<>();
    private int idCount;

    @GetMapping("/personnage")
    @Operation(
            description = "Liste des personnages",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Liste ok!",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    public List<Personnage> getPersonages() {

        return personnages;
    }

    @PostMapping("/personnage")
    @Operation(
            description = "Ajout d'un personnage",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "personnage crée!",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )

    public Personnage ajouterPersonnage(
             @RequestBody Personnage personnage) {

        String name = personnage.getName();
        Type type= personnage.getType();


        Personnage personnageNew = new Personnage(this.idCount, name, type, 10);
        personnages.add(personnageNew);
        this.idCount++;

        return personnageNew;
    }


    @GetMapping("/personnage/{id}")
    @Operation(
            description = "Détail d'un personnage",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Détails",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "id n'existe pas",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )

    public ResponseEntity<Personnage> findPersonnage(@PathVariable("id") int id) {


        Optional<Personnage> optionalPersonnage = personnages.stream()
                .filter(person -> person.getId() == id)
                .findFirst();

        if (optionalPersonnage.isPresent()) {
            Personnage personnage = optionalPersonnage.get();
            return ResponseEntity.ok(personnage);
        } else {
            ResponseEntity<Personnage> responseEntity = new ResponseEntity(null,HttpStatus.NOT_FOUND);
            return responseEntity;
        }

    }

    @PutMapping("/personnage/{id}")
    @Operation(
            description = "Modif d'un personnage",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Modif réalisé",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "id n'existe pas",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    public Personnage updatePersonnage(@PathVariable("id") int id,
                                                       @RequestBody Personnage personnageToUpdate) {

        Personnage personnage = personnages.stream().filter(person -> person.getId() == id).findFirst().orElse(null);

            personnage.setName(personnageToUpdate.getName());
            personnage.setType(personnageToUpdate.getType());
            personnage.setLifePoint(personnageToUpdate.getLifePoint());

            return personnage;

}

    @DeleteMapping("/personnage/{id}")
    public ResponseEntity<String> deletePersonnage(@PathVariable("id") int id) {
        Personnage personnage = personnages.stream().filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);

        personnages.remove(personnage);

        return ResponseEntity.ok("Personnage supprimé");
    }
}
