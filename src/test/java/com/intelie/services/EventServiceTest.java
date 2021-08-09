package com.intelie.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intelie.models.Event;
import com.intelie.repositories.EventRepository;

@ExtendWith(SpringExtension.class)
public class EventServiceTest {

	@InjectMocks
	private EventService service;

	@Mock
	private EventRepository repository;

	@BeforeEach
	void setUp() {
		Event existingEvent = new Event(1L, "Party", 15L);

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(existingEvent);
	}

	@Test
	public void removeAllShouldThrowsExceptionWhenEvenDoesNotExist() throws Exception {
		assertThrows(EntityNotFoundException.class, () -> {
			service.deleteEventsByType("Party");
		});
		Mockito.verify(repository, Mockito.times(1)).deleteEventsByType("Party");
	}
}
