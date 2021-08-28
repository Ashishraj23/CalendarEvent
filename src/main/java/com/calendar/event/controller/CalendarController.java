package com.calendar.event.controller;

import com.calendar.event.entity.Event;
import com.calendar.event.entity.EventCreateDTO;
import com.calendar.event.entity.EventFindDTO;
import com.calendar.event.entity.EventMoveDTO;
import com.calendar.event.error.EventNotFoundException;
import com.calendar.event.service.CalendarService;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarController {

    @Autowired
    CalendarService calendarService;


    @PostMapping("/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Event createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
       return calendarService.createEvent(eventCreateDTO);
    }

    @PostMapping("/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    List<Event> events(@RequestBody EventFindDTO eventFindDTO) throws EventNotFoundException {
        return calendarService.fetchEvent(eventFindDTO);
    }

    @PutMapping("/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveDTO eventMoveDTO) throws EventNotFoundException {
        return calendarService.moveEvent(eventMoveDTO);
    }

}
