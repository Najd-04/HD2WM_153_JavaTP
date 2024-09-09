package eni.tp.app.eni_app.Bll;

import eni.tp.app.eni_app.Bo.Genre;
import eni.tp.app.eni_app.Dao.IDAOGenre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreManager implements IGenreManger {


    private IDAOGenre genreDAO;
    public GenreManager(IDAOGenre genreDAO) {
        this.genreDAO = genreDAO;

    }
    @Override
    public List<Genre> getGenres() {



        return genreDAO.findAll();
    }

    @Override
    public Genre getGenre(int id) {
        return genreDAO.findById(id);
    }

}
