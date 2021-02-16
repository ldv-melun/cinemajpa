package org.vincimelun.cinemajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.vincimelun.cinemajpa.formdata.FilmFormDTO;
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

    @GetMapping("/film")
    public String film(Model model, @RequestParam(name="id") Long id){
        Film film = cinemaService.getFilm(id);
        List<Personne> personnes = cinemaService.getPersonnes();
        model.addAttribute("film", film);
        model.addAttribute("persons", personnes);
        return "filmform";
    }

    @PostMapping("/film")
    public String postFilm(@ModelAttribute(name="film") FilmFormDTO film){
        // creer dans CinemaService la méthode de creation/modification du film à partir du DTO
        MultipartFile file = film.getAffiche();
        if(!file.isEmpty()) {
            System.out.println("Nom du champ     : "+file.getName());
            System.out.println("Nom du fichier   : "+file.getOriginalFilename());
            System.out.println("Type de fichier  : "+file.getContentType());
            System.out.println("Taille en octets : "+file.getSize());
        }
        System.out.println("Id réalisateur   : "+film.getRealisateurId());
        return "redirect:/";
    }

}
