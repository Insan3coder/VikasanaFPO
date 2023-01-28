package com.Project.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Project.demo.model.Events;

@Repository
public interface EventRepo extends JpaRepository<Events, Long> {

    List<Events> findByEventName(String eventName);

    @Modifying
    @Query(value = "update Events e set e.eventName=:eventName where e.eventId=:eventId", nativeQuery = false)
    void updateEventNameByEventId(Long eventId, String eventName);

    @Modifying
    @Query("update Events e set e.eventDetails=:eventDetails where e.eventId=:eventId")
    void updateEventDetailsByEventId(Long eventId, String eventDetails);

    @Modifying
    @Query("update Events e set e.eventName=:eventName,e.eventDetails=:eventDetails where e.eventId=:eventId")
    void updateEventByEventId(Long eventId, String eventName, String eventDetails);

}
