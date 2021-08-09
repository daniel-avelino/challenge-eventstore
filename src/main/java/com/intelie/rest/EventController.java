package com.intelie.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intelie.dto.EventDTO;
import com.intelie.services.EventService;

@RestController
@RequestMapping(path = "/events")
public class EventController {

	@Autowired
	private EventService services;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(services.findAll());
	}

	@GetMapping(path = "/search")
	public ResponseEntity<?> searchQuery(@RequestParam("type") String type, @RequestParam("start_time") long startTime,
			@RequestParam("end_time") long endTime) {
		services.queryEvents(type, startTime, endTime);
		return ResponseEntity.ok().body(services.queryEvents(type, startTime, endTime));
	}

	@SuppressWarnings("static-access")
	@PostMapping
	public ResponseEntity<?> createEvent(@Valid @RequestBody EventDTO eventRequest) {
		services.insertEvent(eventRequest);
		return new ResponseEntity<>(HttpStatus.CREATED).ok().body("Event: " + eventRequest.getType() + " was created.");
	}

	@DeleteMapping
	public ResponseEntity<?> deleteEventsByType(@RequestParam("type") String type) throws Exception {
		services.deleteEventsByType(type);
		return ResponseEntity.ok().body("Events of type: " + type + " were deleted with success!");
	}

}
