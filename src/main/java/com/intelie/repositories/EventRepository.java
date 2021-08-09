package com.intelie.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intelie.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query(nativeQuery = true, value = "SELECT * from tb_events WHERE type = ?1 AND timestamp >= ?2 AND timestamp < ?3")
	List<Event> findEventsQuery(String type, long startTime, long endTime);

	@Modifying
	@Query(nativeQuery = true, value = "DELETE from tb_events WHERE type = ?1")
	int deleteEventsByType(String type);

	Optional<Event> findEventByType(String type);
}
