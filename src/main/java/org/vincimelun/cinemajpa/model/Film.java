package org.vincimelun.cinemajpa.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name="FILMS")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "TITLE", nullable = true, length = 50)
    private String titre;
    @Basic
    @Column(name = "RATING", nullable = true, precision = 1)
    private BigDecimal note;
    @Basic
    @Column(name = "IMAGE_PATH", nullable = true, length = 120)
    private String afficheNom;
    @Basic
    @Lob
    @Column(name = "SUMMARY", nullable = true)
    private String resume;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FILM_DIRECTOR")
    private Personne realisateur;
    @OneToMany(mappedBy = "film")
    private Collection<Role> acteurs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String title) {
        this.titre = title;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal rating) {
        this.note = rating;
    }

    public String getAfficheNom() {
        return afficheNom;
    }

    public void setAfficheNom(String imagePath) {
        this.afficheNom = imagePath;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String summary) {
        this.resume = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film that = (Film) o;
        return id == that.id &&
                Objects.equals(titre, that.titre) &&
                Objects.equals(note, that.note) &&
                Objects.equals(afficheNom, that.afficheNom) &&
                Objects.equals(resume, that.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, note, afficheNom, resume);
    }

    @Override
    public String toString() {
        return "Film{" +
                "titre='" + titre + '\'' +
                ", note=" + note +
                ", afficheNom='" + afficheNom + '\'' +
                '}';
    }


    public Personne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
    }

    public Collection<Role> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Collection<Role> playsById) {
        this.acteurs = playsById;
    }

}
