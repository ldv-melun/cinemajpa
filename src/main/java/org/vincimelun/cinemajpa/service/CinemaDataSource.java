package org.vincimelun.cinemajpa.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincimelun.cinemajpa.dao.FilmRepository;
import org.vincimelun.cinemajpa.dao.PersonneRepository;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;

import java.util.List;

@Service
public class CinemaDataSource {
    FilmRepository filmRepository;
    PersonneRepository personneRepository;

    @Autowired
    public CinemaDataSource(FilmRepository filmRepository, PersonneRepository personneRepository){
        this.filmRepository = filmRepository;
        this.personneRepository = personneRepository;
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
}
