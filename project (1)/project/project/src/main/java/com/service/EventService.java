package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.EventRepository;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<com.entity.Event> getEventsByPetId(int petId) {
        return eventRepository.findByPetIdOrderByDateDesc(petId);
    }

   
	public com.entity.Event addEvent(com.entity.Event event) {
        return eventRepository.save(event);
    }
}
