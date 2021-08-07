package com.intelie.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.intelie.dto.EventDTO;
import com.intelie.models.Event;

@Service
public class EventMapper {

	public Event toEvent(EventDTO dto) {
		Event event = new Event(dto.getId(), dto.getType(), dto.getTimestamp());
		return event;
	}

	public EventDTO toEventDTO(Event event) {
		EventDTO dto = new EventDTO(event.getId(), event.getType(), event.getTimestamp());
		return dto;
	}

	public List<EventDTO> toEventDTOlist(List<Event> events) {
		List<EventDTO> dtoList = events.stream().map(x -> new EventDTO(x.getId(), x.getType(), x.getTimestamp()))
				.collect(Collectors.toList());
		return dtoList;
	}
}
