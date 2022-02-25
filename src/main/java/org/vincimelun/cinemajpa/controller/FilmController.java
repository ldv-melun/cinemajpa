package org.vincimelun.cinemajpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.vincimelun.cinemajpa.formdata.FilmFormDTO;
import org.vincimelun.cinemajpa.formdata.PersonFormDTO;
import org.vincimelun.cinemajpa.formdata.RoleFormDTO;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;
import org.vincimelun.cinemajpa.service.CinemaService;

import java.util.List;

@Controller
public class FilmController {

    Logger logger = LoggerFactory.getLogger(FilmController.class);

    private CinemaService cinemaService;

    @Autowired
    public FilmController(CinemaService cinemaService){
        this.cinemaService = cinemaService;
    }

    @GetMapping(value = {"/", "/film/index"})
    public String index(Model model){
        model.addAttribute("films", cinemaService.getFilms());
        return "film/list";
    }

    @GetMapping("/film/detail/{id}")
    public String detail(Model model){
        return "todo";
    }

    @GetMapping("/film/edit/{id}")
    public String film(Model model, @PathVariable(name="id") Long id){
        Film film = cinemaService.getFilm(id);
        FilmFormDTO dto = convertFilmEntityToFilmFormDTO(film);
        List<Personne> personnes = cinemaService.getPersonnes();
        model.addAttribute("film", dto);
        model.addAttribute("persons", personnes);
        RoleFormDTO roleDTO = new RoleFormDTO();
        roleDTO.setFilmId(film.getId());
        model.addAttribute("role", roleDTO);
        return "film/form";
    }

    /**
     * convert Film to FilmFormDTO
     * @see  https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
     *
     * @param film
     * @return image dto of film
     */
    private FilmFormDTO convertFilmEntityToFilmFormDTO(Film film) {
        FilmFormDTO dto = new FilmFormDTO();
        dto.setId(film.getId());
        dto.setAfficheNom(film.getAfficheNom());
        dto.setTitre(film.getTitre());
        dto.setResume(film.getResume());
        dto.setRealisateurId(film.getRealisateur().getId());
        dto.setNote(film.getNote());
        return dto;
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
        cinemaService.saveFilm(film);
        return "redirect:/";
    }

    @GetMapping("/film/add")
    public String addFilm(Model model){
        model.addAttribute("film", new FilmFormDTO());
        model.addAttribute("persons", cinemaService.getPersonnes());
        return "film/form";
    }

    @GetMapping("/film/delete/{id}")
    public String deleteFilm(@PathVariable(name = "id") long id){
        cinemaService.deleteFilm(id);
        return "redirect:/";
    }

    @PostMapping("/film/role/add")
    public String addRole(@ModelAttribute RoleFormDTO roleFormDTO){
        logger.info("Create role : " + roleFormDTO.toString());
        cinemaService.saveRole(roleFormDTO);
        return "redirect:/";
    }
}
