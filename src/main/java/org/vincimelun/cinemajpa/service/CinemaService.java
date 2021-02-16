package org.vincimelun.cinemajpa.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincimelun.cinemajpa.dao.FilmRepository;
import org.vincimelun.cinemajpa.dao.PersonneRepository;
import org.vincimelun.cinemajpa.formdata.FilmFormDTO;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;

import java.io.IOException;
import java.util.List;

@Service
public class CinemaService {
    FilmRepository filmRepository;
    PersonneRepository personneRepository;
    ImageManager imageManager;

    @Autowired
    public CinemaService(FilmRepository filmRepository, PersonneRepository personneRepository, ImageManager imageManager){
        this.filmRepository = filmRepository;
        this.personneRepository = personneRepository;
        this.imageManager  = imageManager;
    }

    public List<Film> getFilms(){
        return Lists.newArrayList(filmRepository.findAll());
    }

    public List<Personne> getPersonnes(){
        return Lists.newArrayList(personneRepository.findAll());
    }

    public Personne getPersonne(Long id){
        return personneRepository.findById(id).get();
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id).get();
    }

    public void saveFilm(Film film){
        filmRepository.save(film);
    }

    public void updateFilm(FilmFormDTO filmDTO){
        Film filmDB = filmRepository.findById(filmDTO.getId()).get();
        filmDB.setTitre(filmDTO.getTitre());
        filmDB.setResume(filmDTO.getResume());
        filmDB.setNote(filmDTO.getNote());
        Personne realisateur = personneRepository.findById(filmDTO.getRealisateurId()).get();
        filmDB.setRealisateur(realisateur);
        // modifier l'affiche si nécessaire => si un fichier a été uploadé
        if(!filmDTO.getAffiche().isEmpty()) {
            try{
                imageManager.savePoster(filmDB, filmDTO.getAffiche().getInputStream());
            } catch (IOException ioe){
                System.out.println("Ereur : "+ioe.getMessage());
            }
        }

        filmRepository.save(filmDB);
    }
}
