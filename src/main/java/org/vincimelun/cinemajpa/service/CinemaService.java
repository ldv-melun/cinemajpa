package org.vincimelun.cinemajpa.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincimelun.cinemajpa.dao.FilmRepository;
import org.vincimelun.cinemajpa.dao.PersonneRepository;
import org.vincimelun.cinemajpa.dao.RoleRepository;
import org.vincimelun.cinemajpa.formdata.FilmFormDTO;
import org.vincimelun.cinemajpa.formdata.PersonFormDTO;
import org.vincimelun.cinemajpa.formdata.RoleFormDTO;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;
import org.vincimelun.cinemajpa.model.Role;

import java.io.IOException;
import java.util.List;

@Service
public class CinemaService {
    FilmRepository filmRepository;
    PersonneRepository personneRepository;
    RoleRepository roleRepository;
    ImageManager imageManager;

    @Autowired
    public CinemaService(FilmRepository filmRepository,
                         PersonneRepository personneRepository,
                         RoleRepository roleRepository,
                         ImageManager imageManager){
        this.filmRepository = filmRepository;
        this.personneRepository = personneRepository;
        this.roleRepository = roleRepository;
        this.imageManager  = imageManager;
    }


    public List<Personne> getPersonnes(){
        return Lists.newArrayList(personneRepository.findAll());
    }

    public Personne getPersonne(Long id){
        return personneRepository.findById(id).orElse(new Personne());
    }

    public void deletePerson(long id){
        personneRepository.deleteById(id);
    }

    public void deletePerson(Personne person){
        this.deletePerson(person.getId());
    }

    public void savePerson(PersonFormDTO dto) {
        Personne person = personneRepository.findById(dto.getId()).orElse(new Personne());
        person.setNom(dto.getNom());
        person.setPrenom(dto.getPrenom());
        person.setAnneeNaiscance(dto.getAnneeNaissance());;
        if(!dto.getFicPhoto().isEmpty()){
            try{
                imageManager.savePhoto(person, dto.getFicPhoto().getInputStream());
            } catch (IOException ioe){
                System.out.println("Erreur : "+ioe.getMessage());
            }
        }
        personneRepository.save(person);
    }

    public List<Film> getFilms(){
        return Lists.newArrayList(filmRepository.findAll());
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id).orElse(new Film());
    }

    public void saveFilm(Film film){
        filmRepository.save(film);
    }

    public void deleteFilm(long id){
        filmRepository.deleteById(id);
    }

    public void saveFilm(FilmFormDTO filmDTO){
        Film filmDB = filmRepository.findById(filmDTO.getId()).orElse(new Film());
        //Film filmDB = getFilm(filmDTO.getId());
        filmDB.setTitre(filmDTO.getTitre());
        filmDB.setResume(filmDTO.getResume());
        filmDB.setNote(filmDTO.getNote());
        Personne realisateur = personneRepository.findById(filmDTO.getRealisateurId()).orElse(new Personne());
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

    public void saveRole(RoleFormDTO roleFormDTO) {
        Role roleDB = roleRepository.findById(roleFormDTO.getId()).orElse(new Role());
        roleDB.setName(roleFormDTO.getName());
        roleDB.setOrder(roleFormDTO.getOrder());
        roleDB.setActeur(personneRepository.findById(roleFormDTO.getActeurId()).get());
        roleDB.setFilm(filmRepository.findById(roleFormDTO.getFilmId()).get());
        roleRepository.save(roleDB);
    }
}
