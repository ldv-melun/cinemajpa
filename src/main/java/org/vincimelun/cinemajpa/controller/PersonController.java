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
public class PersonController {
    private CinemaService cinemaService;

    @Autowired
    public PersonController(CinemaService cinemaService){
        this.cinemaService = cinemaService;
    }

    @GetMapping("/person")
    public String listPersons(Model model){
        model.addAttribute("persons", cinemaService.getPersonnes());
        return "person/list";
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
        return "person/form";
    }

    @GetMapping("/person/add")
    public String addPerson(Model model){
        model.addAttribute("person", new PersonFormDTO());
        return "person/form";

    }


}
