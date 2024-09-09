package eni.tp.app.eni_app.Bll;

import eni.tp.app.eni_app.Bo.Genre;

import java.util.List;

public interface IGenreManger {
    public List<Genre> getGenres() ;

    public Genre getGenre(int id);
}
