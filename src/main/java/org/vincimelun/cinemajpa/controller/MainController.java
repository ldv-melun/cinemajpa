package org.vincimelun.cinemajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vincimelun.cinemajpa.formdata.FilmFormDTO;
import org.vincimelun.cinemajpa.formdata.PersonFormDTO;
import org.vincimelun.cinemajpa.formdata.RoleFormDTO;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;
import org.vincimelun.cinemajpa.service.CinemaService;

import java.util.List;

@Controller
public class MainController {
    private CinemaService cinemaService;

    @Autowired
    public MainController(CinemaService cinemaService){
        this.cinemaService = cinemaService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("films", cinemaService.getFilms());
        return "filmlist";
    }

    @GetMapping("/film/{id}")
    public String film(Model model, @PathVariable(name="id") Long id){
        Film film = cinemaService.getFilm(id);
        FilmFormDTO dto = new FilmFormDTO();
        dto.setId(film.getId());
        dto.setAfficheNom(film.getAfficheNom());
        dto.setTitre(film.getTitre());
        dto.setResume(film.getResume());
        dto.setRealisateurId(film.getRealisateur().getId());
        dto.setNote(film.getNote());
        List<Personne> personnes = cinemaService.getPersonnes();
        model.addAttribute("film", dto);
        model.addAttribute("persons", personnes);
        RoleFormDTO roleDTO = new RoleFormDTO();
        roleDTO.setFilmId(film.getId());
        model.addAttribute("role", roleDTO);
        return "filmform";
    }

    @PostMapping("/film")
    public String postFilm(@ModelAttribute(name="film") FilmFormDTO film){
//        // creer dans CinemaService la méthode de creation/modification du film à partir du DTO
//        MultipartFile file = film.getAffiche();
//        if(!file.isEmpty()) {
//            System.out.println("Nom du champ     : "+file.getName());
//            System.out.println("Nom du fichier   : "+file.getOriginalFilename());
//            System.out.println("Type de fichier  : "+file.getContentType());
//            System.out.println("Taille en octets : "+file.getSize());
//        }
//        System.out.println("Id réalisateur   : "+film.getRealisateurId());
        cinemaService.saveFilm(film);
        return "redirect:/";
    }

    @GetMapping("/person")
    public String listPersons(Model model){
        model.addAttribute("persons", cinemaService.getPersonnes());
        return "personlist";
    }

    @PostMapping("/person")
    public String postPerson(@ModelAttribute(name="person") PersonFormDTO dto){
        cinemaService.savePerson(dto);
        return "redirect:/person";
    }

    @GetMapping("/person/{id}")
    public String person(Model model, @PathVariable(name = "id") Long id){
        Personne person = cinemaService.getPersonne(id);
        PersonFormDTO dto = new PersonFormDTO();
        dto.setId(person.getId());
        dto.setNom(person.getNom());
        dto.setPrenom(person.getPrenom());
        dto.setAnneeNaissance(person.getAnneeNaiscance());
        dto.setPhoto(person.getPhoto());
        model.addAttribute("person", dto);
        return "personform";
    }

    @GetMapping("/person/add")
    public String addPerson(Model model){
        model.addAttribute("person", new PersonFormDTO());
        return "personform";

    }

    @GetMapping("/film/add")
    public String addFilm(Model model){
        model.addAttribute("film", new FilmFormDTO());
        model.addAttribute("persons", cinemaService.getPersonnes());
        return "filmform";
    }

    @GetMapping("/film/delete/{id}")
    public String deleteFilm(@PathVariable(name = "id") long id){
        cinemaService.deleteFilm(id);
        return "redirect:/";
    }

    @PostMapping("/role/add")
    public String addRole(@ModelAttribute RoleFormDTO roleFormDTO){
        cinemaService.saveRole(roleFormDTO);
        return "redirect:/film/"+roleFormDTO.getFilmId();
    }
}
