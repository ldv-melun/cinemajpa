package org.vincimelun.cinemajpa.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "PERSONS")
public class Personne
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "SURNAME", nullable = false, length = 60)
    private String nom;
    @Basic
    @Column(name = "GIVENNAME", nullable = true, length = 40)
    private String prenom;
    @Basic
    @Column(name = "BIRTH_YEAR", nullable = true)
    private Integer anneeNaiscance;
    @Basic
    @Column(name = "IMAGE_PATH", nullable = true, length = 80)
    private String photo;
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "realisateur")
    private Collection<Film> filmsRealises;
    @OneToMany(mappedBy = "acteur")
    private Collection<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String surname) {
        this.nom = surname;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String givenname) {
        this.prenom = givenname;
    }

    public Integer getAnneeNaiscance() {
        return anneeNaiscance;
    }

    public void setAnneeNaiscance(Integer birthYear) {
        this.anneeNaiscance = birthYear;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String imagePath) {
        this.photo = imagePath;
    }

    public Collection<Film> getFilmsRealises() {
        return filmsRealises;
    }

    public void setFilmsRealises(Collection<Film> filmssById) {
        this.filmsRealises = filmssById;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> playsById) {
        this.roles = playsById;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne that = (Personne) o;
        return id == that.id &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(anneeNaiscance, that.anneeNaiscance) &&
                Objects.equals(photo, that.photo);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, anneeNaiscance, photo);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", anneeNaiscance=" + anneeNaiscance +
                ", photo='" + photo + '\'' +
                '}';
    }

}
