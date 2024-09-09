package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Pet;
import com.service.EventService;
import com.service.PetService;
import com.entity.Event;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Pet> getAllPets(@RequestParam(required = false) String species) {
        return petService.getAllPets(species);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Pet> getPetById(@PathVariable int id,
//                                          @RequestParam(required = false) String sortKey,
//                                          @RequestParam(required = false) String sortOrder) {
    	
    	@GetMapping("/{id}")
        public ResponseEntity<Pet> getPetById(@PathVariable int id) {
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        List<com.entity.Event> events = eventService.getEventsByPetId(id);
        pet.setEvents(events);
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        try {
            Pet newPet = petService.addPet(pet);
            return ResponseEntity.ok(newPet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable int id, @RequestBody Pet petDetails) {
        Pet updatedPet = petService.updatePet(id, petDetails);
        if (updatedPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable int id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/events")
    public ResponseEntity<Event> addEvent(@PathVariable int id, @RequestBody Event event) {
        Pet pet = petService.getPetById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        event.setPet(pet);
        Event newEvent = eventService.addEvent(event);
        return ResponseEntity.ok(newEvent);

    }
}
