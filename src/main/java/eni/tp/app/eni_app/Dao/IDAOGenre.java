package eni.tp.app.eni_app.Dao;

import eni.tp.app.eni_app.Bo.Genre;

import java.util.List;

public interface IDAOGenre {


    List<Genre> findAll();

    Genre findById(int id);
}
