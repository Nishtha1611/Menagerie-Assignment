package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findBySpecies(String species);
}

