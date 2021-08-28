package com.calendar.event.service;

import com.calendar.event.entity.Event;
import com.calendar.event.entity.EventCreateDTO;
import com.calendar.event.entity.EventFindDTO;
import com.calendar.event.entity.EventMoveDTO;
import com.calendar.event.error.EventNotFoundException;

import java.util.List;

public interface CalendarService {

    public List<Event> fetchEvent(EventFindDTO eventFindDTO) throws EventNotFoundException;

    public Event createEvent(EventCreateDTO eventCreateDTO);

    public Event moveEvent(EventMoveDTO eventMoveDTO) throws EventNotFoundException;
}
