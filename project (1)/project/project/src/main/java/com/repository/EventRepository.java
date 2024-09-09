package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByPetIdOrderByDateDesc(int petId);

	io.micrometer.observation.Observation.Event save(io.micrometer.observation.Observation.Event event);
}
