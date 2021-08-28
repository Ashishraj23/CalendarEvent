package com.calendar.event.service;

import com.calendar.event.entity.Event;
import com.calendar.event.entity.EventCreateDTO;
import com.calendar.event.entity.EventFindDTO;
import com.calendar.event.entity.EventMoveDTO;
import com.calendar.event.error.EventNotFoundException;
import com.calendar.event.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    @Override
    public List<Event> fetchEvent(EventFindDTO eventFindDTO) throws EventNotFoundException {
        List<Event> events = calendarRepository.findBetween(eventFindDTO.getStart(), eventFindDTO.getEnd());
        if(CollectionUtils.isEmpty(events))
            throw new EventNotFoundException("Not a valid start and end Time");
        return events;
    }

    @Override
    @Transactional
    public Event createEvent(EventCreateDTO eventCreateDTO) {
        Event event = Event.builder().start(eventCreateDTO.getStart())
                .end(eventCreateDTO.getEnd()).text(eventCreateDTO.getText()).build();
        return calendarRepository.save(event);
    }

    @Override
    public Event moveEvent(EventMoveDTO eventMoveDTO) throws EventNotFoundException {
        Optional<Event> event = calendarRepository.findById(eventMoveDTO.getId());
        if(event.isPresent()) {
            Event event1 = event.get();
            event1.setStart(eventMoveDTO.getStart());
            event1.setEnd(eventMoveDTO.getEnd());
            return calendarRepository.save(event1);
        }else{
            throw new EventNotFoundException("Event has not created");
        }
    }
}
