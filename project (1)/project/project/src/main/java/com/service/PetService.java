package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Pet;
import com.repository.PetRepository;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets(String species) {
        if (species != null) {
            return petRepository.findBySpecies(species);
        }
        return petRepository.findAll();
    }

    public Pet getPetById(int id) {
        return petRepository.findById(id).orElse(null);
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePet(int id, Pet petDetails) {
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet != null) {
            pet.setName(petDetails.getName());
            pet.setOwner(petDetails.getOwner());
            pet.setSpecies(petDetails.getSpecies());
            pet.setSex(petDetails.getSex());
            pet.setBirth(petDetails.getBirth());
            pet.setDeath(petDetails.getDeath());
            return petRepository.save(pet);
        }
        return null;
    }

    public void deletePet(int id) {
        petRepository.deleteById(id);
    }
}
