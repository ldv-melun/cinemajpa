package org.vincimelun.cinemajpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.vincimelun.cinemajpa.model.Personne;

public interface PersonneRepository extends CrudRepository<Personne, Long> {
}
