package com.intelie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelie.dto.EventDTO;
import com.intelie.mappers.EventMapper;
import com.intelie.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private EventMapper mapper;

	public List<EventDTO> findAll() {
		return mapper.toEventDTOlist(repository.findAll());
	}

	public void insertEvent(EventDTO dto) {
		repository.save(mapper.toEvent(dto));
	}

	public void deleteEventsByType(String type) {
		repository.deleteEventsByType(type);
	}

	public List<EventDTO> queryEvents(String type, long startTime, long endTime) {
		return mapper.toEventDTOlist(repository.findEventsQuery(type, startTime, endTime));
	}

}
