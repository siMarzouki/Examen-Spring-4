package tn.esprit.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.DAO.Entities.Cinema;
import tn.esprit.DAO.Entities.Film;
import tn.esprit.DAO.Entities.GenreFilm;
import tn.esprit.DAO.Entities.Salle;
import tn.esprit.DAO.Repositories.CinemaRepo;
import tn.esprit.DAO.Repositories.FilmRepo;
import tn.esprit.DAO.Repositories.SalleRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class Services implements IServices {

    CinemaRepo cinemaRepo;
    SalleRepo salleRepo;
    FilmRepo filmRepo;
    @Override
    public void ajoutCinemaetSalles(Cinema cinema) {
        cinemaRepo.save(cinema);
        cinema.getSalles()
                .stream()
                .forEach(salle -> {
                    salle.setCinema(cinema);
                    salleRepo.save(salle);
                });

    }

    @Override
    public void addFilm(Film film) {
        filmRepo.save(film);
    }

    @Override
    public List<Film> getFilmParGenre(GenreFilm genreFilm) {
        return filmRepo.findAll()
                .stream()
                .filter(film -> film.getGenre().equals(genreFilm))
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(fixedRate = 30000)
    public String retrieveFilms() {
        List<String> movies = new ArrayList<>();
        filmRepo.findAll()
                .stream()
                .filter(film -> film.getDate().before(new Date()))
                .forEach(film -> {
                    movies.add(film.getNomFilm());
                });
        String res= movies.stream().reduce("Films Dépassés : ", (s1, s2) -> s1+" " + s2);
        log.info(res);
        return  res;
    }

    @Override
    public void affecterFilmSalle(Long idFilm, Long idSalle) {
        Film f = filmRepo.findById(idFilm).get();
        Salle s = salleRepo.findById(idSalle).get();
        f.getSalles().add(s);
        s.getFilms().add(f);
        salleRepo.save(s);
        filmRepo.save(f);
    }

    @Override
    public void supprimerFilms() {
        //finding solo movies
        List<Film> soloFilms= filmRepo.findAll()
                .stream()
                .filter(film ->{
                    if (film.getSalles()!=null){
                        return film.getSalles().size()==1;
                    }
                    return false;
                })
                .collect(Collectors.toList());
        //deleting solo movies from salles
        salleRepo.findAll()
                .stream()
                .forEach(salle -> {
                    List<Film> films = salle.getFilms()
                            .stream()
                            .filter(film -> !soloFilms.contains(film))
                            .collect(Collectors.toList());
                    salle.setFilms(films);
                    salleRepo.save(salle);
                });
        //add solo movies to salle c
        Salle c = salleRepo.findByNomSalle("C");
        List<Salle> sallesForSolo = new ArrayList<>();
        sallesForSolo.add(c);
        soloFilms.stream().forEach(film -> {
            film.setSalles(sallesForSolo);
            filmRepo.save(film);
        });
        c.getFilms().addAll(soloFilms);
        salleRepo.save(c);
    }

    @Override
    public void suppCinemaAllSalles(Long idCinema) {
        Cinema c = cinemaRepo.findById(idCinema).get();
        c.getSalles()
                .stream()
                .forEach(salle -> salleRepo.delete(salle));
        cinemaRepo.delete(c);
    }
}
