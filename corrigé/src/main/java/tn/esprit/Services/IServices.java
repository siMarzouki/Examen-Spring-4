package tn.esprit.Services;
import tn.esprit.DAO.Entities.*;

import java.util.List;

public interface IServices {
    public void ajoutCinemaetSalles(Cinema cinema);
    public void addFilm (Film film);
    List<Film> getFilmParGenre(GenreFilm genreFilm );
    String retrieveFilms();
    public void affecterFilmSalle(Long idFilm, Long idSalle);
    public void supprimerFilms();
    public void suppCinemaAllSalles (Long idCinema);

}
