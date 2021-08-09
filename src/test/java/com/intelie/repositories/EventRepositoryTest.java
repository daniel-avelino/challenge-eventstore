package com.intelie.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.intelie.models.Event;

@DataJpaTest
public class EventRepositoryTest {

	@Autowired
	private EventRepository repository;

	@BeforeEach
	void setUp() {
		repository.save(new Event(1L, "Party", 1L));
	}

	@Test
	public void deleteEventsByTypeShouldDeleteAllEventsOfAType() {
		repository.deleteEventsByType("Soccer");
		assertFalse(repository.findEventByType("Soccer").isPresent());
	}

	@Test
	public void saveEventShouldInsertNewEvent() {
		assertTrue(repository.findEventByType("Party").isPresent());
	}

}
