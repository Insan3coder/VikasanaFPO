package com.Project.demo.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Project.demo.dao.EventRepo;
import com.Project.demo.dto.EventDto;
import com.Project.demo.model.Events;

@Service
public class EventService {

    @Autowired
    EventRepo eventRepo;

    private Logger logger = LogManager.getLogger(EventService.class);

    @Transactional(readOnly = true)
    public List<EventDto> getAll(Long eventId, String eventName) {
        try {
            if (Objects.isNull(eventName) && !Objects.isNull(eventId))
                return eventRepo.findById(eventId).stream().map(x -> assignEventDto(x)).collect(Collectors.toList());
            else if (!Objects.isNull(eventName))
                return eventRepo.findByEventName(eventName).stream().map(x -> assignEventDto(x))
                        .collect(Collectors.toList());
            else if (Objects.isNull(eventName) && Objects.isNull(eventId))
                return eventRepo.findAll().stream().map(x -> assignEventDto(x))
                        .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
        return null;
    }

    private EventDto assignEventDto(Events event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(event.getEventId());
        eventDto.setEventName(event.getEventName());
        eventDto.setEventDetails(event.getEventDetails());
        return eventDto;
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public boolean createEvent(EventDto event) {
        try {
            Events eventDb = new Events();
            eventDb.setEventDetails(event.getEventDetails());
            eventDb.setEventName(event.getEventName());
            eventRepo.save(eventDb);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }

    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public void updateEvent(Long eventId, EventDto event) {
        try {
            if (Objects.isNull(event.getEventName()) && !Objects.isNull(event.getEventDetails()))
                eventRepo.updateEventDetailsByEventId(eventId, event.getEventDetails());
            else if (Objects.isNull(event.getEventDetails()) && !Objects.isNull(event.getEventName()))
                eventRepo.updateEventNameByEventId(eventId, event.getEventName());
            else if (!Objects.isNull(event.getEventName()) && !Objects.isNull(event.getEventDetails()))
                eventRepo.updateEventByEventId(eventId, event.getEventName(), event.getEventDetails());
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

    @Transactional(readOnly = false, rollbackFor = SQLException.class)
    public void deleteEvent(Long eventId) {
        try {
            eventRepo.deleteById(eventId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ApplicationContextException(e.getMessage());
        }
    }

}
