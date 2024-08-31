package com.eventostec.api.service;

import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDto;

@Service
public class EventService {
    public Event createEvent(EventRequestDto data){
        String imageUrl = null;

        if (data.image() != null) {
            imageUrl =  this.uploadImg(data.image());
            
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventURL(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImageURL(imageUrl);

        return newEvent;

       
    }
    private String uploadImg(MultipartFile multipartFile ){
        return"";
    }


}
