package eni.tp.app.eni_app.Bo;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

public class Movie {
    public long id;
    @NotBlank(message="le titre doit être renseigné")
    @Size(min=2,max=250,message=("Doit avoir au moins 2 caractères"))
    public String title;

    public int note = 5;

    @Min(value=1895,message="Veuillez saisir année cohérante")
    public int year;


    @Min(value=1,message="il faut au moins 1 min")
    public int duration;

    @NotBlank(message="le synopsis doit être renseigné")
    public String synopsis;
    public String url;

    public Movie(String title, long id, int year, int duration, String synopsis, String url) {
        this.title = title;
        this.id = id;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.url = url;
    }

    public Movie(long id, String title, int year, int duration, String synopsis, String url) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.url = url;
    }

    public Movie() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNote(){
        return note;
    }
}
