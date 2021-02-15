package org.vincimelun.cinemajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.service.CinemaDataSource;

@Controller
public class MainController {
    private CinemaDataSource cinemaDataSource;

    @Autowired
    public MainController(CinemaDataSource cinemaDataSource){
        this.cinemaDataSource = cinemaDataSource;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("films", cinemaDataSource.getFilms());
        return "filmlist";
    }

    @GetMapping("/film")
    public String film(Model model, @RequestParam(name="id") Long id){
        Film film = cinemaDataSource.getFilm(id);
        model.addAttribute("film", film);
        return "filmform";
    }

    @PostMapping("/film")
    public String postFilm(@ModelAttribute(name="film") Film film){
        Film filmBdd = cinemaDataSource.getFilm(film.getId());
        filmBdd.setTitre(film.getTitre());
        filmBdd.setResume(film.getResume());
        cinemaDataSource.saveFilm(filmBdd);
        return "redirect:/";
    }

}
