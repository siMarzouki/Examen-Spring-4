package tn.esprit.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DAO.Entities.Salle;

public interface SalleRepo extends JpaRepository<Salle,Long> {
    Salle findByNomSalle(String nom);
}
