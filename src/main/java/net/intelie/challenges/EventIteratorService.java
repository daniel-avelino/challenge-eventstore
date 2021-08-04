package net.intelie.challenges;

import java.util.List;
import java.util.ListIterator;

public class EventIteratorService implements EventIterator {

	ListIterator<Event> eventsIterator;

	public EventIteratorService(List<Event> eventsIterator) {
		this.eventsIterator = eventsIterator.listIterator();
	}

	@Override
	public void close() throws Exception {

	}

	@Override
	public boolean moveNext() throws IllegalStateException {
		return this.eventsIterator.hasNext();
	}

	@Override
	public Event current() {
		try {
			return this.eventsIterator.next();
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	@Override
	public void remove(){
		try {
			this.eventsIterator.remove();
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

}