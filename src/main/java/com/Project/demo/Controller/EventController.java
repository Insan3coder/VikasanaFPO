package com.Project.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Project.demo.Service.EventService;
import com.Project.demo.dto.EventDto;

@Component
@RestController()
// @CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<EventDto> getEvents(@RequestParam(value = "eventId", required = false) Long eventId,
            @RequestParam(value = "eventName", required = false) String eventName) {
        return eventService.getAll(eventId, eventName);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Object> createEvent(@RequestBody EventDto event) {
        boolean status = eventService.createEvent(event);
        if (status)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void updateEvent(@PathVariable(value = "id") Long eventId, @RequestBody EventDto event) {
        eventService.updateEvent(eventId, event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable(value = "id") Long eventId) {
        eventService.deleteEvent(eventId);
    }

}
