package org.vincimelun.cinemajpa.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vincimelun.cinemajpa.dao.FilmRepository;
import org.vincimelun.cinemajpa.dao.PersonneRepository;
import org.vincimelun.cinemajpa.service.ImageManager;

import java.io.IOException;
import java.io.InputStream;

@Controller()
@RequestMapping(value = "/img")
public class ImageController {

    FilmRepository filmDao;
    PersonneRepository personDao;
    ImageManager imageManager;

    @Autowired
    ImageController(FilmRepository filmRepository,
                    PersonneRepository personneRepository,
                    ImageManager imageManager){
        this.filmDao = filmRepository;
        this.personDao = personneRepository;
        this.imageManager = imageManager;
    }

    @GetMapping(value = "/film/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[]
    films(@PathVariable("id") Long id) {
        String posterName = filmDao.findById(id).get().getAfficheNom();
        InputStream is = imageManager.retreivePoster(posterName);
        byte[] image = null;
        try {
            image = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @GetMapping(value = "/person/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[]
    photos(@PathVariable("id") Long id) {
        String photoName = personDao.findById(id).get().getPhoto();
        InputStream is = imageManager.retreivePhoto(photoName);
        byte[] image = null;
        try {
            image = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }
}
