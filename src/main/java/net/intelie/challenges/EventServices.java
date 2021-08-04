package net.intelie.challenges;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventServices implements EventStore {

	List<Event> events = new ArrayList<>();


	@Override
	public void insert(Event event) {
		events.add(event);
	}

	@Override
	public void removeAll(String type) {
		events.removeIf(x -> x.type().equalsIgnoreCase(type));
	}

	@Override
	public EventIterator query(String type, long startTime, long endTime) {
		EventIteratorService iteratorServices = new EventIteratorService(findEvents(type, startTime, endTime));
		return iteratorServices;
	}

	public List<Event> findAll() {
		return this.events;
	}

	public List<Event> findEvents(String type, long startTime, long endTime) {
		List<Event> eventsIterator = new ArrayList<>();

		eventsIterator = events.stream()
				.filter(x -> x.type().equalsIgnoreCase(type) && x.timestamp() < endTime && x.timestamp() >= startTime)
				.collect(Collectors.toList());

		return eventsIterator;
	}

}
