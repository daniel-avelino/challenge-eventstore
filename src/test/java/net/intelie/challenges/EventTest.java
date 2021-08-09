package net.intelie.challenges;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class EventTest {

	EventServices services = new EventServices();

	EventIteratorService eventIterator = new EventIteratorService(services.findAll());

	// The setUp with annotation @Before includes 5 new events in the store, here it's verifying if  
	// exists 5 events int the store like expected.
	@Test
	public void insertShouldInsertNewEventsInEventStore() throws Exception {
		assertEquals(5, services.findAll().size());
	}

	/**
	 * Using removeAll() to remove all events which have
	 * "Party" in the type. Ignoring if the type is on upper case or lower case.
	 * From the 5 events, there is 2 events with type "Party". So the expected
	 * remaining events should be 3 events.
	 **/
	@Test
	public void removeAllShouldRemoveAllEventsOfType() {
		assertEquals(5, services.findAll().size());

		services.removeAll("Party");
		assertEquals(3, services.findAll().size());
	}

	/**
	 * Using the query function implemented in
	 * EventServices from EventStore should return just events of the type and in
	 * the range of the timestamp, considering the startTime includes and the
	 * endTime excludes from that search. The iterator.moveNext() is a boolean which
	 * returns TRUE if the query found at least 1 event in the eventStore. The
	 * second assert ensure that event type found is the event type desired. The
	 * iterator.remove() should remove the current event, so if the current event
	 * was removed, the iterator.current() should throw a illegalStateException. If
	 * the query found just 1 event, the iterator.moveNext() should return false,
	 * because there isn't no more events to return.
	 **/
	@Test
	public void queryShouldReturnEventsByTypeAndTimestamp() {
		EventIterator iterator = services.query("Soccer game", 1L, 5L);

		assertEquals(true, iterator.moveNext());

		assertEquals("Soccer Game", iterator.current().type());

		iterator.remove();
		assertThrows(IllegalStateException.class, () -> {
			iterator.current();
		});

		assertEquals(false, iterator.moveNext());

	}

	/*
	 * This test tries remove a non existing event, so the expected result is that
	 * nothing happens
	 * 
	 */
	@Test
	public void removeAllShouldDoNothingIfTypeDoesntExist() {
		services.removeAll("NonExistingEvent");
		assertSame(services.findAll(), services.findAll());
	}
	
	/*
	 * This will run before the tests, including 5 events in store
	 * 
	 */
	@Before
	public void setUp() {
		services.insert(new Event("Party", 1L));
		services.insert(new Event("Soccer Game", 2L));
		services.insert(new Event("Basketball Game", 3L));
		services.insert(new Event("PARTY", 4L));
		services.insert(new Event("Show", 5L));
	}

}