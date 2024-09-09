package eni.tp.app.eni_app.ihm.Converter;

import eni.tp.app.eni_app.Bll.IGenreManger;
import eni.tp.app.eni_app.Bo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



    @Component
    public class StringToGenreConverter implements Converter<String, Genre> {
        @Autowired
        private IGenreManger GenreManager;

        @Override
        public Genre convert(String idGenres) {

            return GenreManager.getGenre(Integer.parseInt(idGenres));
        }
    }

    


