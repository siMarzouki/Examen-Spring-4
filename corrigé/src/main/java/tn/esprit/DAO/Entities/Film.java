package tn.esprit.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Film implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFilm;

    String nomFilm;
    @Enumerated(EnumType.STRING)
    GenreFilm genre;
    @Temporal(TemporalType.DATE)
    Date date;

    @JsonIgnore
    @ManyToMany(mappedBy = "films")
    List<Salle> salles;
}
