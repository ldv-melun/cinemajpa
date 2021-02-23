package org.vincimelun.cinemajpa.formdata;

public class RoleFormDTO {
    private long id;
    private int order;
    private String name;
    private long acteurId;
    private long filmId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getActeurId() {
        return acteurId;
    }

    public void setActeurId(long acteurId) {
        this.acteurId = acteurId;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }
}
