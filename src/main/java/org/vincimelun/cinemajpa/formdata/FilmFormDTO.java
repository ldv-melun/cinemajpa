package org.vincimelun.cinemajpa.formdata;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class FilmFormDTO {
    private long id;
    private String titre;
    private BigDecimal note;
    private String afficheNom;
    private String resume;
    private long realisateurId;
    private MultipartFile affiche;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }

    public String getAfficheNom() {
        return afficheNom;
    }

    public void setAfficheNom(String afficheNom) {
        this.afficheNom = afficheNom;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public long getRealisateurId() {
        return realisateurId;
    }

    public void setRealisateurId(long realisateurId) {
        this.realisateurId = realisateurId;
    }

    public MultipartFile  getAffiche() {
        return affiche;
    }

    public void setAffiche(MultipartFile affiche) {
        this.affiche = affiche;
    }
}
