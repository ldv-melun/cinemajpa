package org.vincimelun.cinemajpa.formdata;

import org.springframework.web.multipart.MultipartFile;

public class PersonFormDTO {
    private long id;
    private String nom;
    private String prenom;
    private Integer anneeNaissance;
    private String photo;
    private MultipartFile ficPhoto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(Integer anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public MultipartFile getFicPhoto() {
        return ficPhoto;
    }

    public void setFicPhoto(MultipartFile ficPhoto) {
        this.ficPhoto = ficPhoto;
    }
}
