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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/events")

public class EventController {

	@Autowired
	private EventService services;

	@ApiOperation(value = "Returns a list of all events registred.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return the list of events") })
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(services.findAll());
	}

	@ApiOperation(value = "Returns a list of all events registred with the type and timeline specified.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return the list of events"),
			@ApiResponse(code = 404, message = "No events were found.") })
	@GetMapping(path = "/search")
	public ResponseEntity<?> searchQuery(@RequestParam("type") String type, @RequestParam("start_time") long startTime,
			@RequestParam("end_time") long endTime) {
		services.queryEvents(type, startTime, endTime);
		return ResponseEntity.ok().body(services.queryEvents(type, startTime, endTime));
	}

	@SuppressWarnings("static-access")
	@ApiOperation(value = "Create a new event.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "The event was created"),
			@ApiResponse(code = 400, message = "A problem was found to create the event.") })
	@PostMapping
	public ResponseEntity<?> createEvent(@Valid @RequestBody EventDTO eventRequest) {
		services.insertEvent(eventRequest);
		return new ResponseEntity<>(HttpStatus.CREATED).ok().body("Event: " + eventRequest.getType() + " was created.");
	}

	@ApiOperation(value = "Delete all events of a specified type.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The events were deleted with success"),
			@ApiResponse(code = 404, message = "No events were found.") })
	@DeleteMapping
	public ResponseEntity<?> deleteEventsByType(@RequestParam("type") String type) throws Exception {
		services.deleteEventsByType(type);
		return ResponseEntity.ok().body("Events of type: " + type + " were deleted with success!");
	}

}
