package tn.esprit.RestControllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.Entities.Cinema;
import tn.esprit.DAO.Entities.Film;
import tn.esprit.DAO.Entities.GenreFilm;
import tn.esprit.DAO.Entities.Salle;
import tn.esprit.Services.IServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class RestControllers {
    @Autowired
    IServices services;


    @PostMapping("/ajoutCinemaetSalles")
    public void ajoutCinemaetSalles(@RequestBody Cinema cinema) {
        services.ajoutCinemaetSalles(cinema);
    }

    @PostMapping("/addFilm")
    public void addFilm(@RequestBody Film film) {
        services.addFilm(film);
    }

    @GetMapping("/getFilmParGenre")
    public List<Film> getFilmParGenre(@RequestParam GenreFilm genreFilm) {
        return services.getFilmParGenre(genreFilm);
    }


    @PutMapping("/affecterFilmSalle")
    public void affecterFilmSalle(@RequestParam Long idFilm, @RequestParam Long idSalle) {
       services.affecterFilmSalle(idFilm,idSalle);
    }

    @DeleteMapping("/supprimerFilms")
    public void supprimerFilms() {
        services.supprimerFilms();
    }

    @DeleteMapping("/suppCinemaAllSalles")
    public void suppCinemaAllSalles(Long idCinema) {
       services.suppCinemaAllSalles(idCinema);
    }
}
