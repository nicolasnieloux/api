package com.test.api;

import org.springframework.web.bind.annotation.*;


@RestController

public class PersonnageController {


    private final PersonnageDao personnageDao;

    public PersonnageController(PersonnageDao personnageDao){
        this.personnageDao = personnageDao;
    }
    private int idCount;

    @GetMapping("/personnage")
    public Iterable<Personnage> getPersonages() {

        Iterable<Personnage> personnages = personnageDao.findAll();
        return personnages;
    }
//
//    @PostMapping("/personnage")
//    @Operation(
//            description = "Ajout d'un personnage",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "personnage crée!",
//                            content = @Content(
//                                    mediaType = "application/json"
//                            )
//                    )
//            }
//    )
//
//    public Personnage ajouterPersonnage(
//             @RequestBody Personnage personnage) {
//
//        String name = personnage.getName();
//        Type type= personnage.getType();
//
//
//        Personnage personnageNew = new Personnage(this.idCount, name, type, 10);
//        personnages.add(personnageNew);
//        this.idCount++;
//
//        return personnageNew;
//    }
////

//    @GetMapping("/personnage/{id}")
//       public Personnage findPersonnage(@PathVariable("id") int id) {
//            return personnageDao.findById(id);
//
//    }

//    @PutMapping("/personnage/{id}")
//      public Personnage updatePersonnage(@PathVariable("id") int id,
//                                                       @RequestBody Personnage personnageToUpdate) {
//
//        Personnage personnage = personnages.stream().filter(person -> person.getId() == id).findFirst().orElse(null);
//
//            personnage.setName(personnageToUpdate.getName());
//            personnage.setType(personnageToUpdate.getType());
//            personnage.setLifePoint(personnageToUpdate.getLifePoint());
//
//            return personnage;
//
//}
//
//    @DeleteMapping("/personnage/{id}")
//    public ResponseEntity<String> deletePersonnage(@PathVariable("id") int id) {
//        Personnage personnage = personnages.stream().filter(person -> person.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        personnages.remove(personnage);
//
//        return ResponseEntity.ok("Personnage supprimé");
//    }
}
