package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Cinema;

public interface CinemaRepo extends JpaRepository<Cinema,Long> {
}
