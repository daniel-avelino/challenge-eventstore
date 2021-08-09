package com.intelie.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intelie.dto.EventDTO;
import com.intelie.mappers.EventMapper;
import com.intelie.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private EventMapper mapper;

	@Transactional(readOnly = true)
	public List<EventDTO> findAll() {
		return mapper.toEventDTOlist(repository.findAll());
	}

	@Transactional
	public void insertEvent(EventDTO dto) {
		repository.save(mapper.toEvent(dto));
	}

	@Transactional
	public void deleteEventsByType(String type) throws Exception {
		int deletedEvents = repository.deleteEventsByType(type);
		if (deletedEvents == 0) {
			throw new EntityNotFoundException("Event not found.");
		}

	}

	@Transactional(readOnly = true)
	public List<EventDTO> queryEvents(String type, long startTime, long endTime) {
		List<EventDTO> eventsDTO = mapper.toEventDTOlist(repository.findEventsQuery(type, startTime, endTime));
		if (eventsDTO.isEmpty()) {
			throw new EntityNotFoundException("Events not found.");
		}
		return eventsDTO;
	}

}
