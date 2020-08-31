package com.cats.lostandfound.controller;

import com.cats.lostandfound.entity.Message;
import com.cats.lostandfound.entity.Photo;
import com.cats.lostandfound.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @PostMapping(value = "/add")
    public Message<Photo> addPhoto(Photo photo) {
        return photoService.addPhoto(photo);
    }

    @GetMapping(value = "/delete_by_post_id/{post_id}")
    public Message<Photo> deleteByPostId(@PathVariable long post_id){
        return photoService.deleteByPostId(post_id);
    }

    @GetMapping(value = "/delete_by_photo_id/{photo_id}")
    public Message<Photo> deleteByPhotoId(@PathVariable long photo_id){
        return photoService.deleteByPhotoId(photo_id);
    }

    @GetMapping(value = "/get_photos/{post_id}")
    public Message<List<Photo>> findPhotosByPostId(@PathVariable long post_id) {
        return photoService.findPhotosByPostId(post_id);
    }

    @GetMapping(value = "/get_cover/{post_id}")
    public Message<Photo> findCoverByPostId(@PathVariable long post_id) {
        return photoService.findCoverByPostId(post_id);
    }
}
