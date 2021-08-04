package net.intelie.challenges;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EventTest {

	EventServices services = new EventServices();
	EventIteratorService eventIterator = new EventIteratorService(services.findAll());

	Event event = new Event("some_type", 123L);
	Event event2 = new Event("some_type2", 1235L);
	Event event3 = new Event("some_type", 1234567L);

	@Test
	public void thisIsAWarning() throws Exception {
		Event event = new Event("some_type", 123L);

		// THIS IS A WARNING:
		// Some of us (not everyone) are coverage freaks.
		assertEquals(123L, event.timestamp());
		assertEquals("some_type", event.type());
	}

	@Test
	public void insertShouldInsertNewEventInEventStore() throws Exception {
		services.insert(event);
		assertEquals(1, services.findAll().size());
	}

	@Test
	public void removeAllShouldRemoveAllEventsOfType() {
		services.insert(event);
		services.insert(event);
		services.insert(event2);

		assertEquals(3, services.findAll().size());

		services.removeAll("some_type");
		assertEquals(1, services.findAll().size());
	}

	@Test
	public void queryShouldReturnEventsByTypeAndTimestamp() {
		services.insert(event2);
		services.insert(event);
		services.insert(event3);


		EventIterator iterator = services.query("some_type", 123L, 123456L);

		assertEquals(true, iterator.moveNext());
		
		assertEquals("some_type", iterator.current().type());
		System.out.println(iterator.toString());
		iterator.remove();
		assertThrows(IllegalStateException.class, () -> {
			iterator.current();
		});
		System.out.println(iterator.toString());

		assertEquals(false, iterator.moveNext());	

	}
}