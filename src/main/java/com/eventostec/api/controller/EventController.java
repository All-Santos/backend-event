package com.eventostec.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDto;
import com.eventostec.api.service.EventService;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    public ResponseEntity<Event> create(@RequestBody EventRequestDto body){
        Event newEvent = this.eventService.createEvent(body);
        return ResponseEntity.ok(newEvent);
    }

}
