package org.vincimelun.cinemajpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.vincimelun.cinemajpa.model.Film;

public interface FilmRepository extends CrudRepository<Film, Long> {

}
