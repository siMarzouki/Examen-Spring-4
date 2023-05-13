package tn.esprit.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Salle implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idSalle;
    String nomSalle;
    int nombre_personnes;

    @JsonIgnore
    @ManyToOne
    Cinema cinema;

    @JsonIgnore
    @ManyToMany
    List<Film> films;
}
