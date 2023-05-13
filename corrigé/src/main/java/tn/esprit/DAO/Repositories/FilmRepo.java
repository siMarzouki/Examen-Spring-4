package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Film;

public interface FilmRepo extends JpaRepository<Film,Long> {
}
