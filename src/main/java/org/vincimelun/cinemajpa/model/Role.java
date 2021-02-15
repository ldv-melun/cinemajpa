package org.vincimelun.cinemajpa.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Projet CinemaJPA-2
 * Pour LAERCE SAS
 * <p>
 * Créé le  03/02/2017.
 *
 * @author fred
 */
@Entity
@Table(name = "PLAY")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "RANK", nullable = false)
    private int order;
    @Basic
    @Column(name = "NAME", nullable = true, length = 90)
    private String name;
    @ManyToOne
    @JoinColumn(name="PERSON_ID")
    private Personne acteur;
    @ManyToOne
    @JoinColumn(name="FILM_ID")
    private Film film;

    public void setId(long key) {
        this.id = key;
    }

    public long getId(){
        return this.id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Personne getActeur() {
        return acteur;
    }

    public void setActeur(Personne acteur) {
        this.acteur = acteur;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role that = (Role) o;
        return id == that.id &&
                order == that.order &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, name);
    }


}
