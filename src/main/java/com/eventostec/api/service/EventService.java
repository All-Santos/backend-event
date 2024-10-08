package com.eventostec.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRequestDto;
import com.eventostec.api.repositories.EventRepository;

@Service
public class EventService {

     @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired 
    private EventRepository repository;

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
        newEvent.setRemote(data.remote());

        repository.save(newEvent);

        return newEvent;

       
    }
    private String uploadImg(MultipartFile multipartFile ){
        String fileName = UUID.randomUUID() + "-"+ multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName,fileName,file);
            file.delete();
            return s3Client.getUrl(bucketName,fileName).toString();
            
        } catch (Exception e) {
            System.out.println("erro ao subir arquivo");
            return null;
        }
        
    }
    private File convertMultipartToFile(MultipartFile multipartFile ) throws IOException {
        File convFile = new File (Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos =new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;



    }


}
